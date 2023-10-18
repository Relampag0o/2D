import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Simulator {
    public ArrayList<MyProcess> processes;


    public Simulator() {
        this.processes = new ArrayList<MyProcess>();

    }

    public void generateProcesses() {
/*
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            processes.add(new MyProcess("pid_" + i, r.nextInt(7), r.nextInt(5)));
        }
*/

        processes.add(new MyProcess("p1", 0, 3));
        processes.add(new MyProcess("p2", 2, 4));
        processes.add(new MyProcess("p3", 3, 2));
        processes.add(new MyProcess("p4", 3, 1));

    }

    public void playFCFS() {
        Collections.sort(processes);
        simulation();
    }

    public void playSJF() {
        LinkedList<MyProcess> sortedProcesses = new LinkedList<MyProcess>();
        int currentTime = 0;

        while (!processes.isEmpty()) {
            MyProcess shortestProcess = null;
            int shortestTime = Integer.MAX_VALUE;
            for (MyProcess proc : processes) {
                if (proc.getArrival() <= currentTime && proc.getExecution() < shortestTime) {
                    shortestProcess = proc;
                    shortestTime = proc.getExecution();
                }
            }
            if (shortestProcess != null) {
                currentTime += shortestProcess.getExecution();
                sortedProcesses.add(new MyProcess(shortestProcess.getName(), shortestProcess.getArrival(), shortestProcess.getExecution()));
                processes.remove(shortestProcess);
            } else
                currentTime++;
        }
        processes.clear();
        processes.addAll(sortedProcesses);
        simulation();
    }


    public void simulation() {
        String command = "java";
        String mainClass = "MyProcess";
        for (MyProcess proc : processes) {
            String execTime = proc.getExecution() + "";
            ProcessBuilder processBuilder = new ProcessBuilder(command, mainClass, execTime);
            try {
                System.out.println("Executing process:  " + proc.getName());
                Process process = processBuilder.start();
                System.out.println("Waiting for process: " + proc.getName());
                int exitCode = process.waitFor();
                System.out.println("Process " + proc.getName() + " has finished. ExitCode:" + exitCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showTableProcesses() {
        System.out.println("PID\t Arrival\t Execution");
        for (MyProcess proc : processes) {
            System.out.print(proc.getName() + "\t\t");
            System.out.print(proc.getArrival() + "\t\t");
            System.out.println(proc.getExecution() + "\t\t");
        }
    }


    public static void main(String[] args) {

        Simulator sim = new Simulator();
        sim.generateProcesses();

        System.out.println("PLAYING FCFS:");
        sim.playFCFS();

        System.out.println("-----");

        System.out.println("PLAYING SJF: ");
        sim.playSJF();


    }


}