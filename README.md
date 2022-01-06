# LuckDraw
- Java开发 包含后端+前端 
- 特性：可内定中奖人员
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

4、浏览器访问<http://ip:端口>，第一次访问会让你输入令牌  默认为123456 ，若需修改自行连接本地redis修改

## 说明

- Windows版程序包内已经包含java运行环境和redis，无需再进行其他安装，Linux环境下自行安装java和redis运行环境
- **管理员页面地址 <http://ip:端口/mgr>**
- 导入员工前请先下载模板  按照模板导入员工信息
- 一轮抽奖只会有一名内定人员中奖
- 有问题请联系我**741703967@qq.com**，用完记得给一个star哦

## 鸣谢

前端样式借用这位兄弟的样式

<https://github.com/fouber/lottery>

逻辑方面我稍作修改 
 - 加入了内定人员 
 - 修改了localstorge存储方式 
 - 页面加入关闭当前中奖人员的蒙层功能



