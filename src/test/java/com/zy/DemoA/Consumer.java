package com.zy.DemoA;

public class Consumer implements Runnable {
      
    private EventStorage storage;  
      
    public Consumer(EventStorage storage) {  
        this.storage = storage;  
    }  
  
    @Override  
    public void run() {  
        for(int i = 0; i < 20; i++) {
            storage.get();  
        }  
    }  
      
    public static void main(String[] args) {  
        EventStorage storage = new EventStorage();  
        Producer producer = new Producer(storage);  
        Thread thread1 = new Thread(producer);  
          
        Consumer consumer = new Consumer(storage);  
        Thread thread2 = new Thread(consumer);

        thread1.start();
        thread2.start();  

    }  
}  