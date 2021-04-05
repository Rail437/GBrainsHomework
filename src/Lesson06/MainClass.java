package Lesson06;

import java.io.*;
import java.util.Locale;
import java.util.Random;

public class MainClass {
    public static void main (String [] args) {
        String fileName = "src/Lesson06/Home Work1.txt";
        String fileName2 = "src/Lesson06/Home Work2.txt";
        Boolean selector = false;
        String checkWord = "Hope";

        String text1 = "STAR WARS\nEpisode 4\n" + "New Hope\n" +
        "There is a civil war. Spaceships\n" +
        "insurgents striking from a secret base,\n" +
        "won the first victory in the battle\n" + "with the sinister Galactic Empire.\n";
        String text2 = "During the battle, the rebel scouts\nmanaged to steal secret plans,\n" +
                "associated with the main weapon of the Empire -\n" +
                "Death Star, armored space\n" +
        "a station capable of destroying an entire planet.\n" +
        "Princess Chased by Imperial Agents\n" +
        "Leia hurries home in her starship.";
        writeToFile(fileName, text1, selector);
        writeToFile(fileName2, text2,selector);
        mixToFile(fileName, fileName2);
        System.out.println(checkWord(checkWord, fileName));
        System.out.println(searchFiles(new File("D:\\"),checkWord));
    }
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
            String buff;
            boolean sel = false;

            while ((tmp = file1InputStream.read()) != -1){
                if(ss > 0){
                    sel = true;
                }
                buff = "" + (char)tmp;
                writeToFile(mixedFile, buff, sel);
                ss++;
            }
            file1InputStream.close();
            FileInputStream file2InputStream = new FileInputStream(file2);
            while ((tmp = file2InputStream.read()) != -1){
                buff = "" + (char)tmp;
                writeToFile(mixedFile, buff, true);
            }
            file2InputStream.close();
        }catch (FileNotFoundException fileNotFoundException) {
            System.out.println("ERROR: file not found.");
        }catch (IOException exception) {
            System.out.println("ERROR: IOExeption.");
        }
    }
    private static boolean checkWord (String word, String file){
    try{
        int count = word.length();
    int tmp;
    int i = 0;
    char[] buff = word.toCharArray();
    //buff = word;
        FileInputStream fileInputStream = new FileInputStream(file);
        while ((tmp = fileInputStream.read()) != -1){
            if((char)tmp == buff[i]){
                //System.out.print(buff[i]);
                i++;
            }else
                i = 0;
            if(i == count){
                return true;
            }
        }
        fileInputStream.close();
    }catch (FileNotFoundException fileNotFoundException) {
        System.out.println("ERROR: file not found.");
    }catch (IOException exception) {
        System.out.println("ERROR: IOExeption.");
    }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
        System.out.println("ERROR: No search word entered.");
    }
    return false;
    }
    private static boolean searchFiles(File rootFile, String word){
        if(rootFile.isDirectory()){
            System.out.println("searching at: " + rootFile.getAbsolutePath());
            File [] directoryFiles = rootFile.listFiles();
            if(directoryFiles != null){
                for (File file: directoryFiles){
                    if(file.isDirectory()){
                        searchFiles(file,word);
                    } else {
                        if(file.getName().toLowerCase().endsWith(".txt")){
                        if(checkWord(word,file.getAbsolutePath()) == true){
                            return true;
                        }
                        }
                    }
                }
            }
        }
        return false;
    }
}