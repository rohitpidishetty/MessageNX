package com.mnx.MessageNX.Controller;

import com.mnx.MessageNX.MessageUtility.MessageQueue;
import com.mnx.MessageNX.Service.ConsumerWorker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message-nx-consumer")
public class ConsumerListener {

    final private MessageQueue mq;
    final private ConsumerWorker worker;

    public ConsumerListener(MessageQueue mq, ConsumerWorker worker) {
        this.mq = mq;
        this.worker = worker;
    }

    @GetMapping("/listen")
    public ResponseEntity<?> listen(@RequestParam String channel) {
        return worker.pollMessage(this.mq, channel);
    }
}
