package com.example.chayanin.workflowlist.ViewWork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chayanin.workflowlist.EditWork.EditWorkActivity;
import com.example.chayanin.workflowlist.Model.Process;
import com.example.chayanin.workflowlist.Model.Work;
import com.example.chayanin.workflowlist.R;

public class ViewWorkActivity extends AppCompatActivity implements ViewWorkView {

    private ViewWorkPresenter presenter;

    private ListView lv_processList;
    private TextView tv_date;
    private TextView tv_percent;
    private TextView tv_time;
    private TextView tv_topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_work);
        int i = getIntent().getIntExtra("index", 0);

        presenter = new ViewWorkPresenter(this, i);

        lv_processList = (ListView) findViewById(R.id.lv_viewWork_processList);
        tv_date = (TextView) findViewById(R.id.tv_viewWork_date);
        tv_percent = (TextView) findViewById(R.id.tv_viewWork_percent);
        tv_time = (TextView) findViewById(R.id.tv_viewWork_time);
        tv_topic = (TextView) findViewById(R.id.tv_viewWork_topic);

        setUpAllComponents();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                setUpAllComponents();
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
    public void goToEditWorkActivity(View view) {
        Intent intent = new Intent(ViewWorkActivity.this, EditWorkActivity.class);
        intent.putExtra("index", presenter.getIndex());
        startActivityForResult(intent, 1);
    }

    @Override
    public void goToMainActivity(View view) {
        onBackPressed();
    }

    @Override
    public void removeThisWork(View view) {
        presenter.removeThisWork();
        goToMainActivity(view);
    }

    @Override
    public void setUpAllComponents() {
        Work w = presenter.getWork();

        tv_topic.setText(w.toString());

        if (w.getNumProcessesContained() == 0)
            tv_percent.setText("-");
        else
            tv_percent.setText(String.format("%.0f", w.getNumProcessesFinished() * 1.0 / w.getNumProcessesContained()) + "%");

        tv_date.setText(w.getDeadlineDate());
        tv_time.setText(w.getDeadlineTime());

        ArrayAdapter<Process> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, w.getProcesses());
        lv_processList.setAdapter(adapter);
    }

}
