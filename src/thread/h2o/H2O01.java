package thread.h2o;


/**
 * @Desc: 缺点: 最慢。
 *        1. 使用了单个锁
 *        2. 没有条件变量,notifyAll唤醒了两个方法都等待的锁,其实hydrogen方法只想唤醒oxygen方法,但是本身方法阻塞的线程也唤醒了。
 * @Author：zhh
 * @Date：2024/11/25 15:11
 */
public class H2O01 {

    int hNum = 0;
    Object h = new Object();

    public H2O01()  {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (h){
            while (hNum == 2){
                h.wait();
            }
            hNum++;
            h.notifyAll();
        }
        releaseHydrogen.run();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (h){
            while (hNum != 2){
                h.wait();
            }
            releaseOxygen.run();
            hNum = 0;
            h.notifyAll();
        }
    }
}
