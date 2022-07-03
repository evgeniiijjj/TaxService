public class Shop implements Runnable {
    private final TaxService service;
    private final int[] gain;
    private final int pause;

    public Shop(TaxService service, int[] gain) {
        this.service = service;
        this.gain = gain;
        this.pause = service.pause;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("Магазин %s открылся\n", name);
        for (int i = 0; i < gain.length; i++) {
            try {
                Thread.sleep(pause);
            } catch (InterruptedException ignored) { }
            service.taxAccount.addAndGet(gain[i]);
            System.out.printf("Магазин %s получил выручку в размере %d р.\n", name, gain[i]);
        }
        System.out.printf("Магазин %s закрылся\n", name);
    }
}
