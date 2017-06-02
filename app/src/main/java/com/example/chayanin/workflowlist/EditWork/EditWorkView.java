package com.example.chayanin.workflowlist.EditWork;

import android.view.View;

public interface EditWorkView {

    void addNewProcessInChanging(View view);
    void goToEditProcessActivity(int index);
    void goToViewWorkActivity(View view);
    void modifyWork(View view);
    void setUpAllComponents();
    void setUpListView();

}
