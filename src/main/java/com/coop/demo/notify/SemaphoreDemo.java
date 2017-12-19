package com.coop.demo.notify;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreDemo {
	
	public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(10);
        final AtomicInteger number = new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                    try {
                        semaphore.acquire();
                        number.incrementAndGet();
                    } catch (InterruptedException e) {}
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
        Thread.sleep(10000);
        System.out.println("��" + number.get() + "���̻߳�õ��ź�");
        System.exit(0);
    }
    

}
