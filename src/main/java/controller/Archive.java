package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Archive {
    private Map<String, Status> statusMap;

    public Archive() {
        statusMap = new HashMap<>();
    }
    private String getId(){
        return UUID.randomUUID().toString();
    }

    public String addRecord() {
        String id = getId();
        statusMap.put(id, new Status());
        return id;
    }

    public void delivered(String id) {
        statusMap.get(id).delivered();
    }

    public void done(String id) {
        statusMap.get(id).done();
    }

    public String getStatus(String id) {
        try {
            return statusMap.get(id).getStatus();
        } catch (Exception e) {
            return "doesn't exist";
        }
    }
}
