package controller;

import executor.Broker;
import executor.Executor;
import executor.TaskQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import task.TaskExample;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public Archive getArchive() {
        return new Archive();
    }

    @Bean
    public TaskQueue getTaskQueue(Archive archive) {
        return new TaskQueue(archive);
    }

    @Bean
    public Executor getExecutor(Archive archive, TaskQueue taskQueue) {
        Executor executor = new Executor(taskQueue, archive);
        executor.start();
        return executor;
    }

    @Bean
    public TaskExample getTaskExample() {
        return new TaskExample();
    }
}
