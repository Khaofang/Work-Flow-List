package com.example.chayanin.workflowlist;

/**
 * Created by Chayanin on 2017-06-01.
 */

public class ViewWorkPresenter {

    private ViewWorkView view;

    private Work work;

    public ViewWorkPresenter(ViewWorkView view, int index) {
        this.view = view;
        work = WorkRepository.getInstance().getWorks().get(index);

        // TODO: implements information from work to show on components
    }

    public Work getWork() {
        return work;
    }

    // ACT: Calculate to progress bar

}
