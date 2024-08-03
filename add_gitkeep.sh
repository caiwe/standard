#!/bin/bash

# Linux/macOS Shell 脚本: 在项目中所有空文件夹中一次性添加.gitkeep文件
# 获取项目根目录
project_root=$(pwd)

# 遍历项目中的所有文件夹
find "$project_root" -type d | while read dir; do
  # 检查目录是否为空
  if [ -z "$(ls -A $dir)" ]; then
    # 如果目录为空，则创建一个 .gitkeep 文件
    touch "$dir/.gitkeep"
  fi
done