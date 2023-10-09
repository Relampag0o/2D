package org.example;

import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

        public static void createIndex (String src, String dst){
            File f = new File(src);
            File f2 = new File(dst);
            try(BufferedReader bfr = new BufferedReader(new FileReader(f));
                BufferedWriter bfw = new BufferedWriter(new FileWriter(dst))){
            String line =bfr.readLine();
            int index = line.getBytes().length;
            bfw.write("DNI: " + index + '\n');
                System.out.println("DNI: " + index + '\n');

            while ((line=bfr.readLine())!=null){
                String[] words = line.split(";");
                index+=line.getBytes().length;
                bfw.write("DNI: " + words[0] + "" + index + '\n');
                System.out.println("DNI: " + words[0] + "" + index + '\n');



            }



            }catch(Exception e){
                System.out.println("Error...");
            }



        }

        public void searchInfo (String dni, String indexFile){

        }

    public static void main(String[] args) {


            //App app = new App();
            if (args.length == 3) {
                switch (args[0]) {
                    case "-g":
                        System.out.println(args[1]+args[2]);
                        createIndex(args[1],args[2]);
                        break;
                    case "-f":
                        //app.buscarInfo(args[1], args[2]);
                        break;
                    default:
                        System.err.println("Not supported opperattion");
                        break;
                }
            } else {
                System.err.println("The amount of args is not valid. Try again.");
            }
        }
    }

