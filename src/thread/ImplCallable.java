package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Author：zhh
 * @Date：2023/9/10 14:10
 * 实现Callable接口 依赖 FutureTask ,好处可以抛出异常且有返回值
 *
 * 可以看出启动线程的根本是Thread 都要搭载Thread才可以启动线程。
 */
public class ImplCallable implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("impl Callable");
        return null;
    }

    public static void main(String[] args) {
        Callable implCallable = new ImplCallable();
        FutureTask futureTask = new FutureTask<>(implCallable);
        new Thread(futureTask).start();
    }
}
