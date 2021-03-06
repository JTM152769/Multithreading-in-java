//This good practise to use Synchronized block when we have independent tasks and have different threads with 

// two independent lock 

package com.amit.multithreading;


public class SynchronizedBlockWithLock {
	
	public static int count1=0;
	public static int count2=0;
  
  public static Object lock1 = new Object();
  public static Object lock2 = new Object();
	
	public static void counter1() {
    		Synchronized(lock1){
      			count1++;
      		}
	}
	
	public synchronized static void counter2() {
		Synchronized(lock2){
      			count2++;
      		}
	}
	
	public static void compute() {
		for(int i=0;i<100;++i) {
			counter1();
			counter2();
		}
	}
	
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				compute();
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				compute();
			}
			
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Count1= " +count1+ "Count2= " + count2);
	
	
	}

}
