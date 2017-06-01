package com.example.chayanin.workflowlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewWorkActivity extends AppCompatActivity implements ViewWorkView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_work);
        int index = getIntent().getIntExtra("index", 0);

    }
}
