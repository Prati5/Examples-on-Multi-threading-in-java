//Thread is an  independent path of execution in program.
// Multithreading refers to concurrent execution of two or more tasks in a single program.
/*Synchronized methods define critical sections
1. wait() tells the calling thread to give up the monitor and go to sleep until some other thread enters the same monitor and calls notify( ).
2. notify() wakes up the first thread that called wait() on the same object.
*/

package twothreads;

/**
 *
 * @author 
 */
class Printoddeven{

    public synchronized void print(int num) {
        try {
            if(num % 2==0) {
                    System.out.println(num);
                    Thread.sleep(2000);
                    notify();
                    wait();
            } else
            if(num %2 ==1) {
                    System.out.println(num);
                    Thread.sleep(2000);
                    notify();
                    wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class PrintOdd extends Thread{
    Printoddeven odd;
    public PrintOdd(Printoddeven odd){
        this.odd=odd;
    }

    public void run(){
        for(int i=1; i<20;i+=2)
        odd.print(i);
    }
}

class PrintEven extends Thread{
    Printoddeven even;
    public PrintEven(Printoddeven even){
        this.even=even;
    }

    public void run(){
      for(int i=0; i<20;i+=2)
        even.print(i);
    }
}



public class Twothreads 
{
    public static void main(String[] args) {
        Printoddeven obj = new Printoddeven();//only one object  
        PrintEven t1=new PrintEven(obj);  
        PrintOdd t2=new PrintOdd(obj);  
        t1.start();  
        t2.start();  
    }
}
