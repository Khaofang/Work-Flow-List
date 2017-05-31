package com.example.chayanin.workflowlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddWorkActivity extends AppCompatActivity implements AddWorkView {

    private AddWorkPresenter presenter;

    private WorkRepository repository;
    private boolean successAdd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);

        if (presenter == null) {
            repository = WorkRepository.getInstance();
            presenter = new AddWorkPresenter(repository, this);
        }
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
        EditText et_topic = (EditText) findViewById(R.id.et_addWork_topic);
        String topic = et_topic.getText().toString();

        // TODO: give AddWorkPresenter add new work required to repository

        goToMainActivity(view);
    }

    public void goToMainActivity(View view) {
        onBackPressed();
    }
}
