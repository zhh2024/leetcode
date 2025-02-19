package thread.zeroEvenOdd;


import java.util.concurrent.CountDownLatch;
import java.util.function.IntConsumer;

/**
 * @Desc: even 和 odd 等待 zero执行完毕,执行。 zero等待 even or odd执行完毕执行。
 *
 * 在循环里面会重新赋值new CountDownLatch(1),防止继续循环。
 * @Author：zhh
 * @Date：2024/12/17 16:30
 */
public class ZeroEvenOddCountDown {
    private int n;

    CountDownLatch zero = new CountDownLatch(0);

    CountDownLatch even = new CountDownLatch(1);

    CountDownLatch odd = new CountDownLatch(1);

    public ZeroEvenOddCountDown(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zero.await();
            printNumber.accept(0);
            //又给1,等even,old countDown
            zero = new CountDownLatch(1);
            if(i %2 == 0){
                even.countDown();
            }else {
                odd.countDown();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if( i%2 == 0){
                even.await();
                printNumber.accept(i);
                //又给1,防止继续循环,等zero countDown
                even = new CountDownLatch(1);
                zero.countDown();
            }
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if( i%2 != 0){
                odd.await();
                printNumber.accept(i);
                //又给1,防止继续循环,等zero countDown
                odd = new CountDownLatch(1);
                zero.countDown();
            }
        }
    }

    public static void main(String[] args) {
        IntConsumer zero = value ->  System.out.printf("%d", value);
        IntConsumer even = value ->  System.out.printf("%d", value);
        IntConsumer odd = value ->  System.out.printf("%d", value);
        ZeroEvenOddCountDown zeroEvenOddSemaphore = new ZeroEvenOddCountDown(2);
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
