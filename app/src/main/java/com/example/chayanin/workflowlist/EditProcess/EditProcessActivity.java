package com.example.chayanin.workflowlist.EditProcess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chayanin.workflowlist.R;

public class EditProcessActivity extends AppCompatActivity implements EditProcessView {

    EditProcessPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_process);
        int wi = getIntent().getIntExtra("work_index", 0);
        int pi = getIntent().getIntExtra("process_index", 0);

        presenter = new EditProcessPresenter(this, wi, pi);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    


}
