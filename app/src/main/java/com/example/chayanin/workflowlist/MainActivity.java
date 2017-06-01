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

    private ListView lv_workList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_workList = (ListView) findViewById(R.id.lv_main_workList);
        setUpListView();

        if (presenter == null)
            presenter = new MainPresenter(this);
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
    public void goToAddWorkActivity(View view) {
        Intent intent = new Intent(MainActivity.this, AddWorkActivity.class);
        startActivityForResult(intent, 1);
    }

    public void setUpListView() {
        ArrayAdapter<Work> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, WorkRepository.getInstance().getWorks());
        lv_workList.setAdapter(adapter);
    }

}
