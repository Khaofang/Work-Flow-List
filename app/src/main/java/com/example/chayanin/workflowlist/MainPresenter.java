package com.example.chayanin.workflowlist;

/**
 * Created by Chayanin on 2017-05-30.
 */

public class MainPresenter {

    private MainView view;

    private WorkRepository repository;

    public MainPresenter(WorkRepository repository, MainView view) {
        this.repository = repository;
        this.view = view;
    }

}
