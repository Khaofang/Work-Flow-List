package com.example.chayanin.workflowlist;

import java.util.ArrayList;
import java.util.List;

public class WorkRepository {

    private static WorkRepository instance;
    private List<Work> works;

    private WorkRepository() {
        works = new ArrayList<Work>();

        Process p1_1 = new Process("Process 1.1");
        Process p1_2 = new Process("Process 1.1");
        Process p2_1 = new Process("Process 2.1");
        Work w1 = new Work("Work 1");
        Work w2 = new Work("Work 2");
        Work w3 = new Work("Work 3");
        w1.addNewProcess(p1_1);
        w1.addNewProcess(p1_2);
        w2.addNewProcess(p2_1);
        addWork(w1);
        addWork(w2);
        addWork(w3);
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
