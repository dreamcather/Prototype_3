package executor;

import controller.MemoryArchive;
import controller.QueueInterface;
import javafx.util.Pair;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue extends Thread implements QueueInterface {
    private LinkedBlockingQueue<Runnable> messageQueue;
    private MemoryArchive archive;

    public TaskQueue(MemoryArchive archive) {
        messageQueue = new LinkedBlockingQueue<>();
        this.archive = archive;
    }

    @Override
    public String put(Runnable expression) {
        String id = archive.addRecord();
        try {
            messageQueue.put(() -> {
                archive.changeStatus(id, "Delivered");
                expression.run();
                archive.changeStatus(id, "Done");
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        while (true) {
            Runnable task = null;
            try {
                task = messageQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CompletableFuture.runAsync(task, executorService);
        }
    }
}
