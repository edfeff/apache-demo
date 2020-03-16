package com.wpp.okhttp;

import com.wpp.okhttp.get.GetExample2;

import java.io.*;
import java.util.*;

public class F1 {
    public static List<String> handleM38uToList(String file, String baseUrl) {
        File m3 = new File(file);
        List<String> fileList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(m3);
            Scanner scanner = new Scanner(fileInputStream);
            int i = 0;
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (!s.startsWith("#")) {
//                    ../watch/d615dab744cea8f000133202/1583415154_1249.ts
                    String[] names = s.split("/");
//                    String nFileName = names[2] + "-" + i + ".mp4";
//                    String nFileName = i + ".mp4";
                    String fileUrl = baseUrl + s;
                    fileList.add(fileUrl);
//                    System.out.println(nFileName + " : " + fileUrl);
//                    System.out.println("curl " + base + fileUrl + " -o " + nFileName);
                    i++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileList;
    }

//    public static void getCurlScript(String file, String baseUrl) {
//        Map<String, String> map = handleM38uToList(file, baseUrl);
//        Set<String> strings = map.keySet();
//        String sh = "curl url -o filename";
//        for (String name : strings) {
//            System.out.println("curl " + map.get(name) + " -o " + name + "");
//        }
//    }
//
//    public static void getCurlScript(String file, String baseUrl, String dest) {
//        Map<String, String> map = handleM38uToList(file, baseUrl);
//        Set<String> strings = map.keySet();
//        try {
//            PrintWriter writer = new PrintWriter(new FileOutputStream(dest));
//            for (String name : strings) {
//                String cmd = "curl " + map.get(name) + " -o " + name;
//                writer.println(cmd);
//            }
//            writer.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {

        String baseUrl = "https://resource.edc.org.cn";
        List<String> list = handleM38uToList("/home/wpp/work/maven/apache-parent/okhttp-ant/src/main/resources/d14.m38u", baseUrl);

        String basedir = "/home/wpp/aws-lesson";

        List<String> deleteList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
//            System.out.println("curl " + list.get(i) + " -o " + i + ".mp4");
            String file = basedir + "/" + i + ".mp4";
            System.out.println(file);
            deleteList.add(file);
            GetExample2.download(list.get(i), file);
        }

        String dest = "" + System.currentTimeMillis() + ".mp4";
        int last = list.size() - 1;
        Mov.main(new String[]{basedir, "0", "" + last, "all/" + dest});

        for (String s : deleteList) {
            File file = new File(s);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
