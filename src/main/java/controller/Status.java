package controller;

public class Status {
    private String status;

    Status() {
        status = "in queue";
    }

    public void delivered() {
        status ="delivered";
    }

    public void done() {
        status ="done";
    }

    public String getStatus(){
        return status;
    }
}
