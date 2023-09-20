package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author：zhh
 * @Date：2023/9/10 16:24
 *
 * 与同步原语打印思路一样,互有优缺点 我个人觉得同步源于比Reen要好点
 *
 * Reen 比 sync
 * 优点:可以避免虚假唤醒 ，可以唤醒指定的条件等待的线程，但是如果指定条件等待的线程有多个采用signalAll();
 *      内部是CAS,性能可能会比sync性能高
 * 缺点:最后打印完毕,还需要唤醒所有条件的singAll,而不是单独一个singAll . 这样线程才不会出现无休止的等待。
 *
 * 可以看出既是优点也是缺点。
 *
 * 注意:使用 Reen还需要配合 volatile使用， 因为Reen内部是CAS实现，用户自定义的全局变量不会被保护，需要自己volatile
 * 使用 volatile 关键字来保证变量在多线程之间的可见性。但是，这并不是 ReentrantLock 本身的要求，而是由具体的业务场景决定的。
 *
 */
public class ReenPrint {
    ReentrantLock reentrantLock = new ReentrantLock();
    Condition first = reentrantLock.newCondition();
    Condition second = reentrantLock.newCondition();
    Condition third = reentrantLock.newCondition();
    volatile int flag = 1;
    volatile int printCount = 9;
    public void first(Runnable printFirst) throws InterruptedException {
        try{
            reentrantLock.lock();
            while (printCount>0) {
                if(flag == 1){
                    // printFirst.run() outputs "first". Do not change or remove this line.
                    printFirst.run();
                    flag = 2;
                    printCount --;
                    second.signal();
                }else {
                    first.await();
                }
            }
        }finally {
            reentrantLock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        try{
            reentrantLock.lock();
            while (printCount>0) {
                if(flag == 2){
                    // printSecond.run() outputs "second". Do not change or remove this line.
                    printSecond.run();
                    flag = 3;
                    printCount --;
                    third.signal();
                }else {
                    second.await();
                }
            }
        }finally {
            reentrantLock.unlock();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        try{
            reentrantLock.lock();
            while (printCount>0) {
                if(flag == 3){
                    // printThird.run() outputs "third". Do not change or remove this line.
                    printThird.run();
                    flag = 1;
                    printCount --;
                    if(printCount ==0 ){
                        //唤醒所有线程执行完毕
                        first.signalAll();
                        second.signalAll();
                    }
                    first.signal();
                }else {
                    third.await();
                }
            }
        }finally {
            reentrantLock.unlock();
        }
    }
    public static void main(String[] args) {

        ReenPrint foo = new ReenPrint();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> {
            try {
                foo.first(() -> System.out.println("first"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                foo.second(() -> System.out.println("second"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                foo.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }
}
