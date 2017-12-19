package com.coop.demo.singleton;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.coop.factory.ThreadPoolFactory;

/**
 * ���������࣬����ʽ
 * 
 * @author yongjian.wang
 *
 */
public class SingletonObject {

	private static SingletonObject instance;

	private SingletonObject() {
	}

	public static SingletonObject getInstance() {
		synchronized (SingletonObject.class) {
			if (instance == null) {
				instance = new SingletonObject();
			}
		}

		return instance;
	}

	public static void main(String[] args) throws InterruptedException {
		int threadCounts = 100;
		int testCounts = 100000;
		for (int i = 0; i < testCounts; i++) {
			test(threadCounts);
		}
	}

	public static void test(int threadCounts) throws InterruptedException {
		ExecutorService executorService = ThreadPoolFactory.getCacheExecutor();
		final CountDownLatch startFlag = new CountDownLatch(1);
		final CountDownLatch counter = new CountDownLatch(threadCounts);
		final Set<String> instanceSet = Collections.synchronizedSet(new HashSet<String>());
		for (int i = 0; i < threadCounts; i++) {
			executorService.execute(new Runnable() {
				public void run() {
					try {
						startFlag.await();
					} catch (InterruptedException e) {
					}
					instanceSet.add(SingletonObject.getInstance().toString());
					counter.countDown();
				}
			});
		}

		// �ȴ����е��̣߳�Ȼ��һ����(����ǹģʽ)
		startFlag.countDown();

		// �ȴ����е��߳����꣬��������ִ�У�������joinģʽ��
		counter.await();
		SingletonObject.instance = null;
		if (instanceSet.size() > 1) {
			System.out.print("{");
			for (String instance : instanceSet) {
				System.out.print("[" + instance + "]");
			}
			System.out.println("}");
		}
	}

}
