package com.lpp.larkRobot.okhttp;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;


@Slf4j
public class OkHttpRequestUtils {

    /**
     * GET请求 只有URL
     *
     * @param url url
     */
    public static ResponseResult get(String url) {
        return get(url, null, null, null);
    }


    /**
     * patch 方法
     *
     * @param url
     * @param params
     * @param headers
     * @param object
     * @return
     */
    public static ResponseResult patch(String url, Map<String, String> params, String pathVariableName, String pathVariableValue, Map<String, String> headers, Object object) {
        RequestBody requestBody = buildApplicationJsonBody(JSON.toJSONString(object));

        HttpUrl httpUrl = buildUrl(url, params, pathVariableName, pathVariableValue);
        Request.Builder builder = new Request.Builder()
                .url(httpUrl);
        if (Objects.nonNull(headers)) {
            builder.headers(Headers.of(headers));
        }
        builder.patch(requestBody);
        Request request = builder.build();
        OkHttpClient okHttpClient = OkHttpClientInstance.getOkHttpClient();
        return executeRequest(okHttpClient, request);
    }

    /**
     * GET请求 带URL和查询参数
     *
     * @param url    URL
     * @param params Map<String, String> params 查询参数
     */
    public static ResponseResult get(String url, Map<String, String> params) {
        return get(url, params, null, null);
    }

    /**
     * GET请求 带URL和查询参数以及请求头
     *
     * @param url     URL
     * @param params  查询参数
     * @param headers 请求头
     */
    public static ResponseResult get(String url, Map<String, String> params, Map<String, String> headers) {
        return get(url, params, headers, null);
    }

    /**
     * @param url     URL
     * @param params  查询参数
     * @param headers 请求头
     */
    public static ResponseResult get(String url, Map<String, String> params, Map<String, String> headers, Object tag) {
        HttpUrl httpUrl = buildUrl(url, params);
        Request.Builder builder = new Request.Builder()
                .url(httpUrl);

        if (!Objects.isNull(headers)) {
            builder.headers(Headers.of(headers));
        }
        Request request = builder.build();

//        连接池默认是5个，维持时间为5分支
//       todo liang: 目前client的全部配置都使用默认的配置 后续单独拆开弄成可配置的。
        OkHttpClient okHttpClient = OkHttpClientInstance.getOkHttpClient();

        return executeRequest(okHttpClient, request);
    }

    /**
     * 带路径变量的 get 方法
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static ResponseResult get(String url, Map<String, String> params, String pathVariableName, String pathVariableValue, Map<String, String> headers) {
        HttpUrl httpUrl = buildUrl(url, params, pathVariableName, pathVariableValue);
        Request.Builder builder = new Request.Builder()
                .url(httpUrl);
        if (Objects.nonNull(headers)) {
            builder.headers(Headers.of(headers));
        }
        Request request = builder.build();
        OkHttpClient okHttpClient = OkHttpClientInstance.getOkHttpClient();
        return executeRequest(okHttpClient, request);
    }

    /**
     * 带路径变量的 get 方法
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static ResponseResult del(String url, Map<String, String> params, String pathVariableName, String pathVariableValue, Map<String, String> headers) {
        HttpUrl httpUrl = buildUrl(url, params, pathVariableName, pathVariableValue);
        Request.Builder builder = new Request.Builder()
                .url(httpUrl);
        if (Objects.nonNull(headers)) {
            builder.headers(Headers.of(headers));
        }
        builder.delete();
        Request request = builder.build();
        OkHttpClient okHttpClient = OkHttpClientInstance.getOkHttpClient();
        return executeRequest(okHttpClient, request);
    }


//    --            POST 请求

    /**
     * 发送post请求
     *
     * @param url URL
     * @return Response
     */
    public static ResponseResult post(String url) {
        return post(url, null, null, null, null);
    }

    /**
     * @param url
     * @param params
     * @return
     */
    public static ResponseResult post(String url, Map<String, String> params) {
        return post(url, params, null, null, null);

    }


    public static ResponseResult postWithParams(String url, Map<String, String> params, Object object) {
        RequestBody requestBody = buildApplicationJsonBody(JSON.toJSONString(object));
        return post(url, params, null, requestBody, null);

    }

    public static ResponseResult postWithHeaders(String url, Map<String, String> headers, Object object) {
        RequestBody requestBody = buildApplicationJsonBody(JSON.toJSONString(object));
        return post(url, null, headers, requestBody, null);

    }

    public static ResponseResult post(String url, Object object) {
        RequestBody requestBody = buildApplicationJsonBody(JSON.toJSONString(object));
        return post(url, null, null, requestBody, null);

    }

    public static ResponseResult post(String url, Map<String, String> params, Map<String, String> headers, Object object) {
        RequestBody requestBody = buildApplicationJsonBody(JSON.toJSONString(object));
        return post(url, params, headers, requestBody, null);
    }

    public static ResponseResult postWithHeaderAndBody(String url, Map<String, String> headers, Object body) {
        RequestBody requestBody = buildApplicationJsonBody(JSON.toJSONString(body));
        return post(url, headers, requestBody);
    }

    public static ResponseResult post(String url, Map<String, String> params, Map<String, String> headers) {
        return post(url, params, headers, null, null);

    }

    public static ResponseResult post(String url, Map<String, String> params, Map<String, String> headers, RequestBody body) {
        return post(url, params, headers, body, null);
    }


    public static ResponseResult post(String url, Map<String, String> headers, RequestBody body) {
        HttpUrl httpUrl = buildUrl(url, null);
        Request.Builder builder = new Request.Builder()
                .url(httpUrl);
        if (!Objects.isNull(headers)) {
            builder.headers(Headers.of(headers));
        }
        builder.method(HttpMethod.POST, body);
        Request request = builder.build();
        OkHttpClient okHttpClient = OkHttpClientInstance.getOkHttpClient();
        return executeRequest(okHttpClient, request);
    }

    public static ResponseResult post(String url, Map<String, String> params, Map<String, String> headers, RequestBody body, Object tage) {
        HttpUrl httpUrl = buildUrl(url, params);
        Request.Builder builder = new Request.Builder()
                .url(httpUrl);
        if (!Objects.isNull(headers)) {
            builder.headers(Headers.of(headers));
        }
        if (!Objects.isNull(body)) {
            builder.method(HttpMethod.POST, body);
        }
        Request request = builder.build();
        OkHttpClient okHttpClient = OkHttpClientInstance.getOkHttpClient();
        return executeRequest(okHttpClient, request);
    }

    public static ResponseResult delete(String url, Map<String, String> params, Map<String, String> headers) {

        OkHttpClient okHttpClient = OkHttpClientInstance.getOkHttpClient();
        HttpUrl httpUrl = buildUrl(url, params);
        Request.Builder builder = new Request.Builder()
                .url(httpUrl).delete();
        if (!Objects.isNull(headers)) {
            builder.headers(Headers.of(headers));
        }
        Request request = builder.build();
        return executeRequest(okHttpClient, request);
    }


    /**
     * 通过URL 和查询参数构造HttpUrl
     *
     * @param url    URL
     * @param params 查询参数
     * @return HttpUrl
     */
    private static HttpUrl buildUrl(String url, Map<String, String> params) {
        if (Objects.isNull(params)) {
            return HttpUrl.parse(url);
        }
        HttpUrl.Builder httpUrlBuilder = HttpUrl.parse(url).newBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            httpUrlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        return httpUrlBuilder.build();
    }

    /**
     * 通过URL 和查询参数构造HttpUrl
     *
     * @param url    URL
     * @param params 查询参数
     * @return HttpUrl
     */
    private static HttpUrl buildUrl(String url, Map<String, String> params, String pathVariableName, String pathVariableValue) {
        String newUrl = StringUtils.replace(url, pathVariableName, pathVariableValue);
        if (Objects.isNull(params)) {
            return HttpUrl.parse(newUrl);
        }
        HttpUrl.Builder httpUrlBuilder = HttpUrl.parse(newUrl).newBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            httpUrlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        return httpUrlBuilder.build();
    }


    /**
     * todo: 待测试
     * 构建POST请求媒体类型（MediaType）为"application/json; charset=utf-8"的请求体
     *
     * @param json json字符串
     * @return RequestBody 请求体
     */
    private static RequestBody buildApplicationJsonBody(String json) {
        return RequestBody.create(MediaTypeConstant.APPLICATION_JSON, json);
    }


    /**
     * 构建POST表单的媒体类型（MediaType）为"application/x-www-form-urlencoded"的请求体
     *
     * @param formParam 表单请求体的key和value组成的Map。
     * @return RequestBody
     */
    public static RequestBody buildFormBody(Map<String, String> formParam) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : formParam.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    /**
     * // todo 待测试
     * 构建post表单的媒体类型（MediaType）为"multipart/form-data"的请求体。
     *
     * @param formParam 表单请求体的key和value组成的Map
     * @return RequestBody
     */
    public static RequestBody buildMultipartBody(Map<String, String> formParam) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        for (Map.Entry<String, String> entry : formParam.entrySet()) {
            builder.addFormDataPart(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }


    private static ResponseResult executeRequest(OkHttpClient okHttpClient, Request request) {
//        HttpUrl url = request.url();
//        String method = request.method();
//        String headers = request.headers().toString();
//        RequestBody requestBody = request.body();
//        String bodyMediaType = null;
//        if (!Objects.isNull(requestBody)) {
//            MediaType mediaType = request.body().contentType();
//            bodyMediaType = mediaType.toString();
//        }
        try (Response response = okHttpClient.newCall(request).execute()) {

//            if (!response.isSuccessful()) {
////                直接获取请求体中的内容，API没有提供，所以需要在调用之前打印请求体。
//                log.error("OkHttpRequestUtils isSuccessful error  url:{}, method:{}, headers:{}, bodyMediaType:{}", url.url().getHost(), method, headers, bodyMediaType);
//                return null;
//            }
            MediaType mediaType = null;
            String bodyString = null;
            if (response.body() != null) {
                mediaType = response.body().contentType();
                bodyString = response.body().string();
            }

            return ResponseResult.builder()
                    .request(response.request())
                    .protocol(response.protocol())
                    .code(response.code())
                    .message(response.message())
                    .handshake(response.handshake())
                    .headers(response.headers())
                    .bodyMediaType(mediaType)
                    .responseBodyString(bodyString)
                    .sentRequestAtMillis(response.sentRequestAtMillis())
                    .receivedResponseAtMillis(response.receivedResponseAtMillis())
                    .build();
        } catch (Exception e) {
            log.error("OkHttpRequestUtils  executeRequest Exception ", e);
            return null;
        }
    }


}
