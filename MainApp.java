import services.*;
import models.*;
import java.util.*;

public class MainApp {
    static Scanner sc = new Scanner(System.in);
    static AuthService auth = new AuthService();

    public static void main(String[] args) throws Exception {
        while(true){
            System.out.println("1. Login");
            System.out.println("2. Register");
            int ch = sc.nextInt(); sc.nextLine();

            if(ch==1) login();
            else register();
        }
    }

    static void login() throws Exception {
        System.out.print("Email: "); String email=sc.nextLine();
        System.out.print("Pass: "); String pass=sc.nextLine();

        User u = auth.login(email,pass);
        if(u==null) System.out.println("Invalid!");
        else {
            System.out.println("Welcome "+u.name+" ("+u.role+")");
            if(u.role.equals("ADMIN")) new AdminController().menu((Admin)u);
            if(u.role.equals("TEACHER")) new TeacherController().menu((Teacher)u);
            if(u.role.equals("STUDENT")) new StudentController().menu((Student)u);
        }
    }

    static void register() throws Exception {
        System.out.print("Role ADMIN/TEACHER/STUDENT: ");
        String role = sc.nextLine();
        System.out.print("Name: "); String name=sc.nextLine();
        System.out.print("Email: "); String email=sc.nextLine();
        System.out.print("Pass: "); String pass=sc.nextLine();

        if(role.equals("TEACHER")) {
            System.out.print("Dept: "); String d=sc.nextLine();
            auth.register(new Teacher(name,email,pass,d));
        } else if(role.equals("STUDENT")) {
            System.out.print("Roll: "); String r=sc.nextLine();
            auth.register(new Student(name,email,pass,r));
        } else {
            auth.register(new Admin(name,email,pass));
        }
        System.out.println("Registered ✅");
    }
}