package com.example.chayanin.workflowlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chayanin on 2017-05-29.
 */

public class Work {

    private List<Process> processes;
    private String topic;

    public Work(String topic) {
        processes = new ArrayList<Process>();
        this.topic = topic;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public String getTopic() {
        return topic;
    }

    public void addNewProcess(String s) {
        Process p = new Process(s);
        processes.add(p);
    }

    public int getPercentageComplete() {
        int all = processes.size();
        int countComplete = 0;
        for (int i = 0; i < all; i++) {
            if (processes.get(i).isFinish())
                countComplete++;
        }
        return countComplete * 100 / all;
    }

    public String toString() {
        return topic;
    }

}
