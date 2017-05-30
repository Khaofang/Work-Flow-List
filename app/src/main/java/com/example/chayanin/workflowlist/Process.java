package com.example.chayanin.workflowlist;

/**
 * Created by Chayanin on 2017-05-29.
 */

public class Process {

    private boolean finish;
    private String detail;

    public Process(String detail) {
        finish = false;
        this.detail = detail;
    }

    public boolean isFinish() {
        return finish;
    }

    public String getDetail() {
        return detail;
    }

    public void finish() {
        finish = true;
    }

    public String toString() {
        return detail;
    }

}
