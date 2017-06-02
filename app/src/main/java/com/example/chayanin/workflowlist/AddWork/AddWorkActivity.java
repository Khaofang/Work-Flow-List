package com.example.chayanin.workflowlist.AddWork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.chayanin.workflowlist.Model.Process;
import com.example.chayanin.workflowlist.R;
import com.example.chayanin.workflowlist.Model.Work;

public class AddWorkActivity extends AppCompatActivity implements AddWorkView {

    private AddWorkPresenter presenter;

    private ListView lv_processList;

    private boolean successAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);

        lv_processList = (ListView) findViewById(R.id.lv_addWork_processList);
        successAdd = false;

        if (presenter == null)
            presenter = new AddWorkPresenter(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void addNewProcess(View view) {
        EditText et_process = (EditText) findViewById(R.id.et_addWork_process);
        String process = et_process.getText().toString();
        Process p = new Process(process);
        if (process.length() > 0) {
            presenter.addNewProcess(p);
            setUpListView();
            et_process.setText("");
        }
    }

    @Override
    public void complete(View view) {
        successAdd = true;
        EditText et_topic = (EditText) findViewById(R.id.et_addWork_topic);
        EditText et_day = (EditText) findViewById(R.id.et_addWork_day);
        EditText et_month = (EditText) findViewById(R.id.et_addWork_month);
        EditText et_year = (EditText) findViewById(R.id.et_addWork_year);
        EditText et_hour = (EditText) findViewById(R.id.et_addWork_hour);
        EditText et_minute = (EditText) findViewById(R.id.et_addWork_minute);

        try {
            String topic = et_topic.getText().toString();
            int date = Integer.parseInt(et_day.getText().toString());
            int month = Integer.parseInt(et_month.getText().toString());
            int year = Integer.parseInt(et_year.getText().toString());
            int hrs = Integer.parseInt(et_hour.getText().toString());
            int min = Integer.parseInt(et_minute.getText().toString());

            if (presenter.isCorrectDate(year, month, date, hrs, min) && topic.length() > 0) {
                String deadlineDate = String.format("%02d/%02d/%04d", date, month, year);
                String deadlineTime = String.format("%02d:%02d", hrs, min);
                Work w = new Work(topic, presenter.getProcesses(), deadlineDate, deadlineTime);
                presenter.addNewWork(w);
            } else {
                successAdd = false;
            }

            goToMainActivity(view);
        } catch(Exception e) {
            successAdd = false;
        }
    }

    @Override
    public void goToMainActivity(View view) {
        if (successAdd)
            onBackPressed();
        else {
            System.out.println("Not complete or wrong filling in");
            // TODO: alert that wrong filling in
        }
    }

    @Override
    public void setUpListView() {
        ArrayAdapter<Process> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, presenter.getProcesses());
        lv_processList.setAdapter(adapter);
    }
}
