package com.example.chayanin.workflowlist.EditProcess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.chayanin.workflowlist.R;

public class EditProcessActivity extends AppCompatActivity implements EditProcessView {

    EditProcessPresenter presenter;

    private EditText et_process;
    private Switch sw_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_process);
        int wi = getIntent().getIntExtra("work_index", 0);
        int pi = getIntent().getIntExtra("process_index", 0);

        presenter = new EditProcessPresenter(this, wi, pi);

        et_process = (EditText) findViewById(R.id.et_editProcess_process);
        et_process.setText(presenter.getProcess().toString());
        sw_finish = (Switch) findViewById(R.id.sw_editProcess_finish);
        sw_finish.setChecked(presenter.getProcess().isFinish());
        sw_finish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.setCurrChecked(isChecked);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    public void goToEditWorkProcess(View view) {
        onBackPressed();
    }

    public void modifyThisProcess(View view) {
        String process = et_process.getText().toString();
        presenter.updateData(process);
        goToEditWorkProcess(view);
    }

    public void removeThisProcess(View view) {
        presenter.removeThisProcess();
    }

}
