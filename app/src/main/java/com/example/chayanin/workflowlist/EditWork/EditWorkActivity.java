package com.example.chayanin.workflowlist.EditWork;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.chayanin.workflowlist.EditProcess.EditProcessActivity;
import com.example.chayanin.workflowlist.Model.Process;
import com.example.chayanin.workflowlist.R;

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                setUpListView();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void addNewProcessInChanging(View view) {
        String process = et_process.getText().toString();
        Process p = new Process(process);
        presenter.addNewProcess(p);
        setUpListView();
        et_process.setText("");
    }

    @Override
    public void cancel(View view) {
        presenter.restoreData();
        goToViewWorkActivity(view);
    }

    @Override
    public void goToEditProcessActivity(int index) {
        Intent intent = new Intent(EditWorkActivity.this, EditProcessActivity.class);
        intent.putExtra("process_index", index);
        startActivityForResult(intent, 1);
    }

    @Override
    public void goToViewWorkActivity(View view) {
        onBackPressed();
    }

    @Override
    public void modifyWork(View view) {
        boolean correct = false;
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
                correct = true;
            }
        } catch(Exception e) {
            correct = false;
        }

        if (correct) {
            goToViewWorkActivity(view);
        } else {
            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setMessage("No topic or incorrect date/time.");

            ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });

            AlertDialog alert = ab.create();
            alert.show();
        }
    }

    @Override
    public void setUpAllComponents() {
        String[] date = presenter.getWork().getDeadlineDate().split("/");
        String[] time = presenter.getWork().getDeadlineTime().split(":");
        et_topic.setText(presenter.getWork().getTopic());
        et_day.setText(date[0]);
        et_month.setText(date[1]);
        et_year.setText(date[2]);
        et_hour.setText(time[0]);
        et_minute.setText(time[1]);
        setUpListView();
    }

    @Override
    public void setUpListView() {
        ArrayAdapter<Process> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, presenter.getNewProcesses());
        lv_processList.setAdapter(adapter);
        lv_processList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToEditProcessActivity(position);
            }
        });
    }

}
