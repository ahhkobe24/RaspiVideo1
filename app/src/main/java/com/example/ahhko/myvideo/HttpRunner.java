package com.example.ahhko.myvideo;

/**
 * Created by ahhko on 4/23/2017.
 */


        import java.io.ByteArrayOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.UnsupportedEncodingException;
        import org.apache.http.HttpEntity;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.HttpResponse;
        import org.apache.http.params.CoreConnectionPNames;


@SuppressWarnings("deprecation")
public class HttpRunner {

    static InputStream is = null;
    static String jsonString = "";

    // constructor
    public HttpRunner() {
    }


    public byte[] makeImgHttpGET(String url) {
        jsonString = "";
        // Making HTTP request
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();

            httpClient.getParams().setParameter(
                    CoreConnectionPNames.CONNECTION_TIMEOUT, 60 * 1000);
            HttpGet httpRequest = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpRequest);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                jsonString = "Status Code:"
                        + httpResponse.getStatusLine().getStatusCode();
                return null;
            }
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            jsonString = "ERROR:" + e.toString();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            jsonString = "ERROR:" + e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            jsonString = "ERROR:" + e.toString();
        }

        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len = -1;
        try {
            while ((len = is.read(buffer)) != -1) {
                outstream.write(buffer, 0, len);
            }
            outstream.close();
            is.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            jsonString = "ERROR:" + e.toString();
        }

        // return JSON String
        return outstream.toByteArray();
    }

}

