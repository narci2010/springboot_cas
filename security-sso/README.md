# <font color='red'>测试启动</font>
在当前项目目录`security-sso`cmd下执行：

```cmd
build.cmd debug
```

访问测试：http://localhost:8445
用户：admin/123



# <font color='red'>项目打包</font>

```cmd
build.cmd package
```

# 项目运行

> 项目无需把war包放置tomcat中，可直接运行

```cmd
java -jar cas.war
```


# 自定义主题

http://localhost:8445/login?service=https://baidu.com