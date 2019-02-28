package executor;

import controller.Archive;
import lombok.Getter;

@Getter
public class Broker {
    private Archive archive;
    private TaskQueue mailbox;
    private Executor executor;

    public Broker(){
        archive = new Archive();
        mailbox =new TaskQueue(archive);
        executor =new Executor(mailbox,archive);
        executor.start();
    }
}
