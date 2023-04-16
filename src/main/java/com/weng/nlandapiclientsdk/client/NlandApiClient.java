package com.weng.nlandapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
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

    public String get(String requestPath,HashMap<String,Object> paramMap) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        return HttpRequest.get(CommonConstant.BASE_URL + requestPath).addHeaders(getHeaderMap("")).form(paramMap).execute().body();
    }

    public String get(String requestPath) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        return HttpRequest.get(CommonConstant.BASE_URL + requestPath).addHeaders(getHeaderMap("")).execute().body();
    }

    public String postByMap(String requestPath,HashMap<String,Object> paramMap) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        return HttpRequest.post(CommonConstant.BASE_URL + requestPath).form(paramMap).addHeaders(getHeaderMap("")).execute().body();
    }

    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", genSign(body, secretKey));
        return hashMap;
    }

    /**
     * post方法 + 请求体（json）
     * @param requestPath
     * @param object
     * @return
     */
    public String post(String requestPath, Object object) {
        String json = JSONUtil.toJsonStr(object);
        HttpResponse httpResponse = HttpRequest.post(CommonConstant.BASE_URL + requestPath)
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        return httpResponse.body();
    }
}
