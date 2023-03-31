

import java.util.PriorityQueue;
import java.util.Queue;

public abstract class Scheduler extends Thread{

    private Queue<Task> waitingQueue;
    private Clock clock;
    private Processor[] processors ;

    public Scheduler(Processor[] processors , Queue<Task> queue){
        this.waitingQueue = queue;
        this.processors = processors;
        clock = Clock.getInstance();
    }

    public abstract void schedule();
    public abstract void addTask(Task task);

    public synchronized Queue<Task> getWaitingQueue(){
        return this.waitingQueue;
    };

    public Processor[] getProcessors() {
        return processors;
    }


    public Clock getClock() {
        return clock;
    }
}
