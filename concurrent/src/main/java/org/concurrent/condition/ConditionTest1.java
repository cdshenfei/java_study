package org.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest1 {

	private static Lock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();

	public static void main(String[] args) {
		ThreadA ta = new ThreadA("�߳�A");
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "�߳������߳�A!");
			ta.start();
			System.out.println(Thread.currentThread().getName() + "�߳�����!");
			condition.await();
			System.out.println(Thread.currentThread().getName() + "�̼߳���ִ��!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	static class ThreadA extends Thread {

		public ThreadA(String name) {
			super(name);
		}

		@Override
		public void run() {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + "��ʼִ��!");
				System.out.println(Thread.currentThread().getName() + "���������߳�!");
				condition.signal();
			} finally {
				lock.unlock();
			}
		}

	}

}
