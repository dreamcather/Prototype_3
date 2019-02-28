package executor;

import controller.MemoryArchive;
import javafx.util.Pair;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue {
    private LinkedBlockingQueue<Pair<String,Runnable>> messageQueue;
    private MemoryArchive archive;

    public TaskQueue(MemoryArchive archive) {
        messageQueue = new LinkedBlockingQueue<>();
        this.archive = archive;
    }

    public Pair<String, Runnable> getTask() throws InterruptedException {
        Pair<String,Runnable> task = messageQueue.take();
        Pair<String,Runnable> complexTask = new Pair<>(task.getKey(),()->{
            archive.changeStatus(task.getKey(),"Delivered");
            task.getValue().run();
            archive.changeStatus(task.getKey(),"Done");
        });
        return complexTask;
    }

    public String addTask(Runnable expression) {
        String id =archive.addRecord();
        messageQueue.add(new Pair<>(id,expression));
        return id;
    }
}
