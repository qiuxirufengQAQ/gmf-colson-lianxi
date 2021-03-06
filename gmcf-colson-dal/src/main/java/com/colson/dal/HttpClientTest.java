package com.colson.dal;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

public class HttpClientTest {
    private static void doGet() {
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod("https://www.thepaper.cn/");
        int code = 0;
        try {
            code = client.executeMethod(getMethod);
            if (code == 200) {
                String res = getMethod.getResponseBodyAsString();
                System.out.println(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doPost() {
        String praiseUrl = "https://www.thepaper.cn/www/commentPraise.msp"; // 澎湃新闻评论点赞url
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(praiseUrl);
        // 必须设置下面这个Header
        postMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        postMethod.addParameter("commentId", "18718372"); // 评论的id，抓包获得
        try {
            int code = client.executeMethod(postMethod);
            if (code == 200) {
                String res = postMethod.getResponseBodyAsString();
                System.out.println(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        doGet();
//        System.out.println("-----------分割线------------");
//        System.out.println("-----------分割线------------");
//        System.out.println("-----------分割线------------");
//        doPost();
        baseTest();
    }
    private static void baseTest(){
        //创建client
        HttpClient httpClient = new HttpClient();
        //创建getMethod
        HttpMethod method = new GetMethod("https://www.baidu.com/");
        //post请求创建postMethod
//        HttpMethod postMethod = new PostMethod();

        try {
            httpClient.executeMethod(method);

            System.out.println(method.getStatusLine());
            System.out.println(method.getResponseBodyAsString());

            //记得关闭连接
            method.releaseConnection();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    private static void paramsTest(){
        HttpClient client = new HttpClient();
        //设置超时时长
        client.getParams().setConnectionManagerTimeout(5000);
        client.getParams().setContentCharset("utf-8");
    }
}
