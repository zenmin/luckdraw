# LuckDraw
- Java开发 包含后端+前端 
- 可内定中奖人员
- 支持Excel表格一键导入员工

## 使用技术：

- Redis
- EasyPOI
- Vue

## 程序截图

![2705.jpg](<https://github.com/zenmin/luckdraw/blob/master/img/2705.jpg>)

![2719.jpg](<https://github.com/zenmin/luckdraw/blob/master/img/2719.jpg>)

![5111.jpg](<https://github.com/zenmin/luckdraw/blob/master/img/5111.jpg>)

![4535.jpg](<https://github.com/zenmin/luckdraw/blob/master/img/4535.jpg>)

## 运行程序

### 准备工作

1、安装Redis，[Redis下载地址](https://redis.io/download)

2、给redis设置默认token （使用RedisDesktopManager或redis-cli）

```CQL
set token xxxx(你的令牌)
```

### 方式① 本地jar启动

- 需要JRE环境

1、[下载release包](https://github.com/zenmin/luckdraw/releases/download/1.0/luckdraw-0.0.1-SNAPSHOT.jar)

```shell
wget https://github.com/zenmin/luckdraw/releases/download/1.0/luckdraw-0.0.1-SNAPSHOT.jar
```

2、运行程序

```shell
java -jar -Xms128m -Xmx128m ./luckdraw-0.0.1-SNAPSHOT.jar
```

### 方式② Docker启动(推荐)

- 需要准备docker环境 下面有安装教程

1、[下载release包](https://github.com/zenmin/luckdraw/releases/download/1.0/luckdraw-0.0.1-SNAPSHOT.jar)

```shell
wget https://github.com/zenmin/luckdraw/releases/download/1.0/luckdraw-0.0.1-SNAPSHOT.jar
```

2、编写Dockerfile

```shell
FROM java:8
ADD /luckdraw-0.0.1-SNAPSHOT.jar //
ENTRYPOINT ["java","-Xms128m","-Xmx128m","-jar", "/luckdraw-0.0.1-SNAPSHOT.jar"]
```

3、构建镜像

```shell
# 新建一个文件夹
➜ mkdir luckdraw
# 将程序和Dockerfile放入文件夹中
➜ mv luckdraw-0.0.1-SNAPSHOT.jar luckdraw
➜ mv Dockerfile luckdraw
# 构建镜像 
➜ docker builld -t luckdraw .
```

4、启动程序

```shell
docker run --name luckdraw -d -p 8080:2345 luckdraw
```

##源码构建

### 方式① 本地jar启动

- 本地需要配置Maven环境变量，建议[使用最新版](https://maven.apache.org/download.cgi)

- 本地需要配置JDK环境变量，[JDK1.8下载地址](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

- 本地需要配置Git环境变量，[Git下载地址](https://git-scm.com/downloads)

- 本地需要配置Redis环境，[Redis下载地址](https://redis.io/download)

  ### Redis启动后先设置程序访问令牌 进入redis console  （使用RedisDesktopManager或redis-cli） 执行命令 set token xxxx(你的令牌)

```shell
# 下载master分支源码到本地
➜ git clone https://github.com/zenmin/luckdraw.git
# 进入luckdraw源码根目录
➜ cd luckdraw
# 修改配置文件 配置端口及Redis地址
➜ vim src/main/resource/application.properties
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

- 本地需要配置Redis环境，docker pull redis:5.0

  ### Redis启动后先设置程序访问令牌 进入redis console（使用RedisDesktopManager或redis-cli） 执行命令

  ```CQL
  set token xxxx(你的令牌)
  ```

```shell
# 下载master分支源码到本地
➜ git clone https://github.com/zenmin/luckdraw.git
# 进入luckdraw源码根目录
➜ cd luckdraw
# 修改配置文件 配置端口及Redis地址
➜ vim src/main/resource/application.properties
# 构建docker image
➜ mvn docker:build
# 启动程序
➜ docker run --name luckdraw -d -p 8080:2345 luckdraw
```

## 说明

- **管理员页面地址 http://ip:端口/mgr**
- 进入页面的令牌 就是你刚刚在redis里面设置的token
- 导入员工前请先下载模板  按照模板导入员工信息
- 一轮抽奖只会有一名内定人员中奖
- 有问题请联系我**741703967@qq.com**

## 鸣谢

前端样式借用这位兄弟的样式

<https://github.com/fouber/lottery>

逻辑方面我稍作修改 加入了内定人员 修改了localstorge存储方式 页面加入关闭当前中奖人员的蒙层功能

