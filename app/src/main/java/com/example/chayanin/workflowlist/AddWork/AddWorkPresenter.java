package com.example.chayanin.workflowlist.AddWork;

import com.example.chayanin.workflowlist.Model.Process;
import com.example.chayanin.workflowlist.Model.Work;
import com.example.chayanin.workflowlist.Model.WorkRepository;

import java.util.ArrayList;
import java.util.List;

public class AddWorkPresenter {

    private AddWorkView view;

    private List<Process> processes;
    private WorkRepository repository;

    public AddWorkPresenter(AddWorkView view) {
        this.view = view;
        processes = new ArrayList<Process>();
        repository = WorkRepository.getInstance();
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void addNewProcess(Process p) {
        processes.add(p);
    }

    public void addNewWork(Work w) {
        repository.addWork(w);
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

}
