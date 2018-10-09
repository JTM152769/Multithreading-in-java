//This is not the good practise to use Synchronized block when we have independent tasks and have different threads with 

//as it will take more time with intrinsic class lock

package com.amit.multithreading;


public class SynchronizedBlockWithClasslock {
	
	public static int count1=0;
	public static int count2=0;
	
	public static void counter1() {
    		synchronized(SynchronizedBlockWithClasslock.class){
      			count1++;
      		}
	}
	
	public synchronized static void counter2() {
		synchronized(SynchronizedBlockWithClasslock.class){
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