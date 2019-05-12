package cn.xuweiteng.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ��дһ�����򣬿���3���̣߳�ID�ֱ�ΪA,B,C��ÿ���߳̽��Լ���ID��ӡ10�飬Ҫ����������˳����ʾ
 * ���磺ABCABCABC....
 * ���磺AAAAABBBBBCCCCCAAAAABBBBBCCCCC
 * ���磺��
 * @author MrXu
 *
 */
public class TestAlternately {

	private ReentrantLock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    private int number = 1;
    
    
    public static void main(String[] args) {

        final TestAlternately alternativePrint = new TestAlternately();

        new Thread("A"){
            @Override
            public void run() {
                for (int i= 0; i < 20; i++)
                    alternativePrint.loopA();
            }
        }.start();

        new Thread("B"){
            @Override
            public void run() {
                for (int i= 0; i < 20; i++)
                    alternativePrint.loopB();
            }
        }.start();

        new Thread("C"){
            @Override
            public void run() {
                for (int i= 0; i < 20; i++)
                    alternativePrint.loopC();
            }
        }.start();
    }
    
	
	public void loopA(){
        lock.lock();
        try {
            if (number != 1){
                conditionA.await();
            }

            for (int i = 1; i <= 5; i++){
                System.out.println(Thread.currentThread().getName() + "     " + i);
            }

            number = 2;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB(){
        lock.lock();
        try {
            if (number != 2){
                conditionB.await();
            }

            for (int i = 1; i <= 5; i++){
                System.out.println(Thread.currentThread().getName() + "     " + i);
            }

            number = 3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC(){
        lock.lock();
        try {
            if (number != 3){
                conditionC.await();
            }

            for (int i = 1; i <= 5; i++){
                System.out.println(Thread.currentThread().getName() + "     " + i);
            }

            number = 1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("--------------------------------------------------");
            lock.unlock();
        }
    }
	
}