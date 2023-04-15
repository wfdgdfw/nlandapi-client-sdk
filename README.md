# 接口平台客户端SDK

> 作者：[nland](https://github.com/wfdgdfw)

目前尚不完善，暂时提供代码下载自行安装。




## 快速上手

下载SDK代码

```shell
git clone https://github.com/wfdgdfw/nlandapi-client-sdk
```

maven打包

```shell
maven install
```



在 `application.yml` 添加配置：

```yml
api:
  client:
    access-key: xxxxxx替换成自己的
    secret-key: xxxxxx替换成自己的
```



参考代码

```java
@Resource
private NlandApiClient nlandApiClient;
//post方法
nlandApiClient.post("url",参数)
//get方法
nlandApiClient.post("url",参数)

```

