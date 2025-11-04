import models.Admin;
import services.RoutineService;
import java.util.*;

public class AdminController {
    Scanner sc = new Scanner(System.in);
    RoutineService rs = new RoutineService();

    public void menu(Admin a) throws Exception {
        while(true){
            System.out.println("1. Add Routine");
            System.out.println("2. Logout");
            int c=sc.nextInt(); sc.nextLine();
            if(c==1) addRoutine();
            else return;
        }
    }

    void addRoutine() throws Exception {
        System.out.print("Day: "); String d=sc.nextLine();
        System.out.print("Subject: "); String s=sc.nextLine();
        System.out.print("Teacher: "); String t=sc.nextLine();
        System.out.print("Room: "); String r=sc.nextLine();
        System.out.print("Start: "); String st=sc.nextLine();
        System.out.print("End: "); String e=sc.nextLine();
        System.out.print("Section: "); String sec=sc.nextLine();

        if(rs.addRoutine(d,s,t,r,st,e,sec))
            System.out.println("✅ Routine Added");
        else
            System.out.println("❌ Conflict found!");
    }
}