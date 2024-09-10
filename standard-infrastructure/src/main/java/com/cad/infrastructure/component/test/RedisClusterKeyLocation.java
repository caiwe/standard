package com.cad.infrastructure.component.test;

import redis.clients.jedis.Connection;
import redis.clients.jedis.ConnectionPool;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.util.JedisClusterCRC16;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class RedisClusterKeyLocation {

    public static void main(String[] args) {
        // 假设集群节点列表
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("node1", 7000));
        nodes.add(new HostAndPort("node2", 7001));
        nodes.add(new HostAndPort("node3", 7002));
        JedisCluster jedisCluster = new JedisCluster(nodes);

        // 需要执行脚本的键列表
        List<String> keys = Arrays.asList("key1", "key2", "key3");

        // 缓存键节点信息
        ConcurrentMap<String, String> keyNodeCache = new ConcurrentHashMap<>();

        // 获取每个键所在节点的信息
        Map<String, String> keyNodeMap = getKeyNodeMap(jedisCluster, keys, keyNodeCache);

        // 将键分组到同一节点上
        Map<String, List<String>> nodeKeysMap = groupKeysByNode(keyNodeMap);

        // 在客户端代码中协调执行逻辑
        executeComplexOperation(jedisCluster, nodeKeysMap);

        jedisCluster.close();
    }

    private static Map<String, String> getKeyNodeMap(JedisCluster jedisCluster, List<String> keys, ConcurrentMap<String, String> keyNodeCache) {
        Map<String, String> keyNodeMap = new HashMap<>();
        for (String key : keys) {
            String nodeInfo = keyNodeCache.get(key);
            if (nodeInfo == null) {
                try {
                    nodeInfo = getNodeInfoForKey(jedisCluster, key);
                    keyNodeCache.put(key, nodeInfo);
                } catch (Exception e) {
                    System.err.println("Error getting node information for key: " + key);
                }
            }
            keyNodeMap.put(key, nodeInfo);
        }
        return keyNodeMap;
    }


    private static String getNodeInfoForKey(JedisCluster jedisCluster, String key) {
        // 计算键对应的哈希槽
        int slot = JedisClusterCRC16.getSlot(key);

        Connection connection = jedisCluster.getConnectionFromSlot(slot);

        if (connection != null) {
            return connection.toString();
        }

        throw new RuntimeException("Unable to find node information for key: " + key);
    }

    private static Map<String, List<String>> groupKeysByNode(Map<String, String> keyNodeMap) {
        Map<String, List<String>> nodeKeysMap = new HashMap<>();
        for (Map.Entry<String, String> entry : keyNodeMap.entrySet()) {
            String key = entry.getKey();
            String node = entry.getValue();

            if (!nodeKeysMap.containsKey(node)) {
                nodeKeysMap.put(node, new ArrayList<>());
            }
            nodeKeysMap.get(node).add(key);
        }
        return nodeKeysMap;
    }

    private static void executeComplexOperation(JedisCluster jedisCluster, Map<String, List<String>> nodeKeysMap) {
        for (Map.Entry<String, List<String>> entry : nodeKeysMap.entrySet()) {
            String node = entry.getKey();
            List<String> keysOnNode = entry.getValue();

            // 假设我们需要执行的逻辑
            String lockValue = UUID.randomUUID().toString(); // 生成唯一的锁值
            SetParams params = new SetParams();
            params.nx(); // 只有在键不存在的情况下才设置
            params.px(10000); // 设置过期时间为10秒（以毫秒为单位）

            // 在客户端代码中实现协调逻辑
            for (String key : keysOnNode) {
                String result = jedisCluster.set(key, lockValue, params);
                if ("OK".equals(result)) {
                    System.out.println("锁设置成功：" + key);
                } else {
                    System.out.println("锁设置失败：" + key);
                }
            }
        }
    }
}