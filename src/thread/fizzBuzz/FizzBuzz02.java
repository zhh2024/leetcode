package thread.fizzBuzz;


import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @Desc: FizzBuzz的问题: fizz线程 或者buzz 线程会一直阻塞
 * 原因: fizz进入while等待获取权限,但此时number已经执行完循环了, fizz.acquire()就会一直等待。
 *
 * 新问题: number循环完释放release,会多输出,需要控制输出,需要判断num是否符合条件,这时候num不一定是有序的。
 *         fizzbuzz.release();
 *         fizz.release();
 *         buzz.release();
 * @Author：zhh
 * @Date：2024/12/12 17:49
 */
public class FizzBuzz02 {
    private int n;

    private volatile int num = 1;

    public FizzBuzz02(int n) {
        this.n = n;
    }
    Semaphore number = new Semaphore(1);
    Semaphore fizz = new Semaphore(0);
    Semaphore buzz = new Semaphore(0);
    Semaphore fizzbuzz = new Semaphore(0);

    public void fizz(Runnable printFizz) throws InterruptedException {

        while (num <= n){
            fizz.acquire();
            printFizz.run();
            number.release();
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (num <= n){
            buzz.acquire();
            printBuzz.run();
            number.release();
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (num <= n){
            fizzbuzz.acquire();
            printFizzBuzz.run();
            number.release();
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        while (num <= n){
            number.acquire();
            if(num % 3 != 0 && num % 5 !=0 ){
                printNumber.accept(num);
                number.release();
            }else if (num % 3 == 0 && num % 5 ==0 ){
                fizzbuzz.release();
            }else if (num % 3 == 0){
                fizz.release();
            }else {
                buzz.release();
            }
            num++;
        }
        fizzbuzz.release();
        fizz.release();
        buzz.release();
    }


    public static void main(String[] args) {
        Runnable printFizz = () -> {
            System.out.printf("%s", "fizz");
        };
        Runnable printBuzz = () -> {
            System.out.printf("%s", "buzz");
        };
        Runnable printFizzBuzz = () -> {
            System.out.printf("%s", "fizzbuzz");
        };
        IntConsumer intConsumer = value ->  System.out.printf("%d", value);
        FizzBuzz02 fb = new FizzBuzz02(15);
        new Thread(() -> {
            try {
                fb.fizz(printFizz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"fizz").start();
        new Thread(() -> {
            try {
                fb.buzz(printBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"buzz").start();
        new Thread(() -> {
            try {
                fb.fizzbuzz(printFizzBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"fizzbuzz").start();
        new Thread(() -> {
            try {
                fb.number(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"number").start();

    }


}
