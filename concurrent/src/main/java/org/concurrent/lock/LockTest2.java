package org.concurrent.lock;


// LockTest2.java
// ȥ������ִ��Ч��
public class LockTest2 {

	static class Depot {
		private int size; // �ֿ��ʵ������

		// private Lock lock; // ��ռ��

		public Depot() {
			this.size = 0;
			// this.lock = new ReentrantLock();
		}

		public void produce(int val) {
			// lock.lock();
			// try {
			size += val;
			System.out.printf("%s produce(%d) --> size=%d\n", Thread.currentThread().getName(), val, size);
			// } catch (InterruptedException e) {
			// } finally {
			// lock.unlock();
			// }
		}

		public void consume(int val) {
			// lock.lock();
			// try {
			size -= val;
			System.out.printf("%s consume(%d) <-- size=%d\n", Thread.currentThread().getName(), val, size);
			// } finally {
			// lock.unlock();
			// }
		}
	};

	// ������
	static class Producer {
		private Depot depot;

		public Producer(Depot depot) {
			this.depot = depot;
		}

		// ���Ѳ�Ʒ���½�һ���߳���ֿ���������Ʒ��
		public void produce(final int val) {
			new Thread() {
				public void run() {
					depot.produce(val);
				}
			}.start();
		}
	}

	// ������
	static class Customer {
		private Depot depot;

		public Customer(Depot depot) {
			this.depot = depot;
		}

		// ���Ѳ�Ʒ���½�һ���̴߳Ӳֿ������Ѳ�Ʒ��
		public void consume(final int val) {
			new Thread() {
				public void run() {
					depot.consume(val);
				}
			}.start();
		}
	}

	public static void main(String[] args) {
		Depot mDepot = new Depot();
		Producer mPro = new Producer(mDepot);
		Customer mCus = new Customer(mDepot);

		mPro.produce(60);
		mPro.produce(120);
		mCus.consume(90);
		mCus.consume(150);
		mPro.produce(110);
	}
}