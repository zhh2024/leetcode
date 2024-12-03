package thread.diningPhilosophers;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Desc: 哲学家进餐
 * 与信号量实现一样,只是CAS代替ReentrantLock,采用无锁
 * <p>
 * 问题:
 * 1. CAS,如何区分每把叉子是否上锁,当前叉子上锁,不应该影响其他叉子
 * 解决思路:
 * 1. 位运算,从右到左,每一位是1表示叉子被占用了,比如 0001001 表示第一把和第四把叉子被占用了
 * 2. 每个哲学家获取自己编号附近的两把叉子,上锁就是get()到最新值,当前位由0变成1.
 * 3. 解锁就是当前位由1变成0
 * 4. 在上锁和解锁过程中,不影响别的叉子使用,只期望当前叉子所对应的位数是0还是1即可。
 * @Author：zhh
 * @Date：2024/12/3 11:09
 */
public class DiningPhilosophers05 {

    //初始化 00000000代表没有叉子被使用,如果 00000110 ,就代表编号 1,2的叉子被使用了。如果 00011111,就代表五把叉子都使用了。
    AtomicInteger fork = new AtomicInteger(0);

    //二进制表示,就是00000001 ,00000010, 00000100, 00001000, 0010000
    int[] forks = {1, 2, 4, 8, 16};

    //限制 最多只有4个哲学家去持有叉子
    Semaphore semaphore = new Semaphore(4);

    public DiningPhilosophers05() {

    }

    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        int leftFork = forks[philosopher];
        int rightFork = forks[(philosopher + 1) % forks.length];
        semaphore.acquire();

        while (!fork.compareAndSet(getExpect(leftFork), getUpdate(leftFork))) {
            Thread.sleep(1);
        }
        while (!fork.compareAndSet(getExpect(rightFork), getUpdate(rightFork))) {
            Thread.sleep(1);
        }
        semaphore.release();

        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();

        fork.compareAndSet(getUpdate(leftFork), getExpect(leftFork));
        fork.compareAndSet(getUpdate(rightFork), getExpect(rightFork));
    }

    /**
     * 期望值是fork.get()所对应的当前哲学家的叉子未上锁
     * 取反然后 与运算 , 比如 00000010 取反是 11111101 , 11111101 & 00011111 变成 00011101 ,在原有forkNum基础上当前位变成0
     *
     * @param forkNum 数组下标所对应的值,直接参与位运算即可,因为下标值是特殊的,无需位移
     * @return
     */
    public int getExpect(int forkNum) {
        return fork.get() & ~forkNum;
    }

    /**
     * 目标值是是fork.get()所对应的当前哲学家的叉子上锁
     * 直接|运算,将当前位变成1,表示上锁
     *
     * @param forkNum 数组下标所对应的值,直接参与位运算即可,因为下标值是特殊的,无需位移
     * @return
     */
    public int getUpdate(int forkNum) {
        return fork.get() | forkNum;
    }
}
