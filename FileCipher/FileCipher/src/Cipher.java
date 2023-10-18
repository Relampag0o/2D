// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class Cipher {

    private int[] swaps;

    public Cipher(int password) {
        swaps = new int[256];
        Arrays.fill(swaps, -1);
        shuffle(password);
    }

    public void showSwaps() {
        for (int i = 0; i < swaps.length; i++) {
            System.out.println("[" + i + "]" + " " + swaps[i] + " char: " + (char) swaps[i]);
        }
    }

    public void encrypt(File src, File dst) {
        if (src.exists() && src.isFile()) {
            try {
                BufferedReader bfr = new BufferedReader(new FileReader(src));
                BufferedWriter bfw = new BufferedWriter(new FileWriter(dst));

                String line = "";

                while ((line = bfr.readLine()) != null) {
                    for (int i = 0; i < line.length(); i++) {
                        System.out.println("Swapping " + line.charAt(i) + " " + "by" + " " + swaps[line.charAt(i)]);
                        bfw.write(swaps[line.charAt(i)]);

                    }
                    bfw.newLine();
                }


                close(bfr);
                close(bfw);

            } catch (Exception e) {
                System.out.println("There was an error with the file..");
            }


        }
    }

    public void decrypt(File src, File dst) {
        if (src.exists() && src.isFile()) {
            try {
                BufferedReader bfr = new BufferedReader(new FileReader(src));
                BufferedWriter bfw = new BufferedWriter(new FileWriter(dst));

                String line = "";

                while ((line = bfr.readLine()) != null) {
                    for (int i = 0; i < line.length(); i++) {
                        for (int j = 0; j < swaps.length; j++) {
                            if (swaps[j] == line.charAt(i)) {
                                bfw.write((char) j);
                                break;
                            }
                        }
                    }
                    bfw.newLine();
                }


                close(bfr);
                close(bfw);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("There was an error with the file..");
            }


        }
    }

    private void shuffle(int password) {
        Random r = new Random(password);

        for (int i = 0; i < swaps.length; i++) {
            int random = r.nextInt(swaps.length);
            while (find(random)) {

                random = r.nextInt(swaps.length);
            }
            swaps[i] = random;
        }
    }

    private boolean find(int value) {
        for (int i = 0; i < swaps.length; i++) {
            if (swaps[i] == value)
                return true;
        }
        return false;
    }

    public static void close(Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (IOException e) {
            //log the exception
        }
    }

    public static void main(String[] args) {

        if (args.length == 4) {
            Cipher cipher = new Cipher(args[1].hashCode());
            cipher.showSwaps();
            switch (args[0]) {
                case "-c":
                    System.out.println("Encrypting file : '" + args[2] + "' with password: " + args[1]);
                    cipher.encrypt(new File(args[2]), new File(args[3]));
                    System.out.println("Encrypted file: " + args[3]);
                    break;
                case "-d":
                    System.out.println("Decrypting file:'" + args[2] + "' with password: " + args[1]);
                    cipher.decrypt(new File(args[2]), new File(args[3]));
                    System.out.println("Decrypted file: " + args[3]);
                    break;
                default:
                    System.out.println("Not supported operation.");
                    break;
            }
        } else {
            System.err.println("You can only use 4 arguments.");
            System.out.println("Encrypting: -c password srcFile secretFile");
            System.out.println("Decrypting: -d password secretFile dstFile");
        }

    }
}