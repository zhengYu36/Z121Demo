package com.zy.DemoA;

import java.util.Date;
import java.util.LinkedList;  
import java.util.List;


/**
 * <ul>
 * <li>文件包名 : com.zy.DemoA</li>
 * <li>创建时间 : 2018/6/1 14:07 </li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 * @author zhengyu
 */


  
public class EventStorage {  
      
    private int maxSize;  
      
    private List<Date> storage;  
      
    public EventStorage() {  
        maxSize = 10;  
        storage = new LinkedList<Date>();  
    }  
      
    public synchronized void set() {
        while(storage.size() == maxSize) {  
            try {  
                wait();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
          
        storage.add(new Date());  
        System.out.printf("Set: %d",storage.size());
        System.out.println();
        notifyAll();
    }  
      
    public synchronized void get() {
        while(storage.size() == 0) {  
            try {  
                wait();  
            } catch (InterruptedException e) {  
                // TODO AutoCreateJavaFile-generated catch block
                e.printStackTrace();  
            }  
        }  
          
        System.out.printf("Get: %d: %s",storage.size(),((LinkedList<?>)storage).poll());
        System.out.println();
        notifyAll();
    }
}