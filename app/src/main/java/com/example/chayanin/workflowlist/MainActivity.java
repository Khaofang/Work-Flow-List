package com.example.chayanin.workflowlist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;

    private WorkRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (presenter == null) {
            repository = WorkRepository.getInstance();
            presenter = new MainPresenter(repository, this);
        }

        ListView lv_workList = (ListView) findViewById(R.id.lv_workList);
        ArrayAdapter<Work> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, repository.getWorks());
        lv_workList.setAdapter(adapter);

        FloatingActionButton fab_add = (FloatingActionButton) findViewById(R.id.fab_add_main);

    }

    @Override
    public void goToAddWorkActivity(View view) {
        System.out.println("Go to Add Work Page");
        Intent intent = new Intent(MainActivity.this, AddWorkActivity.class);
        System.out.println("Create Intent");
        startActivity(intent);
    }

}