package controller;

import executor.Executor;
import executor.TaskQueue;
import org.springframework.context.annotation.Bean;
import task.TaskExample;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public MemoryArchive getArchive() {
        return new MemoryArchive();
    }

    @Bean
    public TaskQueue getTaskQueue(MemoryArchive archive) {
        return new TaskQueue(archive);
    }

    @Bean
    public Executor getExecutor(MemoryArchive archive, TaskQueue taskQueue) {
        Executor executor = new Executor(taskQueue);
        executor.start();
        return executor;
    }

    @Bean
    public TaskExample getTaskExample() {
        return new TaskExample();
    }
}
