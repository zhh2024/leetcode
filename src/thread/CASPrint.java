package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author：zhh
 * @Date：2023/9/10 17:19
 *
 * Reen内部也是CAS, 那么直接利用 原子变量更改时的互斥是不是就能实现呢？
 *
 * 只能打印一次, 没啥用 要打印多次 必然是要上锁加锁，那就是Reen了。
 */
public class CASPrint {

    AtomicInteger atomicInteger = new AtomicInteger(0);
    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        atomicInteger.compareAndSet(0,1);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (!atomicInteger.compareAndSet(1,2)){

        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (!atomicInteger.compareAndSet(2,1)){

        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) {


        CASPrint foo = new CASPrint();

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
