//volatile keyword in Multithreading

package com.amit.multithreading;

class Worker implements Runnable {
	  
	 //This flag 'isTerminated' is set to false 
	//This will keep reading from cache memory without terminating
	//private boolean isTerminated = false;
	
	//Volatile keyword helps cpu to read from Main memory for better visibility for thread
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
		
		//To manually set the Priority of thread
		//t1.setPriority(10);
		
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
