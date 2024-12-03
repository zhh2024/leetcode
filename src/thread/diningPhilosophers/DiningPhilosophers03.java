package thread.diningPhilosophers;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc: 哲学家进餐
 * 设置门禁(信号量) 同一时刻只允许至多四位哲学家拿筷子。总有一位哲学家能拿到成对筷子,吃完释放筷子。
 * @Author：zhh
 * @Date：2024/12/3 11:09
 */
public class DiningPhilosophers03 {

    //数组下标就是每个叉子的编号
    ReentrantLock[] forks = {
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock()
    };

    Semaphore semaphore = new Semaphore(3);

    public DiningPhilosophers03() {

    }


    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        //1. 因为五个叉子放到了数组中,根据哲学家编号,哲学家只能取编号附近的左右叉子,4号哲学家取数组最左侧的叉子。
        ReentrantLock leftFork = forks[(philosopher+1) % forks.length];
        ReentrantLock rightFork = forks[philosopher];

        //2. 能不能拿到叉子,取决于能不能获取许可
        semaphore.acquire();
        //3. 拿到许可,等待拿到叉子
        leftFork.lock();
        rightFork.lock();
        //4. 拿到叉子,既可以释放许可了。
        semaphore.release();

        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        leftFork.unlock();
        putRightFork.run();
        rightFork.unlock();

    }
}
