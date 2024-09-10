//package com.cad.infrastructure.component.test;
//
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.exceptions.JedisDataException;
//import redis.clients.jedis.util.JedisClusterCRC16;
//
//import java.util.*;
//
//public class RedisClusterScriptExample {
//
//    private static final int CACHE_TTL_SECONDS = 60; // 缓存过期时间
//
//    public static void main(String[] args) {
//        // 假设集群节点列表
//        Set<HostAndPort> nodes = new HashSet<>();
//        nodes.add(new HostAndPort("node1", 7000));
//        nodes.add(new HostAndPort("node2", 7001));
//        nodes.add(new HostAndPort("node3", 7002));
//        JedisCluster jedisCluster = new JedisCluster(nodes);
//
//        // 需要执行脚本的键列表
//        List<String> keys = Arrays.asList("key1", "key2", "key3");
//
//        // 缓存键节点信息
//        Map<String, NodeInfo> keyNodeCache = new HashMap<>();
//
//        // 获取每个键所在节点的信息
//        Map<String, String> keyNodeMap = getKeyNodeMap(jedisCluster, keys, keyNodeCache);
//
//        // 将键分组到同一节点上
//        Map<String, List<String>> nodeKeysMap = groupKeysByNode(keyNodeMap);
//
//        // 分段执行脚本
//        executeScripts(jedisCluster, nodeKeysMap);
//
//        jedisCluster.close();
//    }
//
//    private static Map<String, String> getKeyNodeMap(JedisCluster jedisCluster, List<String> keys, Map<String, NodeInfo> keyNodeCache) {
//        Map<String, String> keyNodeMap = new HashMap<>();
//        for (String key : keys) {
//            NodeInfo nodeInfo = keyNodeCache.get(key);
//            if (nodeInfo == null) {
//                try {
//                    int slot = JedisClusterCRC16.getSlot(key);
//
//                    jedisCluster.getClusterNodes()
//                    String host = jedisCluster.getClient(key).getHost();
//                    int port = jedisCluster.getClient(key).getPort();
//                    nodeInfo = new NodeInfo(host, port);
//                    keyNodeCache.put(key, nodeInfo);
//                    // 设置缓存过期时间
//                    keyNodeCache.put(key, nodeInfo);
//                } catch (JedisDataException e) {
//                    System.err.println("Error getting node information for key: " + key);
//                }
//            }
//            keyNodeMap.put(key, nodeInfo.getHost() + ":" + nodeInfo.getPort());
//        }
//        return keyNodeMap;
//    }
//
//    private static Map<String, List<String>> groupKeysByNode(Map<String, String> keyNodeMap) {
//        Map<String, List<String>> nodeKeysMap = new HashMap<>();
//        for (Map.Entry<String, String> entry : keyNodeMap.entrySet()) {
//            String key = entry.getKey();
//            String node = entry.getValue();
//
//            if (!nodeKeysMap.containsKey(node)) {
//                nodeKeysMap.put(node, new ArrayList<>());
//            }
//            nodeKeysMap.get(node).add(key);
//        }
//        return nodeKeysMap;
//    }
//
//    private static void executeScripts(JedisCluster jedisCluster, Map<String, List<String>> nodeKeysMap) {
//        for (Map.Entry<String, List<String>> entry : nodeKeysMap.entrySet()) {
//            String node = entry.getKey();
//            List<String> keysOnNode = entry.getValue();
//
//            // 假设我们需要执行的脚本逻辑
//            String script = "local result = tonumber(ARGV[1]) + tonumber(ARGV[2]); return redis.call('SET', KEYS[1], result)";
//            List<String> keys = keysOnNode;
//            Object[] args = new Object[]{"value1", "value2"};
//
//            // 执行脚本
//            String[] splitNode = node.split(":");
//            HostAndPort hostAndPort = new HostAndPort(splitNode[0], Integer.parseInt(splitNode[1]));
//            JedisCluster localCluster = new JedisCluster(hostAndPort);
//            localCluster.eval(script, keys.toArray(new String[0]), args);
//            localCluster.close();
//        }
//    }
//
//    static class NodeInfo {
//        private final String host;
//        private final int port;
//
//        public NodeInfo(String host, int port) {
//            this.host = host;
//            this.port = port;
//        }
//
//        public String getHost() {
//            return host;
//        }
//
//        public int getPort() {
//            return port;
//        }
//    }
//}