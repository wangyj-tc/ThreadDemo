package com.coop.demo.notify;
/**
 * ����߳�֮����л��Ѻ����߲���
 * @author yongjian.wang
 *
 */
public class MultiObjectWait {
	
	public static void main(String[] args) throws InterruptedException {
        final Object object1 = new Object();
        final Object object2 = new Object();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("�ȴ�object1��֪ͨ��");
                    synchronized (object1) {
                        object1.wait();
                    }
                    System.out.println("object1�ѱ�֪ͨ�����Ͽ�ʼ֪ͨobject2��");
                    synchronized (object2) {
                        object2.notify();
                    }
                    System.out.println("֪ͨobject2��ϣ�");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("���Ͽ�ʼ֪ͨobject1��");
                    synchronized (object1) {
                        object1.notify();
                    }
                    System.out.println("֪ͨobject1��ϣ��ȴ�object2��֪ͨ��");
                    synchronized (object2) {
                        object2.wait();
                    }
                    System.out.println("object2�ѱ�֪ͨ��");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
    }


}
