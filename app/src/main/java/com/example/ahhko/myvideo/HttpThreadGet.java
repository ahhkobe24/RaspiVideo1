package com.example.ahhko.myvideo;

/**
 * Created by ahhko on 4/21/2017.
 */


        import android.os.Handler;
        import android.os.Message;

public class HttpThreadGet extends Thread {
    public static final int GET = 1;
    public static final int GETIMG = 2;

    String httpUrl ;
    int type;
    Handler handler;

    HttpThreadGet(String url, int type, Handler handler) {
        this.type = type;
        this.httpUrl = url;
        this.handler = handler;
    }

    public void run() {
        HttpRunner jsonParser = new HttpRunner();
        byte[] imgBuff = null;
        Message msg = new Message();

                imgBuff = jsonParser.makeImgHttpGET(httpUrl);
                msg.what = GET;
                msg.obj = imgBuff;
        handler.sendMessage(msg);
    }
}
