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
        processes.add(new MyProcess("p2", 1, 4));
        processes.add(new MyProcess("p3", 1, 2));
        processes.add(new MyProcess("p4", 1, 1));

    }

    public void playFCFS() {
        Collections.sort(processes);
        simulation();
    }

    public LinkedList<MyProcess> playSJF() {
        LinkedList<MyProcess> sorted = new LinkedList<MyProcess>();
        int currentTime = 0;

        while (!processes.isEmpty()) {
            MyProcess shortestProcess = null;
            int shortestTime = Integer.MAX_VALUE;
            // i copied that line from somewhere, just wanted to set a high value to this variable
            // so i can update it on the first iteration..
            for (MyProcess proc : processes) {
                if (proc.getArrival() <= currentTime && proc.getExecution() < shortestTime) {
                    shortestProcess = proc;
                    shortestTime = proc.getExecution();
                }
            }
            if (shortestProcess != null) {
                currentTime += shortestProcess.getExecution();
                // didnt want to do sorted.add(shortestProcess)
                // just in case i get any issue doing so.
                // rather prefered to instance a new one.
                sorted.add(new MyProcess(shortestProcess.getName(), shortestProcess.getArrival(), shortestProcess.getExecution()));
                processes.remove(shortestProcess);
            } else
                currentTime++;

        }

        // showing the processes..
        for (MyProcess p : sorted) {
            System.out.println("Process name: " + p.getName() + " arrival time: " + p.getArrival() + " execution time: " + p.getExecution());
        }
        return sorted;
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
/*
        System.out.println("---FCFS---");
        System.out.println("BEFORE SORTING: ");
        sim.showTableProcesses();
        sim.simulation();
        System.out.println("AFTER SORTING: ");
        sim.playFCFS();
        sim.showTableProcesses();
        System.out.println("---SJF---");
*/

        sim.playSJF();


    }


}