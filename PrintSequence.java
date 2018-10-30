/*
Using synchronized method, we can print different operators(which represents different threads) in sequence.
 */
package printsequence;

/**
 *
 * @author pratima
 */
public class PrintSequence {

    public static void main(String[] args) {
        // TODO code application logic here
		
		PrintOperators op1=new PrintOperators(1);
		PrintOperators op2=new PrintOperators(2);
		PrintOperators op3=new PrintOperators(0);
		
		Thread t1=new Thread(op1,"*");
		Thread t2=new Thread(op2,"=");
		Thread t3=new Thread(op3,"-");
		
		t1.start();
		t2.start();
		t3.start();	  
	}
}
 class PrintOperators implements Runnable{
 
	public int max=12;
	static int  i=1;
	int r;
	static Object lock=new Object();
 
	PrintOperators(int r)
	{
		this.r=r;
	}
 
	@Override
	public void run() {
		while (i < max-1) {
			synchronized (lock) {
				while (i % 3 != r) { // wait for operators that is not a  remainder
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName());
				i++;
				lock.notifyAll();
			}
		}
	}
}
 

    

