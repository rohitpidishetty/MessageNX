package com.mnx.MessageNX.Controller;

import com.mnx.MessageNX.MessageUtility.MessageQueue;
import com.mnx.MessageNX.Service.ProducerWorker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message-nx-producer")
public class ProducerListener {

    private final ProducerWorker worker;
    private final MessageQueue mq;

    public ProducerListener(ProducerWorker worker, MessageQueue mq) {
        this.worker = worker;
        this.mq = mq;
    }

    @PostMapping("/listen")
    public ResponseEntity<?> listener(@RequestBody Object topic) {
        try {
            this.worker.workOn(topic, this.mq);
            return ResponseEntity.status(200).body("Job offered");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Try again");
        }
    }

    @GetMapping("/id")
    public ResponseEntity<?> generateId(@RequestParam String content) {
        try {
            String hash = String.valueOf(content.hashCode());
            if (this.mq.idAlreadyExists(hash)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Try different combination");
            } else {
                this.mq.register(hash);
                return ResponseEntity.status(200).body(hash);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Try again");
        }
    }
}
