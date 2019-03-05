package controller;

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

    @Autowired
    public RequestControler(MemoryArchive archive, TaskQueue taskQueue, TaskExample taskExample) {
        this.archive = archive;
        this.taskQueue = taskQueue;
        this.taskExample = taskExample;
        taskQueue.start();
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> sendRequest() throws IOException, InterruptedException {
        String id = taskQueue.put(() -> taskExample.WriteA("additional"));
        return ResponseEntity.status(HttpStatus.OK).body("Id " + id);
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> statusRequest(@Valid @RequestBody Request request) throws IOException, InterruptedException {
        String status = archive.getStatus(request.getJobID());
        return ResponseEntity.status(HttpStatus.OK).body(status);

    }
}
