package com.example.chayanin.workflowlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        if (presenter == null)
            presenter = new ViewWorkPresenter(this, i);

        lv_processList = (ListView) findViewById(R.id.lv_viewWork_processList);
        tv_date = (TextView) findViewById(R.id.tv_viewWork_date);
        tv_percent = (TextView) findViewById(R.id.tv_viewWork_percent);
        tv_time = (TextView) findViewById(R.id.tv_viewWork_time);
        tv_topic = (TextView) findViewById(R.id.tv_viewWork_topic);

        setUpAllComponents(presenter.getWork());
    }

    public void setUpAllComponents(Work w) {
        tv_topic.setText(w.toString());

        if (w.getNumProcessesContained() == 0)
            tv_percent.setText("-");
        else
            tv_percent.setText(String.format("%.0f", w.getNumProcessesFinished() * 1.0 / w.getNumProcessesContained()) + "%");

        tv_date.setText(w.getDeadlineDate());
        tv_time.setText(w.getDeadlineTime());

        ArrayAdapter<Process> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, w.getProcesses());
        lv_processList.setAdapter(adapter);


        // TODO:

    }


}
