package thread;

/**
 * @Author：zhh
 * @Date：2023/9/10 14:09
 * 要依赖Thread
 */
public class ImplRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("implRunnable");
    }

    public static void main(String[] args) {
        new Thread(new ImplRunnable()).start();
    }
}
