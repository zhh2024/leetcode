package thread.fizzBuzz;


import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @Desc: 解题思路:
 * number循环控制输出,释放许可, 其他线程循环等待获取许可,获取到执行完毕,释放number许可。
 * @Author：zhh
 * @Date：2024/12/12 17:49
 */
public class FizzBuzz {
    private int n;

    private volatile int num = 1;

    public FizzBuzz(int n) {
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
        FizzBuzz fb = new FizzBuzz(15);
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
