
package sample.SaveMessages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface MessageHistory {

    /**
     * НЕ получилось у меня реализовать нормально этот интерфейс.
     */
     /*default String lastHundredLine(String nick){
        String path = ("D:\\GBrainsHomework\\src\\ChatFx\\src\\sample\\SaveMessages\\" + nick + ".txt");
        File file = new File(path);
        StringBuilder history = new StringBuilder();
        //try(FileInputStream fileInputStream = new FileInputStream(file)) {
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[2048];
            fileInputStream.read(bytes);
            String text = new String(bytes);
            long countSlesh = Files.lines(Paths.get(path))
                    .filter("\\n"::equals).count();
            System.out.println(countSlesh);
            if(countSlesh > 100){
                List textlist = Files.lines(Paths.get(path)).collect(Collectors.toList());
                for(int i = textlist.size() - 100; i < textlist.size(); i++){
                    history.append(textlist.get(i));
                }
                return history.toString();
            }
            return text;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "(>_<)";
    }*/
}

