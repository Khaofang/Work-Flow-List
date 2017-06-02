package com.example.chayanin.workflowlist.Model;

public class Process {

    private boolean finish;
    private String detail;

    public Process(String detail) {
        finish = false;
        this.detail = detail;
    }

    public Process(String detail, boolean finish) {
        this.finish = finish;
        this.detail = detail;
    }

    public boolean isFinish() {
        return finish;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void finish() {
        finish = true;
    }

    public void notFinish() {
        finish = false;
    }

    public String toString() {
        return detail;
    }

}
