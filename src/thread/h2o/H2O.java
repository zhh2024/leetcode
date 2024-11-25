package thread.h2o;


import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc: 现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。
 * 存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。
 * 氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。
 * 这些线程应该三三成组突破屏障并能立即组合产生一个水分子。
 * 你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。
 * 换句话说:
 * 如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
 * 如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。
 * 书写满足这些限制条件的氢、氧线程同步代码。
 * <p>
 * 示例 1:
 * 输入: water = "HOH"
 * 输出: "HHO"
 * 解释: "HOH" 和 "OHH" 依然都是有效解。
 * <p>
 * 示例 2:
 * 输入: water = "OOHHHH"
 * 输出: "HHOHHO"
 * 解释: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" 和 "OHHOHH" 依然都是有效解。
 *
 *
 * 解题思想: A和B共同去完成一件事,需要互相配合,一般解题都是两个条件变量,A等待,释放B。B等待,释放A。
 * 1. 使用一个容器去接收H元素, H方法往容器中放H元素时,需要上锁保持同步,不然同时涌入容器会超过个数
 * 2. 当H元素达到2时,H方法应该阻塞,唤醒O方法,去消耗H元素
 * 3. O方法,需要判断H元素是否达到2个,如果没有达到,阻塞。如果达到了2个,容器置空,唤醒H方法。

 * 缺点:慢。 使用了单个锁和条件变量来管理两个方法的同步.
 * 两个方法,不应该互相受影响，导致交叉执行,完全可以并行执行。
 * @Author：zhh
 * @Date：2024/11/19 14:42
 */
public class H2O {

    int num = 0;
    ReentrantLock reentrantLock = new ReentrantLock();
    Condition h = reentrantLock.newCondition();
    Condition o = reentrantLock.newCondition();

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        reentrantLock.lock();
        while (num == 2) {
            h.await();
        }
        num++;
        o.signal();
        releaseHydrogen.run();
        reentrantLock.unlock();
    }


    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        reentrantLock.lock();
        while (num != 2) {
            o.await();
        }
        num = 0;
        h.signal();
        releaseOxygen.run();
        reentrantLock.unlock();
    }
}
