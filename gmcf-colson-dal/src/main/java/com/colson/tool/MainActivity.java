//package com.colson.tool;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import com.colson.tool.TextClock;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//
//public class MainActivity extends AppCompatActivity{
//    private TextClock mTextClock;
//    private Timer mTimer;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mTextClock = (TextClock)findViewById(R.id.mTextClock);
//
//        mTimer = new Timer();
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.e("MainActivity.java(run)", "行数: 32  走");
//                        mTextClock.doInvalidate();
//                    }
//                });
//
//            }
//        };
//        mTimer.schedule(task,1000,1000);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mTimer.cancel();
//    }
//}
