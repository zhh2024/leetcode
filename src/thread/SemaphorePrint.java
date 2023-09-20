package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author：zhh
 * @Date：2023/9/10 17:52
 *
 * 信号量思路:信号量底层是 可以允许多少个线程通行,如果给定通行证只有一个， 利用信号量次数来阻塞，互相阻塞。
 *
 * A线程拿上打印，然后释放通行证
 * B线程拿上打印,然后释放通行证
 * A，B线程交替打印肯定还需要一个flag。
 * 写完发现出现问题了，上面这种思路会出现CPU空转。因为信号量只有获取和释放，要在线程之间通信,另一个线程要被阻塞掉
 * 光有信号量的获取和释放不行，不满足通信条件不能一直空循环呀。
 *
 * 本质就是: sync和Reen 能提供锁，还能提供等待,且等待能够释放锁，Semaphore并没有等待功能。
 *
 * 既然一个信号量解决不了问题,那么用两个信号量对象试试呢？
 * A线程有自己的信号量 ,B线程有自己的信号量
 * A获取到信号量，执行代码，释放B的信号量
 * B获取到信号量，执行代码，释放A的信号量
 * 问题信号量都为1 就会AB同时执行，AB先后顺序的，A为1，B为0.
 * A线程先处理,B线程阻塞。A处理完，释放B的信号量，信号量会+1，哪怕一开始是0，源码会+1
 * A再想获取信号量就会阻塞，因为信号量没有重入概念而是次数，所以虽然重入了但是还是会被阻塞。
 * B获取信号量，执行完，释放A的信号量，A信号量+1,并且唤醒阻塞队列中的A。
 *
 *
 */
public class SemaphorePrint {
    private int n;

    public SemaphorePrint(int n){
        this.n = n;
    }

    Semaphore a = new Semaphore(1);
    Semaphore b = new Semaphore(0);
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n;  i++) {
            a.acquire();
            printFoo.run();
            b.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            b.acquire();
            printBar.run();
            a.release();
        }
    }
    public static void main(String[] args) {

        SemaphorePrint foo = new SemaphorePrint(5);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> {
            try {
                foo.foo(() -> System.out.println("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                foo.bar(() -> System.out.println("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }
}
