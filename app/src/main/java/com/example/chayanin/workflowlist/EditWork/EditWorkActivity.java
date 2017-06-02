package com.example.chayanin.workflowlist.EditWork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.chayanin.workflowlist.Model.Process;
import com.example.chayanin.workflowlist.R;

import java.util.List;

public class EditWorkActivity extends AppCompatActivity implements EditWorkView {

    private EditWorkPresenter presenter;

    private EditText et_day;
    private EditText et_hour;
    private EditText et_minute;
    private EditText et_month;
    private EditText et_process;
    private EditText et_topic;
    private EditText et_year;
    private ListView lv_processList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_work);
        int i = getIntent().getIntExtra("index", 0);

        presenter = new EditWorkPresenter(this, i);

        et_day = (EditText) findViewById(R.id.et_editWork_day);
        et_hour = (EditText) findViewById(R.id.et_editWork_hour);
        et_minute = (EditText) findViewById(R.id.et_editWork_minute);
        et_month = (EditText) findViewById(R.id.et_editWork_month);
        et_process = (EditText) findViewById(R.id.et_editWork_process);
        et_topic = (EditText) findViewById(R.id.et_editWork_topic);
        et_year = (EditText) findViewById(R.id.et_editWork_year);
        lv_processList = (ListView) findViewById(R.id.lv_editWork_processList);

        setUpAllComponents();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    public void addNewProcess() {
        String process = et_process.getText().toString();
        Process p = new Process(process);
        presenter.addNewProcess(p);
    }

    public void goToEditProcessActivity(int index) {

    }

    public void goToViewWorkActivity(View view) {
        onBackPressed();
    }

    public void modifyWork(View view) {
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
                presenter.updateData(topic, deadlineDate, deadlineTime);
            } else {
            }

            goToViewWorkActivity(view);
        } catch(Exception e) {
        }
    }

    public void setUpAllComponents() {
        ArrayAdapter<Process> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, presenter.getNewProcesses());
        String[] date = presenter.getWork().getDeadlineDate().split("/");
        String[] time = presenter.getWork().getDeadlineTime().split(":");
        et_topic.setText(presenter.getWork().getTopic());
        et_day.setText(date[0]);
        et_month.setText(date[1]);
        et_year.setText(date[2]);
        et_hour.setText(time[0]);
        et_minute.setText(time[1]);
        lv_processList.setAdapter(adapter);
    }

}
