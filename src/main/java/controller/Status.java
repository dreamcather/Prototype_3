package controller;


public class Status {
    private String status;

    Status() {
        status = "InQueue";
    }

    public void change(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
