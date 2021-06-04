package sample.SaveMessages;

import java.io.*;

public class SavingMessages {


    public void SaveMessagesToText(String nick, String text) {
        File dir = new File("D:\\GBrainsHomework\\src\\ChatFx\\src\\sample\\SaveMessages");
        String textPath = nick + ".txt";
        if(!dir.exists()) {
            dir.mkdirs();
        }
        File messages = new File(dir, textPath);
        if(!messages.exists()){
            try {
                messages.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String str = text;
        String sleshn = "\n";
        try (FileOutputStream fileOutputStream = new FileOutputStream(messages, true)){
            fileOutputStream.write(str.getBytes());
            fileOutputStream.write(sleshn.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String lastHundredLine(String nick){
        String path = (nick + ".txt");
        File file = new File("D:\\GBrainsHomework\\src\\ChatFx\\src\\sample\\SaveMessages\\" + path);
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
}
