package com.example.chayanin.workflowlist.EditWork;

import com.example.chayanin.workflowlist.Model.Process;
import com.example.chayanin.workflowlist.Model.Work;
import com.example.chayanin.workflowlist.Model.WorkRepository;

import java.util.ArrayList;
import java.util.List;

public class EditWorkPresenter {

    private EditWorkView view;

    private List<Process> newProcesses;
    private List<Process> oldProcesses;
    private Work work;
    private int index;

    public EditWorkPresenter(EditWorkView view, int index) {
        this.view = view;
        work = WorkRepository.getInstance().getWorks().get(index);
        this.index = index;
        newProcesses = work.getProcesses();
        oldProcesses = new ArrayList<Process>();
        for (int i = 0; i < newProcesses.size(); i++) {
            Process p = newProcesses.get(i);
            Process pSaved = new Process(p.getDetail(), p.isFinish());
            oldProcesses.add(pSaved);
        }
    }

    public int getIndex() {
        return index;
    }

    public List<Process> getNewProcesses() {
        return newProcesses;
    }

    public List<Process> getOldProcesses() {
        return oldProcesses;
    }

    public Work getWork() {
        return work;
    }

    public void addNewProcess(Process p) {
        newProcesses.add(p);
    }

    public boolean isCorrectDate(int year, int month, int date, int hrs, int min) {
        if (date < 1 || month < 1 || year < 1970 || hrs < 0 || min < 0)
            return false;
        if (hrs > 23)
            return false;
        if (min > 59)
            return false;

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (date > 31)
                return false;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (date > 30)
                return false;
        } else if (month == 2) {
            if (isLeapYear(year)) {
                if (date > 29)
                    return false;
            } else {
                if (date > 28)
                    return false;
            }
        } else if (month > 12)
            return false;

        return true;
    }

    public boolean isLeapYear(int year) {
        if (year % 4 != 0)
            return false;
        if (year % 100 == 0 & year % 400 != 0)
            return false;

        return true;
    }

    public void restoreData() {
        work.setProcesses(oldProcesses);
    }

    public void updateData(String topic, String date, String time) {
        work.setTopic(topic);
        work.setProcesses(newProcesses);
        work.setDeadlineDate(date);
        work.setDeadlineTime(time);
    }

}
