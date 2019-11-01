package cc.readcode.java.threadlocal;

public class MainTest01 {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Thread xMThread = new Thread(() -> bank.deposit(200), "Ming");
        Thread xGThread = new Thread(() -> bank.deposit(200), "Gang");
        Thread xHThread = new Thread(() -> bank.deposit(200), "Hong");
        xMThread.start();
        xGThread.start();
        xHThread.start();
    }
}

class Bank {

    private int money = 1000;

    public void deposit(int money) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "--current amt:" + this.money);
        this.money += money;
        System.out.println(threadName + "--save " + money + " count amt after save is:" + this.money);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
