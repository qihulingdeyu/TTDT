package com.qing.ttdt.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.protocol.HTTP;

public class LrcHandle {

    private List<String> wordList = new ArrayList<String>();
    private List<Integer> timeList = new ArrayList<Integer>();
    private InputStream in;
    private Reader reader;
    private BufferedReader br;
    
    public LrcHandle(){
        
    }
    public List<String> getWordList() {
        return wordList;
    }
    public List<Integer> getTimeList() {
        return timeList;
    }
/*
[ti:青花瓷]
[ar:周杰伦]
[al:我很忙]
[by:张琪]
[00:00.00]发送短信18到291199下载该歌曲到手机
[00:01.11]青花瓷
[03:36.49]
[00:21.39]素眉勾勒秋千话北风龙转丹
 */
    public void readLRC(String path){
        File file = new File(path);
        try {
            in = new FileInputStream(file);
            reader = new InputStreamReader(in, HTTP.UTF_8);
            br = new BufferedReader(reader);
            
            String str = null;
            String str1 = null;
            while((str=br.readLine())!=null){
                addTiemToList(str);
                
                if((str.indexOf("[ar:")!=-1) || (str.indexOf("[ti:")!=-1) || (str.indexOf("[by:")!=-1)){
                    str = str.substring(str.indexOf(":")+1, str.indexOf("]"));
                }else{
                    str1 = str.substring(str.indexOf("["), str.indexOf("]")+1);
                    str = str.replace(str1, "");
                }
                wordList.add(str);
            }
        } catch (FileNotFoundException e) {
            wordList.add("歌词文件找不到，请先下载");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            wordList.add("没有读取到歌词");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private Matcher matcher = null;
    private String strTime = null;
    private void addTiemToList(String str) {
        matcher = Pattern.compile("\\[\\d{1,2}:\\d{1,2}([\\.:]\\d{1,2})?\\]").matcher(str);
        if(matcher.find()){
            strTime = matcher.group();
            timeList.add(timeHandler(strTime.substring(1, strTime.length()-1)));
        }
        
    }
    private String[] time = null;
    private int minute = 0;
    private int second = 0;
    private int millisecond = 0;
    private int curTime = 0;
    //分离出时间
    private int timeHandler(String str) {
        str = str.replace(".", ":");
        time = str.split(":");
        //分离出分、秒并转换成整型
        minute = Integer.parseInt(time[0]);
        second = Integer.parseInt(time[1]);
        millisecond = Integer.parseInt(time[2]);
        
        //计算上一行与下一行的时间转换为毫秒数
        curTime = (minute*60 + second)*1000 + millisecond*10;
        return curTime;
    }
}
