import java.util.PriorityQueue;

public class LJFWithPriority extends Scheduler {



    public LJFWithPriority(Processor[] processorList){
        super(processorList , new PriorityQueue<>());
    }


    @Override
    public void run() {
        schedule();
    }

    @Override
    public void schedule() {

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {e.printStackTrace();}
            for (int i =0 ; i< getProcessors().length ; i++) {
                if (getProcessors()[i]!=null)
                    if (getProcessors()[i].isAlive())
                        continue;
                if (getWaitingQueue().isEmpty()){
                    getProcessors()[i] = null;
                    continue;
                }
                getProcessors()[i] = new Processor(i ,getWaitingQueue().poll() , getClock().getCurrentCycle()-1 );
                getProcessors()[i].start();
            }
            synchronized (getClock()) {
                try {
                    getClock().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public synchronized void addTask(Task task){
            getWaitingQueue().add(task);
    }




}
