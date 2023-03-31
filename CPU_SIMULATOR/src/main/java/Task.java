public class Task implements Comparable<Task>{
    private int taskNumber;
    private int taskPriority;
    private int numOfCycles;
    private int arrivalCycleNumber;
    private int finishCycleNumber;
    private static int tasksCounter = 1;

    public Task(int arrivalCycleNumber , int numOfCycles , int taskPriority) {
        this.taskNumber = tasksCounter++;
        this.taskPriority = taskPriority;
        this.numOfCycles = numOfCycles;
        this.arrivalCycleNumber=arrivalCycleNumber;
        this.finishCycleNumber =numOfCycles;
    }

    @Override
    public int compareTo(Task task) {
        if (this.getTaskPriority()==task.getTaskPriority())
            return task.getNumOfCycles() - this.getNumOfCycles();
        return task.getTaskPriority() - this.getTaskPriority() ;
    }
    @Override
    public String toString(){
        return "T"+taskNumber;
    }
    public int getArrivalCycleNumber(){
        return this.arrivalCycleNumber;
    }
    public int getFinishCycleNumber(){
        return this.finishCycleNumber;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }

    public int getTaskNumber() {
        return taskNumber;
    }
}
