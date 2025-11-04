package services;
import java.io.*;
import java.util.*;

public class FileService {

    public static void writeLine(String file, String data) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.write(data);
        bw.newLine();
        bw.close();
    }

    public static List<String> readAll(String file) throws Exception {
        List<String> list = new ArrayList<>();
        File f = new File(file);
        if(!f.exists()) return list;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while((line = br.readLine()) != null) list.add(line);
        br.close();
        return list;
    }
}