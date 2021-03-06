package sample.SaveMessages;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class SavingMessages {


    public void SaveMessagesToText(String nick, String text) {
        File dir = new File("src\\sample\\SaveMessages");
        String textPath = nick + ".txt";
        if(!dir.exists()) {
            dir.mkdir();
        }
        File messages = new File(dir, textPath);
        if(!messages.exists()){
            try {
                messages.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String str = text + " \n";
        //String sleshn = "\n";
        try (FileOutputStream fileOutputStream = new FileOutputStream(messages, true)){
            fileOutputStream.write(str.getBytes());
            //fileOutputStream.write(sleshn.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String lastHundredLine(String nick){
        String path = (nick + ".txt");
        File file = new File("src\\sample\\SaveMessages\\" + path);
        //try(FileInputStream fileInputStream = new FileInputStream(file)) {
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[2048];
            fileInputStream.read(bytes);
            String text = new String(bytes);
            return text;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "(>_<)";
    }

    public String lastHundredHistory(String nick){
        String path = ("src\\sample\\SaveMessages\\" + nick + ".txt");
        File file = new File(path);
        StringBuilder history = new StringBuilder();
        try{
            List textlist = Files.lines(Paths.get(path)).map((s) -> "\n" + s).collect(Collectors.toList());
            //System.out.println("Save textList = " + textlist.size());
            if(textlist.size() > 100){
                for(int i = textlist.size() - 100; i < textlist.size(); i++){
                    history.append(textlist.get(i));
                }
                return "*****100 ?????????????????? ??????????????????:***** \n" + history;
            }
            return "****?????????????????? ??????????????????:**** \n" + lastHundredLine(nick);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "(>_<)";
    }
}
