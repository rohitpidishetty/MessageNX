package com.mnx.MessageNX.Service;

import com.mnx.MessageNX.MessageUtility.MessageQueue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"*", "unchecked"})
@Service
public class ConsumerWorker {
    public ResponseEntity<?> pollMessage(MessageQueue mq, String channel) {

        if (!mq.MessageNXQChannels.containsKey(channel)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No channel");
        }
        if (mq.MessageNXQChannels.get(channel).isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No messages");
        HashMap<String, Object> topic = (HashMap<String, Object>) mq.MessageNXQChannels.get(channel).poll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(topic);
    }

}
