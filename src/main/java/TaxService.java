import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

public class TaxService {
    static final LongAdder taxAccount = new LongAdder();
    static final int pause = 100;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Shop(taxAccount, getArray(), pause));
        thread1.start();
        Thread.sleep(pause);
        Thread thread2 = new Thread(new Shop(taxAccount, getArray(), pause));
        thread2.start();
        Thread.sleep(pause);
        Thread thread3 = new Thread(new Shop(taxAccount, getArray(), pause));
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.printf("Итого общий доход равен %d р.", taxAccount.sum());
    }

    static int[] getArray() {
        Random rnd = new Random();
        int size = 10 + rnd.nextInt(10);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rnd.nextInt(100);
        }
        return array;
    }
}
