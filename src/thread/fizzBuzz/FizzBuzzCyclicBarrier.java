package thread.fizzBuzz;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @Desc: CyclicBarrier ,思路还是信号量的思路,number方法放行
 *
 * @Author：zhh
 * @Date：2024/12/12 17:49
 */
public class FizzBuzzCyclicBarrier {

    private int n;

    CyclicBarrier number = new CyclicBarrier(2);

    CyclicBarrier fizz = new CyclicBarrier(2);

    CyclicBarrier buzz = new CyclicBarrier(2);

    CyclicBarrier fizzbuzz = new CyclicBarrier(2);

    public FizzBuzzCyclicBarrier(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException{

        for (int num = 1; num <= n; num++) {
            //卡住
            if (num % 3 == 0 && num % 5!= 0){
                try {
                    fizz.await();
                    printFizz.run();
                    number.await();
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int num = 1; num <= n; num++) {
            if (num % 3 != 0 && num % 5 == 0){
                try {
                    buzz.await();
                    printBuzz.run();
                    number.await();
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int num = 1; num <= n; num++) {
            if (num % 3 == 0 && num % 5 == 0) {
                try {
                    fizzbuzz.await();
                    printFizzBuzz.run();
                    number.await();
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }


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
            if(num % 3 != 0 && num % 5 !=0 ){
                printNumber.accept(num);
            }else if (num % 3 == 0 && num % 5 ==0 ){
                try {
                    fizzbuzz.await();
                    number.await();
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }else if (num % 3 == 0){
                try {
                    fizz.await();
                    number.await();
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }else {
                try {
                    buzz.await();
                    number.await();
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
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
        FizzBuzzCyclicBarrier fb = new FizzBuzzCyclicBarrier(15);
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
