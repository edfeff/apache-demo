package com.wpp.okhttp;

import java.io.*;
import java.util.Scanner;

public class Readm38u {
    public static void main(String[] args) {
        System.out.println("Usage [path] [server]");

        String path = "/home/wpp/work/gogs/sier/13/";
        String server = "https://vod01-o.zmengzhu.com/record";
        String m38u = "m.m38u";
        String sh = path + "b1.sh";

        path = args[0];
        server = args[1];

        File m3 = new File(path + m38u);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(sh);
            PrintWriter writer = new PrintWriter(fileOutputStream);

            FileInputStream fileInputStream = new FileInputStream(m3);
            Scanner scanner = new Scanner(fileInputStream);
            int i = 0;
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (!s.startsWith("#")) {
                    String script = "curl " + server + s.substring(2) + " -o " + i + ".mp4";
//                    System.out.println("curl " + server + s.substring(2) + " -o " + i + ".mp4");
                    writer.println(script);
                    i++;
                }
            }

            fileInputStream.close();
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
