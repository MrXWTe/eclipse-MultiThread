package cn.xuweiteng.juc;

import java.util.concurrent.Semaphore;

/**
 * �����ź���
 * @author MrXu
 *
 */
public class TestSemaphore {
	public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        
        PrintQueue1 printQueue = new PrintQueue1();
        
        for(int i = 0 ; i < 10 ; i++){
            threads[i] = new Thread(new Job1(printQueue),"Thread_" + i);
        }
        
        for(int i = 0 ; i < 10 ; i++){
            threads[i].start();
        }
    }
}

class PrintQueue1 {
    private final Semaphore semaphore;   //�����ź���
    
    public PrintQueue1(){
        semaphore = new Semaphore(1);
    }
    
    public void printJob(Object document){
        try {
            semaphore.acquire();//����acquire��ȡ�ź���
            long duration = (long) (Math.random() * 10);
            System.out.println( Thread.currentThread().getName() + 
                    "PrintQueue : Printing a job during " + duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }  finally{
            semaphore.release();  //�ͷ��ź���
        }
    }
}

class Job1 implements Runnable{
    private PrintQueue1 printQueue;
    
    public Job1(PrintQueue1 printQueue){
        this.printQueue = printQueue;
    }
    
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Going to print a job");
        printQueue.printJob(new Object());
        System.out.println(Thread.currentThread().getName() + " the document has bean printed");
    }

}