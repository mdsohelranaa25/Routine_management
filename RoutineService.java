package services;
import java.util.*;

public class RoutineService {
    String file = "data/routines.txt";

    public boolean conflict(String day, String teacher, String start, String end) throws Exception {
        List<String> list = FileService.readAll(file);
        for (String r : list) {
            String[] p = r.split(",");
            if(p[0].equals(day) && p[2].equals(teacher)) {
                if(!(end.compareTo(p[4]) <= 0 || start.compareTo(p[5]) >= 0))
                    return true;
            }
        }
        return false;
    }

    public boolean addRoutine(String day,String sub,String t,String room,String st,String end,String sec) throws Exception {
        if(conflict(day,t,st,end)) return false;
        FileService.writeLine(file,day+","+sub+","+t+","+room+","+st+","+end+","+sec);
        return true;
    }
}