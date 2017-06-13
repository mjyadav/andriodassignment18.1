package com.example.mrityunjay.androidassignment181;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);



    IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

    registerReceiver(broadcastReceiver,intentFilter);
}


    BroadcastReceiver broadcastReceiver = new BroadCastReciever() {
        public void onReceive(Context context, Intent intent) {
            context.unregisterReceiver(this);
            int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int level = -1;
            if (currentLevel >= 0 && scale > 0) {
                level = (currentLevel * 100) / scale;
            }
            text.setText("Battery Level remaining :" + level + "%");
            progressBar.setProgress(level);
        }
    };
}

