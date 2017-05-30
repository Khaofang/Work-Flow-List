package com.example.chayanin.workflowlist;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;

    private WorkRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = WorkRepository.getInstance();

        ListView lv_workList = (ListView) findViewById(R.id.lv_workList);
        // TODO: continue SET UP list view

        FloatingActionButton fab_add = (FloatingActionButton) findViewById(R.id.fab_add_main);
    }

    @Override
    public void goToAddWorkActivity(View view) {
        System.out.println("Click!");
    }
}
