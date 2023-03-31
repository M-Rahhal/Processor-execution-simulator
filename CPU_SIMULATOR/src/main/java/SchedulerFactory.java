public class SchedulerFactory {

    public Scheduler getSchedulingAlgo(String SchedulerName , Processor[] processors){
        if (SchedulerName.equals("LJFWithPriority"))
            return new LJFWithPriority(processors);

        throw new IllegalArgumentException("The Scheduler you are trying to request is not correct");
    }
}
