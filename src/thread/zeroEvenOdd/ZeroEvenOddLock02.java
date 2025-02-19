package thread.zeroEvenOdd;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @Desc: even 和 odd 等待 zero执行完毕,执行。 zero等待 even or odd执行完毕执行。
 * 优化: 添加阻塞等待 ,增加条件变量,无需flag
 * @Author：zhh
 * @Date：2024/12/17 16:30
 */
public class ZeroEvenOddLock02 {

    private int n;

    private volatile int number = 1;

    ReentrantLock lock = new ReentrantLock();

    Condition zero = lock.newCondition();

    Condition even = lock.newCondition();

    Condition odd = lock.newCondition();


    public ZeroEvenOddLock02(int n) {
        this.n = n;
    }


    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (number <= n){
                printNumber.accept(0);
                if (number % 2 !=0){
                    odd.signal();
                }else {
                    even.signal();
                }
                zero.await();
            }
            even.signal();
            odd.signal();
        } finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (number <= n){
            lock.lock();
            if(number <= n && number % 2 ==0){
                printNumber.accept(number);
                number++;
                zero.signal();
            }
            even.await();
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
                if( number % 2 !=0){
                    printNumber.accept(number);
                    number ++;
                    zero.signal();
                }
                odd.await();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        IntConsumer zero = value ->  System.out.printf("%d", value);
        IntConsumer even = value ->  System.out.printf("%d", value);
        IntConsumer odd = value ->  System.out.printf("%d", value);
        ZeroEvenOddLock02 zeroEvenOddSemaphore = new ZeroEvenOddLock02(51);
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
