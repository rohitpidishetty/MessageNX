package com.mnx.MessageNX.Service;

import com.mnx.MessageNX.MessageUtility.MessageQueue;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"*", "unchecked"})
@Service
public class ProducerWorker {

    public void workOn(Object topic, MessageQueue mq) {
        HashMap<String, Object> payload = (HashMap<String, Object>) topic;

        mq.offer((String) payload.get("channel"), payload.get("topic"));
    }

}
