package executor;

import controller.Archive;
import javafx.util.Pair;

public class Executor extends Thread {

    private TaskQueue taskQueue;
    private Archive archive;
    private Pair<String,Runnable> task;

    public Executor(TaskQueue taskQueue, Archive archive) {
        this.taskQueue = taskQueue;
        this.archive = archive;
    }

    public void run() {
        while (true) {
            try {
                task = taskQueue.getTask();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("I do smt with " + task.getKey());
                Thread.sleep(10000);
                try {
                    task.getValue().run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                archive.done(task.getKey());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
