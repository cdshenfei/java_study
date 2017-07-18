package org.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数组有界队列
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class ArrayBoundedQueue<T> {

	/**
	 * 数组
	 */
	private Object[] objectArray = null;

	/**
	 * 记录add操作的下标
	 */
	private int addIndex = 0;

	/**
	 * 记录remove操作的下标
	 */
	private int removeIndex = 0;

	/**
	 * 记录队列进行增删操作后的真实总数
	 */
	private int totalCount = 0;

	/**
	 * 锁对象
	 */
	private Lock lock = new ReentrantLock();

	/**
	 * 队列满时用于阻塞添加操作
	 */
	private Condition fullCondition = lock.newCondition();

	/**
	 * 队列空时用于阻塞删除操作
	 */
	private Condition emptyCondition = lock.newCondition();

	/**
	 * 构造方法
	 * 
	 * @param size
	 */
	public ArrayBoundedQueue(int size) {
		objectArray = new Object[size];
	}

	/**
	 * 添加元素到有界队列
	 * 
	 * @param e
	 * @throws InterruptedException
	 */
	public void add(T e) {
		lock.lock();
		try {
			if (totalCount == objectArray.length) {
				fullCondition.await();
			}
			objectArray[addIndex++] = e;
			totalCount++;
			// 新增的index满了之后下次从0开始
			if (addIndex == objectArray.length) {
				addIndex = 0;
			}
			emptyCondition.signal();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("++++++++++++++++向队列中成功添加元素" + e);
		lock.unlock();
	}

	/**
	 * 删除有界队列中的元素
	 * 
	 * @param e
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unchecked")
	public T remove(T e) {
		Object x = null;
		lock.lock();
		try {
			if (totalCount == 0) {
				emptyCondition.await();
			}
			x = objectArray[removeIndex++];
			totalCount--;
			// 删除的index满了之后下次从0开始
			if (removeIndex == objectArray.length) {
				removeIndex = 0;
			}
			fullCondition.signal();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("-----------------------成功的从队列中移除元素" + e);
		lock.unlock();
		return (T) x;
	}

	public static void main(String[] args) throws InterruptedException {
		final ArrayBoundedQueue<Integer> queue = new ArrayBoundedQueue<Integer>(2);
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					queue.remove(i);

				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					queue.add(i);
				}
			}
		}).start();
	}

}