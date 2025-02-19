package thread.zeroEvenOdd;


import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @Desc: even 和 odd 等待 zero执行完毕,执行。 zero等待 even or odd执行完毕执行。
 * 三个线程一直去抢锁,有可能一个线程会一直强,但又不符合条件处理,会空转,浪费资源。
 * @Author：zhh
 * @Date：2024/12/17 16:30
 */
public class ZeroEvenOddLock {
    private int n;

    private volatile int number = 1;

    private int flag = 0;

    ReentrantLock lock = new ReentrantLock();


    public ZeroEvenOddLock(int n) {
        this.n = n;
    }


    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (number <= n){
            try {
                lock.lock();
                //没释放锁,所以必须finally捕捉释放锁
                if(number > n){
                    return;
                }
                if(flag == 0){
                    printNumber.accept(0);
                }
                if (number % 2 !=0){
                    flag = 1;
                }else {
                    flag = 2;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (number <= n){
            lock.lock();
            if(number <= n && flag == 2){
                printNumber.accept(number);
                number++;
                flag = 0;
            }
            lock.unlock();
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (true){
            try {
                lock.lock();
                if(number > n){
                    return;
                }
                if(number <= n && flag == 1){
                    printNumber.accept(number);
                    number ++;
                    flag = 0;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        IntConsumer zero = value ->  System.out.printf("%d", value);
        IntConsumer even = value ->  System.out.printf("%d", value);
        IntConsumer odd = value ->  System.out.printf("%d", value);
        ZeroEvenOddLock zeroEvenOddSemaphore = new ZeroEvenOddLock(51);
        new Thread(() -> {
            try {
                zeroEvenOddSemaphore.zero(zero);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"zero").start();
        new Thread(() -> {
            try {
                zeroEvenOddSemaphore.even(even);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"even").start();
        new Thread(() -> {
            try {
                zeroEvenOddSemaphore.odd(odd);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"odd").start();
    }
}
