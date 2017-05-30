package com.example.chayanin.workflowlist;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

public class AddWorkActivity extends AppCompatActivity {

    WorkRepository repository;

    boolean successAdd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);

        repository = WorkRepository.getInstance();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        if (successAdd) {
            setResult(RESULT_OK, intent);
        } else {
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }

    public void complete(View view) {
        successAdd = true;
        // TODO: add new work required to repository
    }

    public void goToMainActivity(View view) {
        onBackPressed();
    }
}
