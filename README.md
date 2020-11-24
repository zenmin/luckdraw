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

![5111.jpg](<https://github.com/zenmin/luckdraw/blob/master/img/5111.jpg>)

![2719.jpg](<https://github.com/zenmin/luckdraw/blob/master/img/2719.jpg>)

![4535.jpg](<https://github.com/zenmin/luckdraw/blob/master/img/4535.jpg>)

## 运行程序（Windows环境）

1、[下载release包](https://github.com/zenmin/luckdraw/releases/download/1.0-windows/luckdraw-win.zip)

2、解压release包

3、双击“运行程序.bat”

4、使用RedisDesktopManager[点击下载](http://www.downza.cn/soft/210734.html)连接本地redis，默认端口6379

5、进入redis console db0 执行命令

```CQL
set TOKEN xxxx(你的令牌)
```

6、等待程序启动完成直接访问打印在java console上的地址

7、打开java console上的地址如http://127.0.0.1:2345 然后输入刚刚在redis中设置令牌即可使用

8、进入管理地址http://127.0.0.1:2345/mgr 对程序进行管理

###### 如果需要修改配置如使用远程redis 请编辑application.properties文件




## 运行程序（Linux环境）

### 准备工作

1、安装Redis，[Redis下载地址](https://redis.io/download)

2、给redis设置默认token （使用RedisDesktopManager或redis-cli）

```CQL
set TOKEN xxxx(你的令牌)
```

### 方式① 本地jar启动

前提：需要JRE环境 下面有下载地址

1、[下载release包和外部配置文件](https://github.com/zenmin/luckdraw/releases/download/1.0/luckdraw-0.0.1-SNAPSHOT.jar)

```shell
wget https://github.com/zenmin/luckdraw/releases/download/1.0/luckdraw-0.0.1-SNAPSHOT.jar
wget https://github.com/zenmin/luckdraw/raw/master/src/main/resources/application.properties
```

2、修改application.properties中的配置
   - 运行程序

```shell
java -jar -Xms128m -Xmx128m ./luckdraw-0.0.1-SNAPSHOT.jar
```

### 方式② Docker启动(推荐)

前提： 需要准备docker环境 下面有安装教程

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



#### 至此程序就跑起来了 ，管理页面：http://ip:端口/mgr

#### 如果你需要修改源码 请查看下面源码构建步骤

-----



## 源码构建

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
  set TOKEN xxxx(你的令牌)
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

