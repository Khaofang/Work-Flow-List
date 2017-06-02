package com.example.chayanin.workflowlist.EditProcess;

import com.example.chayanin.workflowlist.Model.Process;
import com.example.chayanin.workflowlist.Model.Work;
import com.example.chayanin.workflowlist.Model.WorkRepository;

public class EditProcessPresenter {

    private EditProcessView view;

    private Process process;
    private Work work;
    private int workIndex;
    private int processIndex;

    public EditProcessPresenter(EditProcessView view, int workIndex, int processIndex) {
        this.view = view;
        work = WorkRepository.getInstance().getWorks().get(workIndex);
        process = work.getProcesses().get(processIndex);
        this.workIndex = workIndex;
        this.processIndex = processIndex;
    }

}
