package com.example.shiwen.handledemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,Runnable{
    private Button upDateBtn, testBtn;
    private ProgressBar myProgressBar;

    private static final String TAG = "MainActivity";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        upDateBtn = (Button) findViewById(R.id.uptate);
        testBtn = (Button) findViewById(R.id.testBtn);
        upDateBtn.setOnClickListener(this);
        testBtn.setOnClickListener(this);
        myProgressBar = (ProgressBar) findViewById(R.id.progress);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uptate:
                Log.d(TAG, "onClick: 更新UI");
                Message message = new Message();
                message.arg1 = 1;
                myhandle.post(myRunble);
                break;
            case R.id.testBtn:
//                myhandle.
                Log.d(TAG, "onClick: 测试按钮点击"+Thread.currentThread());
                break;
        }
    }

    //消息队列
    Handler myhandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            myProgressBar.setProgress(msg.arg1);
            Log.d(TAG, "handleMessage: " + Thread.currentThread());

        }
    };

    ///任务
    Runnable myRunble = new Runnable() {
        public void run() {

            progress += 10;
            Message message = new Message();
            message.arg1 = progress;
//            通过消息队列，把任务添加到安卓系统消息队列中，下次1000ms执行一次run方法
            myhandle.postDelayed(myRunble, 1000);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myhandle.sendMessage(message);
//            Log.d(TAG, "run: "+"new commit");

        }

    };

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
    int progress;
    @Override
    public void run() {

    }
}
