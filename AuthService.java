package services;
import models.*;
import java.util.*;

public class AuthService {
    String userFile = "data/users.txt";

    public User login(String email, String pass) throws Exception {
        List<String> users = FileService.readAll(userFile);
        for(String u : users) {
            String[] p = u.split(",");
            if(p[2].equals(email) && p[3].equals(pass)) {
                if(p[0].equals("ADMIN")) return new Admin(p[1],p[2],p[3]);
                if(p[0].equals("TEACHER")) return new Teacher(p[1],p[2],p[3],p[4]);
                return new Student(p[1],p[2],p[3],p[4]);
            }
        }
        return null;
    }

    public void register(User u) throws Exception {
        String data = u.role+","+u.name+","+u.email+","+u.password+",";
        if(u instanceof Teacher) data += ((Teacher)u).department;
        else if(u instanceof Student) data += ((Student)u).roll;
        else data += "-";
        FileService.writeLine(userFile,data);
    }
}