package cc.readcode.java.threadlocal;
import java.util.function.Supplier;

public class MainTest {

    public static void main(String[] args) {
        Bank1 bank = new Bank1();
        Thread xMThread = new Thread(() -> bank.deposit(200), "Ming");
        Thread xGThread = new Thread(() -> bank.deposit(200), "Gang");
        Thread xHThread = new Thread(() -> bank.deposit(200), "Hong");
        xMThread.start();
        xGThread.start();
        xHThread.start();
    }
}

class Bank1 {

    // 初始化账户余额为 100
    ThreadLocal<Integer> account = ThreadLocal.withInitial(new Supplier<Integer>() {
        @Override
        public Integer get() {
            return 1000;
        }
    });

    public void deposit(int money) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "--current amt:" + account.get());
        account.set(account.get() + money);
        System.out.println(threadName + "-save " + money + " count amt after save is:" + account.get());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}