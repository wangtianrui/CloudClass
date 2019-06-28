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
    public static void upSource(String courseId, String uperId, String uperName, String type, String path,
                                File source, Callback callback) {
        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("courseId", courseId)
                .addFormDataPart("uperId", uperId)
                .addFormDataPart("type", type)
                .addFormDataPart("sourceName", path)
                .addFormDataPart("uperName", uperName)
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

    /**
     * 获取资源
     *
     * @param data
     * @param callback
     */
    public static void getSource(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "get_source/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 发起签到
     *
     * @param data
     * @param callback
     */
    public static void upSign(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "upSign/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 学生签到
     *
     * @param data
     * @param callback
     */
    public static void studentSign(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "studentSign/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 通过课程ID获得签到
     *
     * @param data
     * @param callback
     */
    public static void getSign(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "getSign/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 获取签到了的学生
     *
     * @param data
     * @param callback
     */
    public static void getSignedStudent(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "getSignedStudent/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 发送通知
     *
     * @param data
     * @return
     */
    public static void upInform(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "create_inform/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 获取通知
     *
     * @param data
     * @return
     */
    public static void getInform(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "get_inform/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }


    /**
     * 获取学生成员
     *
     * @param data
     * @param callback
     */
    public static void getMember(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "getCourseMember/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }


    /**
     * 发起讨论
     *
     * @param data
     * @param callback
     */
    public static void addCommunication(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "create_communication/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 获取已有讨论
     *
     * @param data
     * @param callback
     */
    public static void getCommunication(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "get_communication/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 发送交流的item
     *
     * @param data
     * @param callback
     */
    public static void sendCommunicationItem(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "create_communicationitem/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 获取交流item
     *
     * @param data
     * @param callback
     */
    public static void getCommunicationItem(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "get_communicationitem/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 发布作业
     *
     * @param data
     * @param callback
     */
    public static void addHomeWork(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "add_homework/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 获取作业
     *
     * @param data
     * @param callback
     */
    public static void getHomeWork(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "get_homework/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 添加答案
     *
     * @param homeworkId
     * @param uperId
     * @param content
     * @param path
     * @param source
     * @param callback
     */
    public static void addAnswer(String homeworkId, String uperId, String content, String path,
                                 File source, Callback callback) {
        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("homework_id", homeworkId)
                .addFormDataPart("content", content)
                .addFormDataPart("userId", uperId)
                .addFormDataPart("image",
                        String.valueOf(new Random().nextInt()) + "." + path.split("\\.")[1],
                        RequestBody.create(mediaType, source)
                )
                .build();
        Request request = new Request.Builder().post(requestBody).url(ConstantUtils.webHost +
                "add_answer/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 获取回答
     *
     * @param data
     * @param callback
     */
    public static void getAnswer(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "get_answer/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 获取用户
     *
     * @param data
     * @param callback
     */
    public static void getUser(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "get_user/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 为回答打分
     *
     * @param data
     * @param callback
     */
    public static void scoreAnswer(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "scoreAnswer/").build();
        Call call = mClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 打分
     *
     * @param data
     * @param callback
     */
    public static void addScore(HashMap<String, String> data, Callback callback) {
        Request request = new Request.Builder().post(getBody(data))
                .url(ConstantUtils.webHost + "addScore/").build();
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
