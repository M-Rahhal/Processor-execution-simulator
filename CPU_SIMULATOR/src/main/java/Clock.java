public class Clock extends Thread{


    private final int clockCycles;
    private int currentCycle;

    private static Clock clock;

    private Clock(int clockCycles) {
        this.clockCycles = clockCycles;
        currentCycle=1;
    }


    public static Clock getInstance(int clockCycles){
        if (clock==null)
            clock = new Clock(clockCycles);
        return clock;
    }
    public static Clock getInstance(){
        if (clock==null)
            throw new IllegalArgumentException();
        return clock;
    }



    @Override
    public void run(){
        while (currentCycle<=clockCycles) {

            try {
                doCycle();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (currentCycle>clockCycles)
                System.exit(0);

            synchronized (this) {
                notifyAll();
            }

        }

        System.exit(0);
    }




    private void doCycle() throws InterruptedException {
        Thread.sleep(1000);
        currentCycle++;
    }


    public synchronized int getCurrentCycle() {
        return currentCycle;
    }

    @Override
    public String toString() {
        return "C"+currentCycle;
    }
}
