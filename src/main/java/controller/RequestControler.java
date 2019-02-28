package controller;

import executor.Executor;
import executor.TaskQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import task.TaskExample;

import javax.validation.Valid;
import java.io.IOException;


@Controller
@RequestMapping("/command")
public class RequestControler {
    MemoryArchive archive;
    TaskQueue taskQueue;
    TaskExample taskExample;
    Executor executor;

    @Autowired
    public RequestControler(MemoryArchive archive, TaskQueue taskQueue, TaskExample taskExample, Executor executor) {
        this.archive = archive;
        this.taskQueue = taskQueue;
        this.executor = executor;
        this.taskExample = taskExample;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> sendRequest() throws IOException, InterruptedException {
        String id = taskQueue.addTask(() -> taskExample.WriteA("additional"));
        return ResponseEntity.status(HttpStatus.OK).body("Id " + id);
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> statusRequest(@Valid @RequestBody Request request) throws IOException, InterruptedException {
        String status = archive.getStatus(request.getJobID());
        return ResponseEntity.status(HttpStatus.OK).body(status);

    }
}
