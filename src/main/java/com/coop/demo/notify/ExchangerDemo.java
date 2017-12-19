package com.coop.demo.notify;

import java.util.concurrent.Exchanger;
/**
 * �����߳�֮�佻�����ݣ������ݿ�������������ٶ�
 * @author yongjian.wang
 *
 */
public class ExchangerDemo {

	public static void main(String[] args) throws InterruptedException {
		final Exchanger<String> exchanger = new Exchanger<String>();
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("�߳�1�ȴ�����");
					String content = exchanger.exchange("thread1");
					System.out.println("�߳�1�յ���Ϊ��" + content);
				} catch (InterruptedException e) {
				}
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("�߳�2�ȴ����ܲ���˯3��");
					Thread.sleep(3000);
					String content = exchanger.exchange("thread2");
					System.out.println("�߳�2�յ���Ϊ��" + content);
				} catch (InterruptedException e) {
				}
			}
		});
		thread1.start();
		thread2.start();
	}

}
