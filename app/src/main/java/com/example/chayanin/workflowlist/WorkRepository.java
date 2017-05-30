package com.example.chayanin.workflowlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chayanin on 2017-05-30.
 */

public class WorkRepository {

    private static WorkRepository instance;
    private List<Work> works;

    private WorkRepository() {
        works = new ArrayList<Work>();

        addWork("Work 1");
        addWork("Work 2");
        addWork("Work 3");
    }

    public static WorkRepository getInstance() {
        if (instance == null)
            instance = new WorkRepository();
        return instance;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void addWork(String s) {
        Work w = new Work(s);
        works.add(w);
    }

}
