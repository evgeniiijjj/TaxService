import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

public class TaxService {
    final LongAdder taxAccount = new LongAdder();
    final int pause = 100;

    public static void main(String[] args) throws InterruptedException {
        TaxService service = new TaxService();
        Thread thread1 = new Thread(new Shop(service, service.getArray()));
        thread1.start();
        Thread.sleep(service.pause);
        Thread thread2 = new Thread(new Shop(service, service.getArray()));
        thread2.start();
        Thread.sleep(service.pause);
        Thread thread3 = new Thread(new Shop(service, service.getArray()));
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.printf("Итого общий доход равен %d р.", service.taxAccount.sum());
    }

    int[] getArray() {
        Random rnd = new Random();
        int size = 10 + rnd.nextInt(10);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rnd.nextInt(100);
        }
        return array;
    }
}
