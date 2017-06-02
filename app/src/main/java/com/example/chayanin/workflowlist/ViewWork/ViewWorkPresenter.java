package com.example.chayanin.workflowlist.ViewWork;

import com.example.chayanin.workflowlist.Model.Work;
import com.example.chayanin.workflowlist.Model.WorkRepository;

public class ViewWorkPresenter {

    private ViewWorkView view;

    private Work work;
    private int index;

    public ViewWorkPresenter(ViewWorkView view, int index) {
        this.view = view;
        work = WorkRepository.getInstance().getWorks().get(index);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Work getWork() {
        return work;
    }

    public void removeThisWork() {
        WorkRepository repository = WorkRepository.getInstance();
        repository.getWorks().remove(index);
    }

}
