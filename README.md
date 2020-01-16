# LuckDraw
###年会抽奖程序 包含后端+前端 可内定 中奖人员

## 使用技术：

- JDK
- Redis
- EasyPOI
- Vue

## 程序构建

### 方式① 本地jar启动

- 本地需要配置Maven环境变量，建议[使用最新版](https://maven.apache.org/download.cgi)

- 本地需要配置JDK环境变量，[JDK1.8下载地址](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

- 本地需要配置Git环境变量，[Git下载地址](https://git-scm.com/downloads)

- 本地需要配置Redis环境，[Redis下载地址](https://redis.io/download)

  ### Redis启动后先设置程序访问令牌 进入redis console 执行命令 set token xxxx(你的令牌)

```shell
# 下载master分支源码到本地
➜ git clone https://github.com/zenmin/luckdraw.git
# 修改配置文件 配置端口及Redis地址
➜ vim src/main/resource/application.properties
# 进入luckdraw源码根目录
➜ cd luckdraw
# 执行打包
➜ mvn clean package
# 进入target目录
➜ cd target
# 启动程序
➜ java -jar -Xms128m -Xmx128m ./luckdraw-0.0.1-SNAPSHOT.jar
```

### 方式② Docker启动（推荐）

- 本地需要配置Docker环境，[Docker快速安装](https://blog.csdn.net/zenmin2015/article/details/86551199)

- 本地需要配置Maven环境变量，建议[使用最新版](https://maven.apache.org/download.cgi)

- 本地需要配置JDK环境变量，[JDK1.8下载地址](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

- 本地需要配置Git环境变量，[Git下载地址](https://git-scm.com/downloads)

- 本地需要配置Redis环境，

  ### Redis启动后先设置程序访问令牌 进入redis console（使用RedisDesktopManager或redis） 执行命令

  ```CQL
  set token xxxx(你的令牌)
  ```

```shell
# 下载master分支源码到本地
➜ git clone https://github.com/zenmin/luckdraw.git
# 修改配置文件 配置端口及Redis地址
➜ vim src/main/resource/application.properties
# 进入luckdraw源码根目录
➜ cd luckdraw
# 执行打包
➜ mvn clean package
# 进入target目录
➜ cd target
# 启动程序
➜ java -jar -Xms128m -Xmx128m ./luckdraw-0.0.1-SNAPSHOT.jar
```

