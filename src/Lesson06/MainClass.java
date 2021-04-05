package Lesson06;

import java.io.*;
import java.util.Random;

public class MainClass {
    public static void main (String [] args) {
        String fileName = "src/Lesson06/Home Work1.txt";
        String fileName2 = "src/Lesson06/Home Work2.txt";
        Boolean selector = false;
        //String text = "";
        //String text2 = "";
        //int count = 100;
        //randomText(text, count);
        String text = "STAR WARS\n Episode 4\n" + "New Hope\n" +
        "There is a civil war. Spaceships\n" +
        "insurgents striking from a secret base,\n" +
        "won the first victory in the battle\n" + "with the sinister Galactic Empire.\n";
        String text2 = "During the battle, the rebel scouts\nmanaged to steal secret plans,\n" +
                "associated with the main weapon of the Empire -\n" +
                "Death Star, armored space\n" +
        "a station capable of destroying an entire planet.\n" +
        "Princess Chased by Imperial Agents\n" +
        "Leia hurries home in her starship.";
        //text2 = text2 + randomText(text2, count);
        writeToFile(fileName, text, selector);
        writeToFile(fileName2, text2,selector);
        mixToFile(fileName, fileName2);
    }
    /*private static String randomText(String text1, int count){
        Random random = new Random();

        for (int i = 0; i < count; i++){
            int rand = random.nextInt(58) + 65;
            if (rand > 90 && rand < 97){
                text1 = text1 + 'X';
            }else text1 = text1 + (char)rand;
        }
        return text1;


    }*/
    private static void writeToFile (String nameFile, String text, boolean selector){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(nameFile, selector);
            PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.print(text);
            printStream.flush();
            printStream.close();
            fileOutputStream.close();
        }catch (FileNotFoundException fileNotFoundException) {
            System.out.println("ERROR: file not found.");
        }catch (IOException exception) {
            System.out.println("ERROR: IOExeption.");
        }

    }
    private static void mixToFile (String file1, String file2){
        try {
            FileInputStream file1InputStream = new FileInputStream(file1);
            String mixedFile = "src/Lesson06/MixFile.txt";
            int tmp;
            int ss = 0;
            String bob;
            boolean sel = false;

            while ((tmp = file1InputStream.read()) != -1){
                if(ss > 0){
                    sel = true;
                }
                bob = "" + (char)tmp;
                writeToFile(mixedFile, bob, sel);
                ss++;
            }
            file1InputStream.close();
            FileInputStream file2InputStream = new FileInputStream(file2);
            while ((tmp = file2InputStream.read()) != -1){
                bob = "" + (char)tmp;
                writeToFile(mixedFile, bob, true);
            }
            file2InputStream.close();
        }catch (FileNotFoundException fileNotFoundException) {
            System.out.println("ERROR: file not found.");
        }catch (IOException exception) {
            System.out.println("ERROR: IOExeption.");
        }
    }
    private static void checkWord (String word, String file){

    }
}
