package executor;

import controller.Archive;
import javafx.util.Pair;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue {
    private LinkedBlockingQueue<Pair<String,Runnable>> messageQueue;
    private Archive archive;

    public TaskQueue(Archive archive) {
        messageQueue = new LinkedBlockingQueue<>();
        this.archive = archive;
    }

    public Pair<String, Runnable> getTask() throws InterruptedException {
        Pair<String,Runnable> task = messageQueue.take();
        archive.delivered(task.getKey());
        return task;
    }

    public String addTask(Runnable expression) {
        String id =archive.addRecord();
        messageQueue.add(new Pair<>(id,expression));
        return id;
    }
}
