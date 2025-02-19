package thread.zeroEvenOdd;


import thread.fizzBuzz.FizzBuzz05;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @Desc: even 和 odd 等待 zero执行完毕,执行。 zero等待 even or odd执行完毕执行。
 *
 * @Author：zhh
 * @Date：2024/12/17 16:30
 */
public class ZeroEvenOddSemaphore {

    private int n;


    Semaphore zero = new Semaphore(1);

    Semaphore even = new Semaphore(0);

    Semaphore odd = new Semaphore(0);

    public ZeroEvenOddSemaphore(int n) {
        this.n = n;
    }


    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zero.acquire();
            printNumber.accept(0);
            if(i % 2 == 0){
                even.release();
            }else {
                odd.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if(i % 2 == 0){
                even.acquire();
                printNumber.accept(i);
                zero.release();
            }
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if(i % 2 != 0){
                odd.acquire();
                printNumber.accept(i);
                zero.release();
            }
        }
    }

    public static void main(String[] args) {
        IntConsumer zero = value ->  System.out.printf("%d", value);
        IntConsumer even = value ->  System.out.printf("%d", value);
        IntConsumer odd = value ->  System.out.printf("%d", value);
        ZeroEvenOddSemaphore zeroEvenOddSemaphore = new ZeroEvenOddSemaphore(2);
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
