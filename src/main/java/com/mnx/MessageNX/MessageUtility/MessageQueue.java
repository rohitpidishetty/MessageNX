package com.mnx.MessageNX.MessageUtility;

import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class MessageQueue {
    final public BlockingQueue<Object> MessageNXQ = new LinkedBlockingQueue<>();

    final public ConcurrentHashMap<String, BlockingQueue<Object>> MessageNXQChannels = new ConcurrentHashMap<>();

    public void offer(String channel, Object topic) {
        this.MessageNXQChannels.computeIfAbsent(channel, e -> new LinkedBlockingQueue<Object>()).offer(topic);
    }

    public boolean idAlreadyExists(String id) {
        return this.MessageNXQChannels.containsKey(id);
    }

    public void register(String id) {
        this.MessageNXQChannels.put(id, new LinkedBlockingQueue<>());
    }
}
