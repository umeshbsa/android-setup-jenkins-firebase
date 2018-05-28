package com.app.wiki;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.app.wiki.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mTextView = (TextView) findViewById(R.id.tv_main);
        mTextView.setText("This is Jenkins Setup :\n" +
                BuildConfig.APPLICATION_ID + " :\n" +
                BuildConfig.BUILD_TYPE + " :\n" +
                BuildConfig.FLAVOR + " :\n" +
                BuildConfig.VERSION_NAME + " :\n" +
                BuildConfig.VERSION_CODE+":"+

                getString(R.string.app_name)
        );
    }
}
