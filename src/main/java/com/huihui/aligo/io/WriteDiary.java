package com.huihui.aligo.io;

import java.io.*;

/**
 * 该类用于复习IO流，实现键盘键入文本，写入指定文件中
 *
 * @author minghui.y BG358486
 * @create 2020-09-07 21:17
 **/
public class WriteDiary {

    private static final String DIARY_FILE_PATH = "C:\\Users\\minghui.y\\Desktop\\myDiary.txt";
    private static final String END_COMMAND = "END";

    public static void main(String[] args) throws IOException {
        InputStreamReader is = null;
        BufferedReader reader = null;
        FileWriter fileWriter = null;
        BufferedWriter writer = null;

        String inputLine;
        System.out.println("请开始写日记：");

        try {
            is = new InputStreamReader(System.in);
            reader = new BufferedReader(is);
            File diaryFile = new File(DIARY_FILE_PATH);
            fileWriter = new FileWriter(diaryFile);
            writer = new BufferedWriter(fileWriter);

            while ((inputLine = reader.readLine()) != null && inputLine.length() > 0) {
                writer.write(inputLine + "\n");
                System.out.println("您已写入一行日记");
                if (END_COMMAND.equals(inputLine.trim())) {
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("IO异常");
        } finally {
            //关闭流要从大到小关闭，否则抛出java.io.IOException: Stream closed
            reader.close();
            is.close();
            writer.close();
            fileWriter.close();
        }




    }
}
