package com.huihui.aligo.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.SequenceInputStream;

/**
 * 合并流操作
 *
 * @author minghui.y BG358486
 * @create 2020-09-12 18:24
 **/
public class SequenceInputStreamDemo {
    public static void main(String[] args) {
        FileInputStream in1 = null;
        FileInputStream in2 = null;
        SequenceInputStream sin = null;
        FileOutputStream out = null;

        try {
            in1 = new FileInputStream("C:\\Users\\minghui.y\\Desktop\\soureFile1.txt");
            in2 = new FileInputStream("C:\\Users\\minghui.y\\Desktop\\soureFile2.txt");
            out = new FileOutputStream("C:\\Users\\minghui.y\\Desktop\\targetFile.txt");
            sin = new SequenceInputStream(in2, in1);

            int hasRead;

            while ((hasRead = sin.read()) != -1) {
                out.write(hasRead);
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in1.close();
                in2.close();
                sin.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
