package thread.h2o;


import java.util.concurrent.Semaphore;


/**
 * @Desc: 两个方法理论可以并行执行, 而且hydrogen,许可证是2,两个线程在多核情况下可以并行执行,效率更快。
 *
 * 所以
 * 1. 两个方法可以并行执行,每个方法对应一个信号量可以提升效率
 * 2. 当2个H和一个O匹配,H方法没必要使用锁保持H个数<=2,使用信号量可以提升效率。
 * @Author：zhh
 * @Date：2024/11/19 14:42
 */
public class H2O03 {

    Semaphore h = new Semaphore(2);
    Semaphore o = new Semaphore(0);


    public H2O03()  {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        releaseHydrogen.run();
        o.release();
    }


    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
        releaseOxygen.run();
        h.release(2);
    }
}
