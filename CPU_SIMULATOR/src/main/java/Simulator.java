import java.util.List;

public class Simulator extends Thread{
    private Clock clock;
    private Scheduler scheduler;
    private String[] processors;
    private List<Task> tasks ;
    private SchedulerFactory builder;

    public Simulator(int clockCyclesNumber , int processorsNumber , List<Task> tasks , String schedulingType){
        clock = Clock.getInstance(clockCyclesNumber);
        processors = new String[processorsNumber+1];
        this.tasks = tasks;
        builder = new SchedulerFactory();
        Processor[] processorsList = new Processor[processorsNumber];

        for (int i = 1; i<=processorsNumber; i++)
            processors[i] = "P"+i;

        this.scheduler = builder.getSchedulingAlgo( schedulingType, processorsList);
    }



    @Override
    public void run(){
        this.clock.start();
        this.scheduler.start();
        printReportColumns();

        while (true) {
            addArriverTasks();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printCurrentCycleReport();
            synchronized (clock) {
                try {
                    clock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private void printReportColumns(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%12s " , "Clock Cycles "));
        for (int i =1;  i < processors.length ; i++)
            builder.append(processors[i]+"     ");

        System.out.println(builder.toString());
    }

    private void printCurrentCycleReport(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%12s " , clock.toString()));
        for (Processor processor :scheduler.getProcessors()) {
            if (processor==null)
                builder.append(String.format("%7s","XX" + "    "));
            else
                builder.append(String.format("%7s",processor.getTask().toString() + "    "));
        }

        builder.append("           available tasks in the waiting queue----> ");
        for (Task t : scheduler.getWaitingQueue())
            builder.append(t.toString()+" ");

        System.out.println(builder.toString());
    }

    private void addArriverTasks(){
        for (Task task : tasks) {
            if (task == null)
                continue;
            if (task.getArrivalCycleNumber()==clock.getCurrentCycle())
                addTaskToScheduler(task);
        }
    }
    private void addTaskToScheduler(Task task){
        scheduler.addTask(task);
    }




}
