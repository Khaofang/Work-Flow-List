package com.example.chayanin.workflowlist;

import java.util.ArrayList;
import java.util.List;

public class WorkRepository {

    private static WorkRepository instance;
    private List<Work> works;

    private WorkRepository() {
        works = new ArrayList<Work>();
    }

    public static WorkRepository getInstance() {
        if (instance == null)
            instance = new WorkRepository();
        return instance;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void addProcess(Work w, Process p) {
        w.addNewProcess(p);
    }

    public void addWork(Work w) {
        works.add(w);
    }

}
