package com.wpp.okhttp;

import java.io.*;
import java.util.*;

public class F1 {
    public static Map<String, String> handleM38uToMap(String file, String baseUrl) {
        File m3 = new File(file);
        Map<String, String> nameUrlMap = new HashMap<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(m3);
            Scanner scanner = new Scanner(fileInputStream);
            int i = 0;
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (!s.startsWith("#")) {
//                    ../watch/d615dab744cea8f000133202/1583415154_1249.ts
                    String[] names = s.split("/");
                    String nFileName = names[2] + "-" + i + ".mp4";
                    String fileUrl = baseUrl + s.substring(2);
                    nameUrlMap.put(nFileName, fileUrl);
//                    System.out.println(nFileName + " : " + fileUrl);
                    i++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return nameUrlMap;
    }

    public static void getCurlScript(String file, String baseUrl) {
        Map<String, String> map = handleM38uToMap(file, baseUrl);
        Set<String> strings = map.keySet();
        String sh = "curl url -o filename";
        for (String name : strings) {
            System.out.println("curl " + map.get(name) + " -o " + name + "");
        }
    }

    public static void getCurlScript(String file, String baseUrl, String dest) {
        Map<String, String> map = handleM38uToMap(file, baseUrl);
        Set<String> strings = map.keySet();
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(dest));
            for (String name : strings) {
                String cmd = "curl " + map.get(name) + " -o " + name;
                writer.println(cmd);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getCurlScript("/home/wpp/work/gogs/sier/13/m.m38u", "https://vod01-o.zmengzhu.com/record","/home/wpp/work/gogs/sier/13/do.sh");
    }
}
