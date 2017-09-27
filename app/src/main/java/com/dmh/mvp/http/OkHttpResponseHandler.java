package com.dmh.mvp.http;

import android.widget.Toast;

import com.dmh.mvp.R;
import com.dmh.mvp.base.App;

import java.net.HttpURLConnection;
import java.util.HashMap;

/**
 * Created by QiuGang on 2017/9/24 18:22
 * Email : 1607868475@qq.com
 */
public abstract class OkHttpResponseHandler<T> implements ResponseHandler<T> {
    private boolean ok = false;
    private HashMap<String, Object> tagMap;
    private Class<T> dataClass;

    public OkHttpResponseHandler(Class<T> dataClass) {
        if (dataClass == null) {
            throw new NullPointerException();
        }
        this.dataClass = dataClass;
        tagMap = new HashMap<>();
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Class<T> getDataClass() {
        return dataClass;
    }

    @Override
    public void setTag(String key, Object tag) {
        tagMap.put(key, tag);
    }

    @Override
    public Object getTag(String key) {
        if (tagMap.containsKey(key)) {
            return tagMap.get(key);
        }
        return null;
    }

    @Override
    public void onFailed(String reqUrl, int errCode, String errMsg) {
        switch (errCode) {
            case -1:
                showMsg(errMsg);
                break;
            case -2:
                showMsg(R.string.toast_error_network_connection_not_available);
                break;
            case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
                showMsg(R.string.toast_error_request_time_out);
                break;
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
            case HttpURLConnection.HTTP_FORBIDDEN:
                showMsg(R.string.toast_error_internal_error);
                break;
            default:
                showMsg(R.string.toast_error_request_error);
                break;
        }
    }

    private void showMsg(String msg) {
        Toast.makeText(App.getApp(), msg, Toast.LENGTH_SHORT).show();
    }

    private void showMsg(int msg) {
        Toast.makeText(App.getApp(), msg, Toast.LENGTH_SHORT).show();
    }
}