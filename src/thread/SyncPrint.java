package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author：zhh
 * @Date：2023/9/10 12:04
 *
 * 同步原语的交替打印思路:
 * (1) 设定一个flag,如果当前线程满足flag,执行打印操作，执行完毕flag置为下一个线程的状态,并且唤醒所有线程
 * (2) 防止被虚假唤醒需要while循环去判断flag是否是当前线程满足状态
 *     如果flag不满足条件,但是不能一直空转浪费cpu资源，当前线程要等待。
 * (3) 如果线程同时执行会出现 死锁问题 。
 *     比如 同时进入，只有一个线程会满足，执行，唤醒下一个线程，但是如果此时下一个线程正好执行中呢
 *     (且正好判断失效，正准备wait，但是已经提前notify)？唤醒失效，当前线程和
 *     下一个线程都会进入wait，导致后面的都执行不了！！！
 *
 * (4) 解决办法就是当其中一个线程满足条件，那么别的线程都不能执行都应该被阻塞掉才可以，那么执行完唤醒就生效了。
 *     所以notify和wait 必须绑定锁 sync!!!
 *（5）wait会释放掉锁,所以sync应该包含while条件。这样执行下一次循环就不会再获取锁了。
 *
 *
 *  上面思路是会一直打印的,如果限定打印次数呢？ 比如每个线程执行3次或者五次呢？
 *  比如打印次数为n, 每个线程打印一次 n--;
 *  while循环条件就是 n>0 就可以 。
 *
 *  要是一组为打印一次，那么 最后一个线程去--即可，就不用每个线程去--
 *
 *  这种思路可扩展性强！
 *
 */
public class SyncPrint {
    public SyncPrint() {

    }
    int flag = 1;
    int printCount = 6;
    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (this){
            while (printCount>0) {
                if(flag == 1){
                    // printFirst.run() outputs "first". Do not change or remove this line.
                    printFirst.run();
                    flag = 2;
                    printCount --;
                    notifyAll();
                }else {
                    wait();
                }
            }
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (this){
            while (printCount>0) {
                if(flag == 2){
                    // printSecond.run() outputs "second". Do not change or remove this line.
                    printSecond.run();
                    flag = 3;
                    printCount --;
                    notifyAll();
                }else {
                    wait();
                }
            }
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (this){
            while (printCount>0) {
                if(flag == 3){
                    // printThird.run() outputs "third". Do not change or remove this line.
                    printThird.run();
                    flag = 1;
                    printCount --;
                    notifyAll();
                }else {
                    wait();
                }
            }
        }
    }

    public static void main(String[] args) {
        SyncPrint foo = new SyncPrint();

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
