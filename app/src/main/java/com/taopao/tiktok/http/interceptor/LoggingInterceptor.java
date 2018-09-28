package com.taopao.tiktok.http.interceptor;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @Author： 淘跑
 * @Date: 2018/7/5 11:43
 * @Use：
 */

public class LoggingInterceptor implements Interceptor {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    public Response intercept(Chain chain) throws IOException {

        //--------------------------------------第一种log输出方式------------------------------------//

//                //这个chain里面包含了request和response，所以你要什么都可以从这里拿
//                Request request = chain.request();
//                long t1 = System.nanoTime();//请求发起的时间
//                LogUtils.f("-----------------------------------Start-----------------------------------");
//                LogUtils.f(String.format("发送请求  %s %n%s%n%s",
//                        request.url(), "connection: " + chain.connection(), "headers: " + request.headers()));
//                Response response = chain.proceed(request);
//                long t2 = System.nanoTime();//收到响应的时间
//                //这里不能直接使用response.body().string()的方式输出日志
//                //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
//                //个新的response给应用层处理
//                ResponseBody responseBody = response.peekBody(1024 * 1024);
//                LogUtils.f(String.format("接收响应: %s %n返回JSON: %s%n请求时长: %.1fms%n%s",
//                        response.request().url(),
//                        responseBody.string(),
//                        (t2 - t1) / 1e6d,
//                        response.headers()));
//                LogUtils.f("-----------------------------------End:"+(t2 - t1) / 1e6d+"毫秒--------------------------");
//                LogUtils.f(String.format("%s",responseBody.string()));


        //-------------------------------第二种log输出方式:格式化JSON 狂拽酷炫吊炸天------------------------------------//


        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();
        long t1 = System.nanoTime();//请求发起的时间

        StringBuilder sb = new StringBuilder();
        if (request.body() instanceof FormBody) {
            FormBody body = (FormBody) request.body();

            if (body != null) {
                for (int i = 0; i < body.size(); i++) {
                    if (i == body.size() - 1) {
                        sb.append(body.encodedName(i) + " = " + URLDecoder.decode(body.encodedValue(i), "utf-8"));
                    } else {
                        sb.append(body.encodedName(i) + " = " + URLDecoder.decode(body.encodedValue(i), "utf-8") + ",");
                    }
                }
            }
        }

        f("╔══════════════════════Start: Request═══════════════════════════════════════");
        f(String.format("║ 发送请求:  %s %n",
                URLDecoder.decode(String.valueOf(request.url()), "utf-8")));

        f(String.format("║ %s %n", "请求参数:  " + sb));
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();//收到响应的时间
        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理

        ResponseBody responseBody = response.peekBody(1024 * 1024);
        String json = responseBody.string();
        f(String.format("║ 请求时长: %.1fms%n", (t2 - t1) / 1e6d));
        f(String.format("║ 返回JSON: %s%n", json));
        logFormatJson(json, "");
        f("╚══════════════════════End:" + (t2 - t1) / 1e6d + "毫秒═════════════════════════════════");
        f(String.format("%s", responseBody.string()));

        return response;
    }


    /**
     * 以级别为 f 的形式输出LOG
     */
    public static void f(String msg) {
        Log.e("LoggingInterceptor->Log", msg);
    }

    /**
     * log拦截器打印log 格式化json字符串 并打印在控制台
     *
     * @param msg        json字符串
     * @param headString 请求头
     */
    public static void logFormatJson(String msg, String headString) {
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        f("║ \n");
        f("║══════════════════════════════Response════════════════════════════════");

        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            f("║ " + line);
        }
    }

}



