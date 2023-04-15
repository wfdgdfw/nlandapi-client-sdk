package com.weng.nlandapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;



import java.util.HashMap;
import java.util.Map;

import static com.weng.nlandapiclientsdk.utils.SignUtils.genSign;

/**
 * 调用第三方接口的客户端
 *
 * @author weng
 */
public class NlandApiClient {

    private String accessKey;

    private String secretKey;

    public NlandApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String get(String url,HashMap<String,Object> paramMap) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        return HttpUtil.get(url, paramMap);
    }

    public String post(String url,HashMap<String,Object> paramMap) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        String result = HttpUtil.post(url, paramMap);
        return result;
    }

    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        // 一定不能直接发送
//        hashMap.put("secretKey", secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", genSign(body, secretKey));
        return hashMap;
    }

    public String postByJson(String url, Object object) {
        String json = JSONUtil.toJsonStr(object);
        HttpResponse httpResponse = HttpRequest.post(url)
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        return httpResponse.body();
    }
}
