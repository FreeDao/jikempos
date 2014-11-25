package com.jike.aty;

import java.util.Map;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;

public class BaseActivity extends Activity{
	public static final String KEY_FOR_CHECKED_VIEW = "checkView";
    public static final String RESULT_FOR_CHECK_VIEW = "result";
    protected static boolean hasHeadset = false;
    protected static boolean headsetDetect = true;
    protected Map<String, Integer> systemResolution = null;
    //protected BaseApplication app;
    private HeadsetPlugReceiver headsetPlugReceiver;
    private Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerHeadsetPlugReceiver();
      //  app = (BaseApplication) getApplication();
      //  systemResolution = DevicesUtils.getSystemResolution(this);
//        handler = new Handler();
//        app.pushToStack(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Runnable runnable = new TaskInfoRunnable(this);
//        handler.postDelayed(runnable, 500L);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterHeadsetPlugReceiver();
//        app.removeFromStack(this);
//        handler = null;
    }

    protected void updateHeadsetFlags() {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //hasHeadset = audioManager.isMicrophoneMute();
        hasHeadset = audioManager.isWiredHeadsetOn();
    }

    private void registerHeadsetPlugReceiver() {
        headsetPlugReceiver = new HeadsetPlugReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        registerReceiver(headsetPlugReceiver, intentFilter);
    }

    private void unregisterHeadsetPlugReceiver() {
        if (headsetPlugReceiver != null) {
            unregisterReceiver(headsetPlugReceiver);
        }
    }

    public class HeadsetPlugReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("state") && intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = intent.getIntExtra("state", -1);
                String name = intent.getStringExtra("name");
                int microphone = intent.getIntExtra("microphone", -1);
                switch (state) {
                    case 0:
                        headsetDetect = false;
                        break;
                    case 1:
                        headsetDetect = true;
                        break;
                    default:
                        headsetDetect = false;
                        break;
                }
            }

        }
    }
}
