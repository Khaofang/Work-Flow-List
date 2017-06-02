package com.example.chayanin.workflowlist.Model;

import java.util.ArrayList;
import java.util.List;

public class Work {

    private List<Process> processes;
    private String deadlineDate;
    private String deadlineTime;
    private String topic;

    public Work(String topic, List<Process> processes, String date, String time) {
        this.processes = processes;
        this.topic = topic;
        deadlineDate = date;
        deadlineTime = time;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public String getTopic() {
        return topic;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public void setDeadlineTime(String deadlineTime){
        this.deadlineTime = deadlineTime;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void addNewProcess(Process p) {
        processes.add(p);
    }

    public int getNumProcessesContained() {
        return processes.size();
    }

    public int getNumProcessesFinished() {
        int count = 0;
        for (Process p : processes) {
            if (p.isFinish())
                count++;
        }
        return count;
    }

    public String toString() {
        return topic;
    }

}
