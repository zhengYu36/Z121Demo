package com.zy.DemoA;

public class Producer implements Runnable {
      
    private EventStorage storge;  
      
    public Producer(EventStorage storage) {  
        this.storge = storage;  
    }  
  
    @Override  
    public void run() {  
        for(int i = 0; i < 20; i++) {
            storge.set();  
        }  
    }  
  
}  