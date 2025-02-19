package thread.fizzBuzz;


import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @Desc: 方法2,每个方法都去执行,因为每个方法都有各自的不同的条件,只需要判断符合条件就打印输出
   解决04的问题:
   1. 需要在判断条件之前上锁, number++完解锁
   2. 可以double check 也可以 while true
   与方法1比较
   1. 每个方法都while 循环去获取锁,然后释放,有可能会出现 某个方法空转,一直占用锁,而不符合条件没法执行,造成时间资源浪费

   推荐使用方法1 FizzBuzz03,每个方法按照顺序调用。
 * @Author：zhh
 * @Date：2024/12/12 17:49
 */
public class FizzBuzz05 {

    private int n;

    private volatile  int num = 1;

    Semaphore semaphore = new Semaphore(1);

    public FizzBuzz05(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            try {
                //读数据之前上锁
                semaphore.acquire();
                if (num > n){
                    return;
                }
                if(num % 3 == 0 && num % 5 !=0 ){
                    printFizz.run();
                    num++;
                }
            } finally {
                //不管是返回还是打印,都解锁
                semaphore.release();
            }
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (num <= n) {
            //double check
            semaphore.acquire();
            if(num <= n && num % 3 != 0 && num % 5 ==0 ){
                printBuzz.run();
                num++;
            }
            semaphore.release();
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (num <= n) {
            semaphore.acquire();
            if (num <= n && num % 3 == 0 && num % 5 ==0 ){
                printFizzBuzz.run();
                num++;

            }
            semaphore.release();
        }
    }

    /**
     * number释放许可
     * @param printNumber
     * @throws InterruptedException
     */
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (num <= n){
            semaphore.acquire();
            if(num <= n && num % 3 != 0 && num % 5 !=0 ){
                printNumber.accept(num);
                num++;
            }
            semaphore.release();
        }
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
        FizzBuzz05 fb = new FizzBuzz05(15);
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





