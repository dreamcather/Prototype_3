package executor;

import javafx.util.Pair;

public class Executor extends Thread {

    private TaskQueue taskQueue;
    private Pair<String, Runnable> task;
    private boolean flag;

    public Executor(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
        flag = true;
    }

    public void run() {
        while (flag) {
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopLoop() {
        flag = false;
    }

    public void runLoop() {
        flag = true;
    }
}
