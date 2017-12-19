package com.coop.demo.notify;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	public static void main(String[] args) {
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
		for (int i = 0; i < 10; i++) {
			final int number = i + 1;
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					System.out.println("�ȴ�ִ������[" + number + "]");
					try {
						cyclicBarrier.await();
					} catch (InterruptedException e) {
					} catch (BrokenBarrierException e) {
					}
					System.out.println("��ʼִ������[" + number + "]");
				}
			};
			Thread thread = new Thread(runnable);
			thread.start();
		}
	}

}
