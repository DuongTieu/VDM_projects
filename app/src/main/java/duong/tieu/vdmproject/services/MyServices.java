package duong.tieu.vdmproject.services;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Nhahv on 4/5/2016.
 */
public class MyServices {

    public static final int GET = 1;
    public static final int POST = 2;
    static String response = null;
    private InputStream inputStream;

    public MyServices() {
    }


    public String get(String url, List<NameValuePair> params) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();

            if (params != null) {
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
            }
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);

            if (httpResponse != null) {
                HttpEntity httpEntity = httpResponse.getEntity();
                response = EntityUtils.toString(httpEntity);
//                response = url;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String post(String url, List<NameValuePair> params) {

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);
            if (params != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(params));
            }
            HttpResponse httpResponse = httpClient.execute(httpPost);

            if (httpResponse != null) {
                HttpEntity httpEntity = httpResponse.getEntity();
                response = EntityUtils.toString(httpEntity);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String isLogin(String path, List<NameValuePair> params) {

        return null;
    }
}
