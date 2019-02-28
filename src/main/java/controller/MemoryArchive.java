package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MemoryArchive implements Archive {
    private Map<String, Status> statusMap;

    public MemoryArchive() {
        statusMap = new HashMap<>();
    }

    private String getId() {
        return UUID.randomUUID().toString();
    }

    public String addRecord() {
        String id = getId();
        statusMap.put(id, new Status());
        return id;
    }

    public String getStatus(String id) {
        try {
            return statusMap.get(id).getStatus();
        } catch (Exception e) {
            return "doesn't exist";
        }
    }

    @Override
    public void changeStatus(String id,String status) {
        statusMap.get(id).change();
    }
}
