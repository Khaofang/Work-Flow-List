package com.example.chayanin.workflowlist;

/**
 * Created by Chayanin on 2017-06-01.
 */

public class ViewWorkPresenter {

    private ViewWorkView view;

    private Work work;

    public ViewWorkPresenter(int index) {
        work = WorkRepository.getInstance().getWorks().get(index);
    }
    
}
