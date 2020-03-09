package com.wpp.okhttp.get;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.*;
import java.util.Scanner;

public class GetExample2 {

    //
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = "https://vod01-o.zmengzhu.com/record/watch/d615dab744cea8f000133202/1583407871_35.ts";
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        InputStream inputStream = response.body().byteStream();
        byte[] buffer = new byte[1024 * 1024 * 4];
        int read = 0;

        FileOutputStream fileOutputStream = new FileOutputStream(new File("demo.mp4"));

        while ((read = inputStream.read(buffer)) > 0) {
            fileOutputStream.write(buffer, 0, read);
        }

        fileOutputStream.close();
    }
}