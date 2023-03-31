public class Processor extends Thread{



    private int processorID;
    private Task task;
    private Clock clock ;
    private int creationCycle;


    public Processor(int id , Task task , int creationCycle){
        this.task=task;
        this.processorID = id;
        this.clock = Clock.getInstance();
        this.creationCycle = creationCycle;

    }



    @Override
    public void run() {
        while (true) {
            if (task == null)
                throw new IllegalArgumentException("Task cannot be null -> processor no. \n" + this.toString());

            if (clock.getCurrentCycle() > creationCycle+task.getFinishCycleNumber()) {
                return;
            }

            synchronized (clock) {
                try {
                    clock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public String toString() {
        return "P"+this.processorID;
    }


    public Task getTask() {
        return task;
    }

}
