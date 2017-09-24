package com.dmh.mvp.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.ArrayMap;

import com.dmh.mvp.BuildConfig;
import com.dmh.mvp.base.App;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 14:57
 */
public class OkHttpApi implements Api {
    private static final boolean DEBUG_MODE = BuildConfig.DEBUG;
    public final String TAG_CANCEL_ALL_REQUEST = "cancelAllRequest";

    private final long TIME_OUT = 60L;

    private final OkHttpClient requestClient;
    private final Handler resultCallbackHandler;
    private final Executor executor;
    private final Gson jsonParse;

    @Inject
    public OkHttpApi() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        if (DEBUG_MODE) {
            builder.addInterceptor(LogInterceptor.getInstance());
        }
        requestClient = builder.build();

        resultCallbackHandler = new Handler(Looper.getMainLooper());
        // 来自OkHttp#ConnectionPool
        executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new
                SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread result = new Thread(runnable, "OkHttpApi ConnectionPool");
                result.setDaemon(true);
                return result;
            }
        });

        jsonParse = new Gson();
    }

    private <T> void get(String url, Object tag, ResponseHandler<T> handler) {
        get(url, tag, null, handler);
    }

    private <T> void get(String url, Object tag, ArrayMap<String, String> params, ResponseHandler<T> handler) {
        url = checkUrlPrefix(url);
        url = addParamsToUrl(url, params);
        Request.Builder requestBuilder = new Request.Builder().url(url).get();
        if (tag != null) {
            if (tag instanceof ArrayMap) {
                throw new RuntimeException();
            }
            requestBuilder.tag(tag.getClass().getName());
        }
        executeRequest(requestBuilder.build(), handler);
    }

    private String addParamsToUrl(String url, ArrayMap<String, String> params) {
        if (params == null || params.size() <= 0) {
            return url;
        }
        StringBuilder paramsBuilder = new StringBuilder(url);
        paramsBuilder.append("?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            paramsBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return paramsBuilder.substring(0, paramsBuilder.length() - 1);
    }

    private String checkUrlPrefix(String url) {
        if (url == null) {
            return API_HOST;
        }
        if (!url.startsWith("http")) {
            if (url.startsWith("/")) {
                url = url.substring(1);
            }
            url = API_HOST + url;
        }
        return url;
    }

    public void cancelRequest(Object tag) {
        if (tag == null) {
            return;
        }
        Dispatcher dispatcher = requestClient.dispatcher();
        if (TAG_CANCEL_ALL_REQUEST.equals(tag.toString())) {
            dispatcher.cancelAll();
            return;
        }
        String tagName = tag.getClass().getName();
        for (int i = dispatcher.runningCalls().size() - 1; i >= 0; i--) {
            Call call = dispatcher.runningCalls().get(i);
            if (tagName.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (int i = dispatcher.queuedCalls().size() - 1; i >= 0; i--) {
            Call call = dispatcher.queuedCalls().get(i);
            if (tagName.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    private <T> void executeRequest(Request request, ResponseHandler<T> responseHandler) {
        if (request == null || responseHandler == null) {
            throw new NullPointerException();
        }
        if (!networkAvailable()) {
            responseHandler.onFailed(request.url().toString(), -2, "The network connection is not available");
            return;
        }
        Call call = requestClient.newCall(request);
        executeCall(call, responseHandler);
    }

    private <T> void executeCall(final Call call, final ResponseHandler<T> responseHandler) {
        final Request request = call.request();
        final String reqUrl = request.url().toString();
        responseHandler.onStart();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = call.execute();
                    if (response == null) {
                        resultCallbackHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                responseHandler.onFailed(reqUrl, -1, "Response Null");
                                responseHandler.onComplete();
                            }
                        });
                    } else {
                        if (response.isSuccessful()) {
                            final int responseCode = response.code();
                            String bodyContent = response.body().string();
                            responseHandler.setOk(true);
                            final ApiResponse apiResponse = jsonParse.fromJson(bodyContent, ApiResponse.class);
                            final T data = jsonParse.fromJson(apiResponse.getData(), responseHandler.getDataClass());
                            resultCallbackHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    responseHandler.onSuccess(responseCode, apiResponse, data);
                                    responseHandler.onComplete();
                                }
                            });
                        } else {
                            final int errCode = response.code();
                            final String errMsg = response.message();
                            resultCallbackHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    responseHandler.onFailed(reqUrl, errCode, errMsg);
                                    responseHandler.onComplete();
                                }
                            });
                        }
                    }
                } catch (IOException e) {
                    if (DEBUG_MODE) {
                        e.printStackTrace();
                    }
                    final String errMsg = e.getMessage();
                    resultCallbackHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            responseHandler.onFailed(reqUrl, -1, errMsg);
                            responseHandler.onComplete();
                        }
                    });
                } finally {
                    if (response != null) {
                        try {
                            response.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private static ConnectivityManager cm = null;

    private static final boolean networkAvailable() {
        if (cm == null) {
            cm = (ConnectivityManager) App.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        return cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable();
    }

    static class LogInterceptor implements Interceptor {
        private final static String LINE_SEPARATOR = "\n";
        private final static String TAB = "\t";
        private static volatile LogInterceptor interceptor;

        private LogInterceptor() {}

        public static LogInterceptor getInstance() {
            if (!DEBUG_MODE) {
                return null;
            }
            if (interceptor == null) {
                synchronized (LogInterceptor.class) {
                    if (interceptor == null) {
                        interceptor = new LogInterceptor();
                    }
                }
            }
            return interceptor;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);

            String requestName = Request.class.getSimpleName();
            String requestUrl = request.url().toString();
            String requestMethod = request.body().toString();

            String responseName = Response.class.getSimpleName();
            int responseCode = response.code();
            String responseMessage = response.message();
            String responseBody = response.body().string();

            StringBuilder logBuilder = new StringBuilder();
            logBuilder.append(requestName);
            logBuilder.append(LINE_SEPARATOR);
            logBuilder.append(TAB);
            logBuilder.append(requestUrl);
            logBuilder.append(LINE_SEPARATOR);
            logBuilder.append(TAB);
            logBuilder.append(requestMethod);
            logBuilder.append(LINE_SEPARATOR);

            logBuilder.append(responseName);
            logBuilder.append(LINE_SEPARATOR);
            logBuilder.append(TAB);
            logBuilder.append("code=").append(responseCode);
            logBuilder.append(TAB);
            logBuilder.append("message=").append(responseMessage);
            logBuilder.append(LINE_SEPARATOR);
            logBuilder.append(TAB);
            logBuilder.append("body=").append(responseBody);
            return response.newBuilder().body(ResponseBody.create(response.body().contentType(), requestMethod))
                    .build();
        }
    }
}