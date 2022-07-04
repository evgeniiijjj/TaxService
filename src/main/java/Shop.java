import java.util.concurrent.atomic.LongAdder;

public class Shop implements Runnable {

    private final LongAdder taxAccount;
    private final int[] gain;
    private final int pause;

    public Shop(LongAdder taxAccount, int[] gain, int pause) {
        this.taxAccount = taxAccount;
        this.gain = gain;
        this.pause = pause;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("Магазин %s открылся\n", name);
        for (int i = 0; i < gain.length; i++) {
            try {
                Thread.sleep(pause);
            } catch (InterruptedException ignored) { }
            taxAccount.add(gain[i]);
            System.out.printf("Магазин %s получил выручку в размере %d р.\n", name, gain[i]);
        }
        System.out.printf("Магазин %s закрылся\n", name);
    }
}
