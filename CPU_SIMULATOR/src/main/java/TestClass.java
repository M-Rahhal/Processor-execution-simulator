import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TestClass {

    public static void main(String[] args) throws  FileNotFoundException {


        if (args.length!=3)
            throw new IllegalArgumentException("The size of the args must be 3\n" +
                    "The first argument is an integer, which represents the number of processors.\n" +
                    "The second argument is an integer, which represents the total number of clock cycles in the" +
                    "simulation.\nThe third argument is string, which represents the path to the text file that contains tasks’" +
                    "information");
        int numOfProcessors;
        int numOfCycles;
        try {
             numOfProcessors = Integer.parseInt(args[0]);
             numOfCycles = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException numberFormatException){
            throw new IllegalArgumentException("The 2nd OR the 3rd argument is not a number");
        }
        String path  = args[2];
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        List<String> list = reader.lines().collect(Collectors.toList());
        List<Task> tasks = new LinkedList<>();
        for (int i =1; i < list.size(); i++){
            String[] line = list.get(i).split(" ");
            tasks.add(new Task(Integer.parseInt(line[0]) ,Integer.parseInt(line[1]) ,Integer.parseInt(line[2])));
        }



        /*
         * The only available scheduling type is the LONGEST JOB WITH PRIORITY
         */
        Simulator s = new Simulator(numOfCycles ,numOfProcessors , tasks , "LJFWithPriority");
        s.start();



    }
}
