package controller;

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
    public TaskExample getTaskExample() {
        return new TaskExample();
    }
}
