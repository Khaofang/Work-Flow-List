package com.example.chayanin.workflowlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddWorkActivity extends AppCompatActivity implements AddWorkView {

    private AddWorkPresenter presenter;

    private ListView lv_processList;

    private List<Process> processes;
    private boolean successAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);

        lv_processList = (ListView) findViewById(R.id.lv_addWork_processList);
        processes = new ArrayList<Process>();
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

    public void addNewProcess(View view) {
        EditText et_process = (EditText) findViewById(R.id.et_addWork_process);
        String process = et_process.getText().toString();
        Process p = new Process(process);
        if (process.length() > 0) {
            processes.add(p);
            setUpListView();
        }
    }

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

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DATE, date);
            cal.set(Calendar.HOUR, hrs);
            cal.set(Calendar.MINUTE, min);
            Date d = cal.getTime();

            Work w = new Work(topic, processes, d);
            presenter.addNewWork(w);
        } catch(Exception e) {
            // TODO: alert that wrong filling in
        }

        goToMainActivity(view);
    }

    public void goToMainActivity(View view) {
        onBackPressed();
    }

    public void setUpListView() {
        ArrayAdapter<Process> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, processes);
        lv_processList.setAdapter(adapter);
    }
}
