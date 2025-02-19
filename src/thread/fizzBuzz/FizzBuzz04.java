package thread.fizzBuzz;


import java.util.function.IntConsumer;

/**
 * @Desc: 方法2,每个方法都去执行,因为每个方法都有各自的不同的条件,只需要判断符合条件就打印输出
 * 1. 每个方法需要共享一个变量num,这样才能保证不同的条件有序执行
 * 2. num作为共享变量会发生线程不安全 比如: num此时为8 , 当number方法执行完num++,但是此时 fizzbuzz方法走到 num % 3 == 0 符合条件的，但是线程切换了
 *    fizz方法执行完 又num++,fizzbuzz线程切回来又执行num % 5 == 0 符合条件，打印了,但是此时num是10,显然不符合fizzbuzz方法。
 * @Author：zhh
 * @Date：2024/12/12 17:49
 */
public class FizzBuzz04 {

    private int n;

    private volatile  int num = 1;

    public FizzBuzz04(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        while (num <= n) {
            if(num % 3 == 0 && num % 5 !=0 ){
                printFizz.run();
                num++;
            }
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (num <= n) {
            if(num % 3 != 0 && num % 5 ==0 ){
                printBuzz.run();
                num++;
            }
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (num <= n) {
            if (num % 3 == 0 && num % 5 ==0 ){
                printFizzBuzz.run();
                num++;
            }
        }
    }

    /**
     * number释放许可
     * @param printNumber
     * @throws InterruptedException
     */
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (num <= n) {
            if(num % 3 != 0 && num % 5 !=0 ){
                printNumber.accept(num);
                num++;
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
        FizzBuzz04 fb = new FizzBuzz04(15);
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
