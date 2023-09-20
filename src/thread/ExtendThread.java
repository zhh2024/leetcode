package thread;

/**
 * @Author：zhh
 * @Date：2023/9/10 14:06
 *
 */
public class ExtendThread extends Thread {

    @Override
    public void run() {
        System.out.println("继承thread");
    }

    public static void main(String[] args) {
        ExtendThread extendThread = new ExtendThread();
        extendThread.start();
    }
}
