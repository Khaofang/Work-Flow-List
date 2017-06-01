package com.example.chayanin.workflowlist;

/**
 * Created by Chayanin on 2017-05-30.
 */

public class MainPresenter {

    private MainView view;

    private WorkRepository repository;

    public MainPresenter(MainView view) {
        this.view = view;
        repository = WorkRepository.getInstance();
    }


}
