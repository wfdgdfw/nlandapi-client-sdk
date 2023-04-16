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
    # 测试key值
    access-key: admin
    secret-key: 12345678
```



参考代码

方法一 ：需配置application.yml

```java
@Resource
private NlandApiClient nlandApiClient;
```

方法二 ：无需配置application.yml

```java
NlandApiClient nlandApiClient = new NlandApiClient(accessKey,secretKey);
```

调用

```java
//post方法
nlandApiClient.post("path",参数)
//get方法
nlandApiClient.get("path",参数)
```

