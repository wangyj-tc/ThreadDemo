package com.coop.demo.notify;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(10);
		for (int i = 0; i < 10; i++) {
			final int number = i + 1;
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					System.out.println("ִ������[" + number + "]");
					countDownLatch.countDown();
					System.out.println("�������[" + number + "]");
				}
			};
			Thread thread = new Thread(runnable);
			thread.start();
		}
		System.out.println("���߳̿�ʼ�ȴ�...");
		countDownLatch.await();
		System.out.println("���߳�ִ�����...");
	}
}
