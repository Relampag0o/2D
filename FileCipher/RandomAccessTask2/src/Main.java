import java.io.*;
import java.nio.charset.StandardCharsets;


// TODO: ASK ABOUT THE STANDARD UTF8. IT IS NOT ADDING THE TILDES EVEN THO IM USING IT.
public class Main {
    public static void close(Closeable c) {
        try {
            c.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void createIndex(String src, String path) {

        try {


            BufferedReader bfr = new BufferedReader(new FileReader(src,StandardCharsets.ISO_8859_1));
            BufferedWriter bfw = new BufferedWriter((new FileWriter(path, StandardCharsets.UTF_8)));

            String line = "";
            int charCounter = 0;

            while ((line = bfr.readLine()) != null) {
                String[] words = line.split("\\.");

                for (String w : words) {
                    // using trim to delete blank spaces at the beginning and at the end of the line.
                    if (w.length() > 1) {
                        // if the array has a single element, it means it took the '\n';
                        // this conditional compares if the array has more than 1 element, which should be a real phrase instead the '\n';
                        bfw.write(w + "." + '\n');
                        charCounter += w.getBytes().length + '\n';
                    }

                }
            }
            close(bfr);
            close(bfw);


        } catch (Exception e) {
            System.out.println("Error..");
        }
    }

    // wonder if i have to use the index file previously created, but seems like i dont have to.

    public static void readPhrase(int phrase) {

        try {
            RandomAccessFile raf = new RandomAccessFile("C:\\Users\\Jose\\Downloads\\quijote.txt", "r");
            raf.seek(phrase);
            FileReader fr = new FileReader(raf.getFD());
            BufferedReader bfr2 = new BufferedReader(fr);
            System.out.println(bfr2.readLine());
            System.out.println(bfr2.readLine());
        } catch (Exception e) {
            System.out.println("Error..");
        }

    }

    public static void main(String[] args) {
        createIndex(args[0], args[1]);
        readPhrase(190);

    }

}