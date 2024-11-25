package thread.h2o;


import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc: 缺点: 使用两把锁,会有问题, 唤醒别的锁的等待线程,必须持有当前锁,很容易造成死锁。
 *             因为hLock ,想去拿oLock 。而oLock又要去拿hLock
 * @Author：zhh
 * @Date：2024/11/19 14:42
 */
public class H2O02 {

    LinkedList<String> hStack = new LinkedList<>();
    LinkedList<String> oStack = new LinkedList<>();
    ReentrantLock hLock = new ReentrantLock();
    ReentrantLock oLock = new ReentrantLock();
    Condition h = hLock.newCondition();
    Condition o = oLock.newCondition();
    public H2O02()  {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hLock.lock();
        while (hStack.size() >= 2){
            h.await();
        }
        hStack.push("H");
        oLock.lock();
        o.signal();
        oLock.unlock();
        releaseHydrogen.run();
        hLock.unlock();
    }


    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oLock.lock();
        while (!oStack.isEmpty()){
            if(hStack.size() >= 2){
                //组合
                oStack.pop();
                hStack.pop();
                hStack.pop();
                hLock.lock();
                h.signal();
                hLock.unlock();
            } else {
                o.await();
            }
        }
        oStack.push("O");
        oLock.unlock();
        releaseOxygen.run();
    }
}
