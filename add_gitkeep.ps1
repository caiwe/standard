
# Windows PowerShell 脚本: 在项目中所有空文件夹中一次性添加.gitkeep文件
function Create-GitKeep {
    param (
        [Parameter(Mandatory=$true)]
        [string]$Path
    )

    # 获取当前路径下的所有子目录
    $directories = Get-ChildItem -Path $Path -Directory

    foreach ($dir in $directories) {
        # 检查当前目录是否为空
        if ((Get-ChildItem -Path $dir.FullName -Force).Count -eq 0) {
            # 如果目录为空，则创建 .gitkeep 文件
            New-Item -ItemType File -Path "$($dir.FullName)\.gitkeep" -Force
        }

        # 递归地处理子目录
        Create-GitKeep -Path $dir.FullName
    }
}

# 调用函数，从指定的根目录开始
$projectRoot = 'E:\workspace\standard' # 更改为你的项目根目录
Create-GitKeep -Path $projectRoot