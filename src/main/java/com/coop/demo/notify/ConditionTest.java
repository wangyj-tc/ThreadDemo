package com.coop.demo.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Condition��������������conditonҲ����Ҫ��������
 * @author yongjian.wang
 *
 */
public class ConditionTest {

	private static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) throws InterruptedException {
		final Condition condition1 = lock.newCondition();
		final Condition condition2 = lock.newCondition();
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				lock.lock();
				try {
					System.out.println("�ȴ�condition1��֪ͨ��");
					condition1.await();
					System.out.println("condition1�ѱ�֪ͨ�����Ͽ�ʼ֪ͨcondition2��");
					condition2.signal();
					System.out.println("֪ͨcondition2��ϣ�");
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				} finally {
					lock.unlock();
				}
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				lock.lock();
				try {
					System.out.println("���Ͽ�ʼ֪ͨcondition1��");
					condition1.signal();
					System.out.println("֪ͨcondition1��ϣ��ȴ�condition2��֪ͨ��");
					condition2.await();
					System.out.println("condition2�ѱ�֪ͨ��");
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				} finally {
					lock.unlock();
				}
			}
		});
		thread1.start();
		Thread.sleep(1000);
		thread2.start();
	}

}
