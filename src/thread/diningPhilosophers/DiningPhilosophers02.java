package thread.diningPhilosophers;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc: 哲学家进餐
*  缺点: 极端情况出现死锁
 * 1.五个哲学家同时拿起左筷子,都发现右筷子拿不到,放下左筷子,有可能再次五个哲学家同时拿起左筷子,如此死循环。
 * 2.而且题目只能输出是拿起左右筷子去吃,然后放下左右筷子。不能说拿不到右筷子就放下左筷子,一是不符合题意,二是有可能更慢,放下左筷子重新拿。
 *
 * 为了防止出现死循环,死锁情况,要设置门禁,同时至多四位科学家允许拿筷子。
 *
 * @Author：zhh
 * @Date：2024/12/3 11:09
 */
public class DiningPhilosophers02 {

    //数组下标就是每个叉子的编号
    ReentrantLock[] forks = {
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock()
    };

    public DiningPhilosophers02() {

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

        while (leftFork.tryLock()){
            pickLeftFork.run();
            if (rightFork.tryLock()){
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                leftFork.unlock();
                putRightFork.run();
                rightFork.unlock();
                break;
            }else {
                putLeftFork.run();
                leftFork.unlock();
            }
        }
    }
}
