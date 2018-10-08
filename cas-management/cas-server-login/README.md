yellowcong的CAS 模版
============================

这个玩意，拿到手后，需要修改证书，或则修改application.properties ,配置`server.ssl.enabled=false`

# 版本依赖

```xml
jdk1.8版本往上走
```

# 项目的debug 和 打包

查看帮助
```bash
./build.sh help
```

debug调试
```bash
./build.sh debug
```


打成war包
```bash
./build.sh package
```

To update `SNAPSHOT` versions run:

```bash
./build.sh package -U
```

# war包依赖下载
我由于实在是通过maven下载不下来，改了镜像源，也不好用，所以就只能单独下载了

## 官网下砸地址
https://oss.sonatype.org/content/repositories/releases/org/apereo/cas/

## 七牛下载地址

###5.2.0 版本war包下载地址
http://yellowcong.qiniudn.com/cas-server-webapp-tomcat-5.2.0.war

###5.2.1 版本war包下载地址
http://yellowcong.qiniudn.com/cas-server-webapp-tomcat-5.2.1.war

### 5.2.2 版本的war包下载地址
http://yellowcong.qiniudn.com/cas-server-webapp-tomcat-5.2.2.war
