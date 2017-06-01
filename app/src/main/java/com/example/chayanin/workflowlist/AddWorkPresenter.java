package com.example.chayanin.workflowlist;

import java.util.List;

public class AddWorkPresenter {

    private AddWorkView view;

    private WorkRepository repository;

    public AddWorkPresenter(AddWorkView view) {
        this.view = view;
        repository = WorkRepository.getInstance();
    }

    public void addNewWork(Work w) {
        repository.addWork(w);
        System.out.println(w.toString());

        List<Work> workList = repository.getWorks();
        for (Work work : workList) {
            System.out.println(work.toString());
        }
    }

}
