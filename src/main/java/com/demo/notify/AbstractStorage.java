package com.demo.notify;

public interface AbstractStorage {
    void consume(int num);

    void produce(int num);
}