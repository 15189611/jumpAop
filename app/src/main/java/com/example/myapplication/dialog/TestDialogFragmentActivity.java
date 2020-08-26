package com.example.myapplication.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import leakcanary.AppWatcher;

public class TestDialogFragmentActivity extends AppCompatActivity {
    HandlerThread background = new HandlerThread("BackgroundThread");
    private final Handler mHandler = new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        background.start();
        final Handler backgrounders = new Handler(background.getLooper());

        findViewById(R.id.testClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        TestDialogFragmentActivity.this.makeCroissants();
                    }
                };
                backgrounders.post(new Runnable() {
                    @Override public void run() {
                        runOnUiThread(new Runnable() {
                            @Override public void run() {
                                new AlertDialog.Builder(TestDialogFragmentActivity.this) //
                                        .setPositiveButton("Baguette", clickListener) //
                                        .show();
                            }
                        });
                    }
                });
            }
        });



    }

    public void makeCroissants(){
        Log.e("Charles","to do something~~");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppWatcher.INSTANCE.getObjectWatcher().watch(this);
    }
}
