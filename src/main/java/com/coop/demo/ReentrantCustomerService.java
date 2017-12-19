package com.coop.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * ReentrantLock轮询锁
 * @author yongjian.wang
 *
 */
public class ReentrantCustomerService implements Runnable {

	public static CustomerIdGengera customerIdGengera = new CustomerIdGengera();

	private static Lock lock = new ReentrantLock();

	public void run() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}

			try {
				lock.lock();
				System.out.println(Thread.currentThread().getName() + " 生成用户ID  " + customerIdGengera.initCustId());
			} catch (Exception e) {

			} finally {
				lock.unlock();
		}

	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new ReentrantCustomerService(), "Thread--" + i).start();
		}
	}
}
