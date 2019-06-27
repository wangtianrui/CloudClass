package com.scorpiomiku.cloudclass.utils;

import com.scorpiomiku.cloudclass.bean.User;

import java.io.File;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by ScorpioMiku on 2019/6/24.
 */

public class WebUtils {
    private static WebUtils instance = new WebUtils();
    private static OkHttpClient mClient = new OkHttpClient();

    private WebUtils() {
    }

    public static WebUtils getInstance() {
        return instance;
    }

    /**
     * 注册
     *
     * @param data
     * @param callback
     */
    public static void rigester(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "register/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 登录
     *
     * @param data
     * @param callback
     * @return
     */
    public static void login(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "login/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 添加个人信息
     *
     * @param data
     * @param callback
     */
    public static void addInformation(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "complete_information/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 创建课堂
     *
     * @param data
     * @param callback
     */
    public static void createCourse(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "create_course/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }


    /**
     * 拉取课程
     *
     * @param data
     * @param callback
     */
    public static void getCourseList(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "get_course/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 加入课堂（老师创建课堂时调用）
     *
     * @param data
     * @param callback
     */
    public static void joinCourse(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "joinCourse/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 学生加入课堂
     *
     * @param data
     * @param callback
     */
    public static void joinCourseByInvited(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "join_course/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 上传文件
     *
     * @param courseId
     * @param uperId
     * @param type
     * @param path
     * @param source
     * @param callback
     */
    public static void upSource(String courseId, String uperId, String type, String path,
                                File source, Callback callback) {
        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("courseId", courseId)
                .addFormDataPart("uperId", uperId)
                .addFormDataPart("type", type)
                .addFormDataPart("sourceName", path)
                .addFormDataPart("source",
                        String.valueOf(new Random().nextInt()) + "." + path.split("\\.")[1],
                        RequestBody.create(mediaType, source)
                )
                .build();
        Request request = new Request.Builder().post(requestBody).url(ConstantUtils.webHost +
                "add_source/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    public static FormBody getBody(HashMap<String, String> data) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        Set<String> keys = data.keySet();
        for (String key : keys) {
            bodyBuilder.add(key, data.get(key));
        }
        return bodyBuilder.build();
    }
}
