package com.coop.demo.map;

import com.coop.model.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2017/12/19.
 */
public class UnsafeMapDemo {

    public static  Map<Person, String> map = new HashMap<Person, String>(1000);

    public static ThreadLocal<Person> threadLocal = new ThreadLocal<Person>();

    public static void main(String[] args) {

        int counter = 0;
        while (true) {
            // creates duplicate objects due to bad Key class
            // map.put(new Person("wyj",26), "value");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    //threadLocal.set(new Person("wyj",26));
                    map.put(new Person("wyj",26), "value");
                }
            }).start();

            counter++;
            if (counter % 1000 == 0) {
                System.out.println("map size: " + map.size());
                System.out.println("Free memory after count " + counter
                        + " is " + getFreeMemory() + "MB");

                sleep(100);
            }

        }

    }

    public static void sleep(long sleepFor) {
        try {
            Thread.sleep(sleepFor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // get available memory in MB
    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory() / (1024 * 1024);
    }

}
