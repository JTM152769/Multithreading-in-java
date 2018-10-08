package com.amit.multithreading;

class Worker implements Runnable {
	
	//private boolean isTerminated = false;
	private volatile boolean isTerminated = false;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!isTerminated) {
			System.out.println("Hi, I am from cache");
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean isTerminated() {
		return isTerminated;
	}

	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}
	
}

public class VolatileDemo {
	
	public static void main(String[] args) {
		
		Worker W = new Worker();
		
		Thread t1 = new Thread(W);
		
		t1.setPriority(10);
		
		t1.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		W.setTerminated(true);
		System.out.println("Finished Thread Running");
		
		
	}

}
