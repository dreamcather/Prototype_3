package controller;

public interface Archive {

    String addRecord();

    String getStatus(String id);

    void changeStatus(String id,String status);

}
