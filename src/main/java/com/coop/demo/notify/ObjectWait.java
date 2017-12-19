package com.coop.demo.notify;

/**
 * wait��notify��������������ȴ�ƪ
 * wait,notify��notifyAll������ʹ��ǰ�������ȡ����ǰ�������
 * @author yongjian.wang
 *
 */
public class ObjectWait {

	private volatile static boolean lock;

	public static void main(String[] args) throws InterruptedException {
		final Object object = new Object();

		Thread thread1 = new Thread(new Runnable() {

			public void run() {
				System.out.println("�ȴ���֪ͨ��");
				try {
					synchronized (object) {
						while (!lock) {
							object.wait();
						}
					}
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				System.out.println("�ѱ�֪ͨ");
			}
		});
		Thread thread2 = new Thread(new Runnable() {

			public void run() {
				System.out.println("���Ͽ�ʼ֪ͨ��");
				synchronized (object) {
					object.notify();
					lock = true;
				}
				System.out.println("��֪ͨ");
			}
		});
		thread1.start();
		thread2.start();
		Thread.sleep(100000);
	}
}
