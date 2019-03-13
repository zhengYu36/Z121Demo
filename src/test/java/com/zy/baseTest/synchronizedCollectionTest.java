package com.zy.baseTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class synchronizedCollectionTest {

    static List al = Collections.synchronizedList(new ArrayList<>());
    static Vector vt = new Vector();

    public static void main(String[] args) throws Exception {

        Thread thread1 = new Thread() {

            public void run() {
                for (int i = 0; i < 10; i++) {
                    al.add(new Integer(i));
                    vt.add(new Integer(i));
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread2 = new Thread() {

            public void run() {
                for (int i = 0; i < 10; i++) {
                    al.add(new Integer(i));
                    vt.add(new Integer(i));
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread1.start();

        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(al);
        System.out.println(vt);
    }

}