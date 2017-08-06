package com.dmh.mvp.http;

import com.dmh.mvp.BuildConfig;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 14:57
 */
public class RetrofitApi implements Api {
    @Inject
    ApiService apiService;

    @Inject
    public RetrofitApi() {
    }

    public static Interceptor getInterceptor() {
        return LogInterceptor.getInstance();
    }

    static class LogInterceptor implements Interceptor {
        private final static String LINE_SEPARATOR = "\n";
        private final static String TAB = "\t";
        private static volatile LogInterceptor interceptor;

        private LogInterceptor() {}

        public static LogInterceptor getInstance() {
            if (!BuildConfig.DEBUG) {
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
            String requestBody = request.body().toString();

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
            logBuilder.append(requestBody);
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
            return response.newBuilder().body(ResponseBody.create(response.body().contentType(), requestBody)).build();
        }
    }
}
