package thread;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author：zhh
 * @Date：2023/9/10 22:04
 *
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 *
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 *
 * 题目的本意是要并发执行， 比如一个字符串，一个字符处理为10s,10个字符就是100s
 * 按照不同字符的处理规则，不同线程去处理，那么就是10s,处理完10个字符。
 *
 *
 *
 */
public class FizzBuzz {
    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        printFizz.run();
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        printBuzz.run();
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        printFizzBuzz.run();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(Runnable  printNumber) throws InterruptedException {
        printNumber.run();

    }

    public static void main(String[] args) {
        int n =10;
        FizzBuzz fizzBuzz = new FizzBuzz(n);
        //其实应该分批去执行
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < n; i++) {
            if(i%3 !=0 && i%5 !=0){
                final int num = i;
                executorService.submit(() -> {
                    try {
                        fizzBuzz.number(() -> System.out.println(num));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }else if(i%5 == 0 || i%3 ==0){
                executorService.submit(() -> {
                    try {
                        fizzBuzz.fizzbuzz(() -> System.out.println("fizzbuzz"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }else if(i%5 == 0 ){
                executorService.submit(() -> {
                    try {
                        fizzBuzz.buzz(() -> System.out.println("buzz"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

            }else {
                executorService.submit(() -> {
                    try {
                        fizzBuzz.fizz(() -> System.out.println("fizz"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        executorService.shutdown();
    }
}
