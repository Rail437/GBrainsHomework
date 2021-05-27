import java.util.ArrayList;

public interface ArrayToList {
    static ArrayList arrayToArrayList(Object[] objects){
        ArrayList list = new ArrayList();
        for (int i = 0; i <objects.length; i++) {
            list.add(objects[i]);
        }
        return list;
    }
}
