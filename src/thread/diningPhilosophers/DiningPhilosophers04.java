package thread.diningPhilosophers;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc: 哲学家进餐
 * 而当5个哲学家都左手持有其左边的叉子 或 当5个哲学家都右手持有其右边的叉子时，会发生死锁。
 * <p>
 * 设计1个避免发生上述情况发生的策略即可。根据哲学家编号分奇偶,奇的拿左筷子,偶的拿右筷子。
 * 1. 下家起手同时竞争同一支筷子,一个哲学家拿到,另一个哲学家拿不到,就一支筷子都拿不到了(起手失败),就不会出现每个人同时持有一支筷子所出现的死锁了。
 * 2. 拿到起手筷子之后,会拿另一双,如果拿不到,上家会拿到最后一支筷子组成一对,肯定能吃上饭,吃完饭就会释放。
 * <p>
 * 不需要设置门禁,也能解决死锁问题。
 * @Author：zhh
 * @Date：2024/12/3 11:09
 */
public class DiningPhilosophers04 {

    //数组下标就是每个叉子的编号
    ReentrantLock[] forks = {
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock(),
            new ReentrantLock()
    };

    public DiningPhilosophers04() {

    }


    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        //1. 因为五个叉子放到了数组中,根据哲学家编号,哲学家只能取编号附近的左右叉子,4号哲学家取数组最左侧的叉子。
        ReentrantLock leftFork = forks[(philosopher + 1) % forks.length];
        ReentrantLock rightFork = forks[philosopher];
        //2. 根据哲学家编号分奇偶,奇的拿左筷子,偶的拿右筷子
        if (philosopher % 2 != 0) {
            leftFork.lock();
            rightFork.lock();
        } else {
            rightFork.lock();
            leftFork.lock();
        }
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();

        leftFork.unlock();
        rightFork.unlock();
    }
}
