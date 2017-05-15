package com.example.ahhko.myvideo;


import android.annotation.SuppressLint;
import android.app.Activity;;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends Activity {

    private int SCREEN_WIDTH, SCREEN_HEIGHT;
    Button  btnSubmit;
    EditText etURL;
    String result = null;
    String TAG = "HttpPort";

    HttpThreadGet myHttpThreadGet;
    static Handler handler;
    private int index;
    private LinearLayout mlayout;

    @SuppressLint("HandlerLeak") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        Log.d(TAG, SCREEN_WIDTH + ":" + SCREEN_HEIGHT);

        setContentView(R.layout.activity_main);
        setupView();


        btnSubmit = (Button) findViewById(R.id.button_submit);
        etURL = (EditText) findViewById(R.id.editText_url);


        btnSubmit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (etURL.getText().toString().isEmpty()) {
                    etURL.setError("Fill the URL");
                    return;
                }
                    doGet();

            }
        });






        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                        byte[] buffer = (byte[])msg.obj;
                        try {
                            BitmapFactory.Options BitmapFactoryinfo = new BitmapFactory.Options();
                            BitmapFactoryinfo.inPreferredConfig = Bitmap.Config.RGB_565;
                            Bitmap image = BitmapFactory.decodeByteArray(buffer, 0, buffer.length, BitmapFactoryinfo);
                            ImageView imageView = (ImageView) findViewById(R.id.imageview2);
                            imageView.setImageBitmap(image);
                        } catch (Exception e) {
                            Log.e(TAG, e.toString());
                        }
                        doGet();


            }
        };

    }

    private void setupView() {
        this.index = 0;
        mlayout = (LinearLayout) findViewById(R.id.linelayout_list);
        mlayout.setOrientation(LinearLayout.VERTICAL);
    }






        private void doGet() {

            String url = etURL.getText().toString();
            myHttpThreadGet = new HttpThreadGet(url, HttpThreadGet.GETIMG, handler);
            myHttpThreadGet.start();
        }





    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
