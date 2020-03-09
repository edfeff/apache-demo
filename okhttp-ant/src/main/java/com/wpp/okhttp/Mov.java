package com.wpp.okhttp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Mov {
    public static void main(String[] args) {
        System.out.println("Usage [basePath] [start] [end] [outfile]");
        if (args.length != 4) {
            System.out.println("参数错误");
        }


        String basePath = args[0];
        if (!basePath.endsWith("/")) {
            basePath = basePath + "/";
        }
        int start = Integer.valueOf(args[1]);
        int end = Integer.valueOf(args[2]);
        String outfile = args[3];

        List<String> filelist = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            filelist.add(i + ".mp4");
        }
        try (FileOutputStream out = new FileOutputStream(basePath + outfile)) {
            byte[] buffer = new byte[1024 * 1024 * 5];
            int read = 0;
            for (String f : filelist) {
                String mp4file = basePath + f;
                System.out.println("add file: " + f);
                FileInputStream fin = new FileInputStream(new File(mp4file));

                while ((read = fin.read(buffer)) > 0) {
                    out.write(buffer, 0, read);
                }

                fin.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
