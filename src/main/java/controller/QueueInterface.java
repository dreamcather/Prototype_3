package controller;

import executor.TaskQueue;
import task.TaskExample;

public interface QueueInterface {

    String put(Runnable runnable);

    void run() throws InterruptedException;
}
