package com.example.chayanin.workflowlist.Main;

import com.example.chayanin.workflowlist.Model.WorkRepository;

public class MainPresenter {

    private MainView view;

    private WorkRepository repository;

    public MainPresenter(MainView view) {
        this.view = view;
        repository = WorkRepository.getInstance();
    }


}
