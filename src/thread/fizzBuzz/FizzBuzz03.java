package thread.fizzBuzz;


import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @Desc: 解决Fizz02的问题,取消掉num
 * 之前循环进来就fizz.acquire(), 导致不一定会获取到许可造成阻塞。
 * 新增方法条件判断,只有符合条件了再fizz.acquire(),number必然会释放许可,这样就不会出现fizz.acquire()可能阻塞的情况了
 * 本质就是,什么条件释放的,什么条件就获取,就一一对应,就不会出现额外获取,造成阻塞。
 * @Author：zhh
 * @Date：2024/12/12 17:49
 */
public class FizzBuzz03 {

    private int n;

    public FizzBuzz03(int n) {
        this.n = n;
    }
    Semaphore number = new Semaphore(1);
    Semaphore fizz = new Semaphore(0);
    Semaphore buzz = new Semaphore(0);
    Semaphore fizzbuzz = new Semaphore(0);

    public void fizz(Runnable printFizz) throws InterruptedException {

        for (int num = 1; num <= n; num++) {
            //卡住
            if (num % 3 == 0 && num % 5!= 0){
                fizz.acquire();
                printFizz.run();
                number.release();
            }
        }

    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int num = 1; num <= n; num++) {
            if (num % 3 != 0 && num % 5 == 0){
                buzz.acquire();
                printBuzz.run();
                number.release();
            }
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int num = 1; num <= n; num++) {
            if (num % 3 == 0 && num % 5 == 0) {
                fizzbuzz.acquire();
                printFizzBuzz.run();
                number.release();
            }
        }
    }

    /**
     * number释放许可
     * @param printNumber
     * @throws InterruptedException
     */
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int num = 1; num <= n; num++) {
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
        FizzBuzz03 fb = new FizzBuzz03(15);
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
