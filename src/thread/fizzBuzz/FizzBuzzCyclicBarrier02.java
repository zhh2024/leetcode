package thread.fizzBuzz;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.IntConsumer;

/**
 * @Desc: 优化CyclicBarrier , 控制每个方法内的for循环执行一次为一次CyclicBarrier,符合条件的就输出,然后继续下一次for循环。
 *
 * @Author：zhh
 * @Date：2024/12/12 17:49
 */
public class FizzBuzzCyclicBarrier02 {

    private int n;

    CyclicBarrier cyclicBarrier = new CyclicBarrier(4);


    public FizzBuzzCyclicBarrier02(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException{

        for (int num = 1; num <= n; num++) {
            if (num % 3 == 0 && num % 5!= 0){
                printFizz.run();
            }
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int num = 1; num <= n; num++) {
            if (num % 3 != 0 && num % 5 == 0){
                printBuzz.run();
            }
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int num = 1; num <= n; num++) {
            if (num % 3 == 0 && num % 5 == 0) {
                printFizzBuzz.run();
            }
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
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
            if (num % 3 != 0 && num % 5 != 0) {
                printNumber.accept(num);
            }
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
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
        FizzBuzzCyclicBarrier02 fb = new FizzBuzzCyclicBarrier02(15);
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
