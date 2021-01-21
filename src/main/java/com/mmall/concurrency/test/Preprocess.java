/**
 * Copyright 2014-2025 JD.COM All Right Reserved.
 *
 * @file： Preprocess.java   project: concurrency
 * @creator: biguodong
 * @date: 2020/11/24
 */

package com.mmall.concurrency.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Preprocess {

    private static BufferedReader br = null;
    private static PrintWriter out = null;
    private static ArrayList<String[]> recordArr = new ArrayList<String[]>();
    private static ArrayList<double[]> recordArrdouble=new ArrayList<double[]>();
    private static ArrayList<int[]> recordArrint=new ArrayList<int[]>();

    public static void main(String [] args) {

        String line = "";
        String csvSplit = ",";
        //String fileToConvert = "pure";

        try {
            //out = new PrintWriter(new PrintWriter("F:/Common/Purefix/"+ fileToConvert +".txt","UTF-8"));
            //br = new BufferedReader(new FileReader("F:/Common/Purefix/" + fileToConvert + ".csv"));
            out=new PrintWriter(new PrintWriter("/Users/biguodong/Desktop/yace/qq_unionid_wid_mapping.txt","UTF-8"));
            br=new BufferedReader(new FileReader("/Users/biguodong/Desktop/yace/qq_unionid_wid_mapping_1/csv/80580218.csv"));

            while((line = br.readLine()) != null ) {

                String[] recCopyToBePushed = new String[128];
                String[] recordExtracted = line.split(csvSplit, 129);
                double[] recordDouble=new double[128];
                for(int a=0;a<128;a++){
                    recordDouble[a]=0;
                }
                int[] recordInt=new int[128];
                for(int a=0;a<128;a++){
                    recordInt[a]=0;
                }
                if(recordExtracted[128].endsWith("A")){
                    for(int x=0;x<recordExtracted.length-1;x++){
                        recordExtracted[x]=recordExtracted[x].replaceAll("inf", "1000000");
                        recCopyToBePushed[x]=recordExtracted[x];
                    }
                    recordArr.add(recCopyToBePushed);
                    recordArrdouble.add(recordDouble);
                    recordArrint.add(recordInt);
                }
                //for(int x = 0; x < recordExtracted.length; x++){
                //  recCopyToBePushed[x] = recordExtracted[x];
                //}
                //recordArr.add(recCopyToBePushed);
            }

            for(int a=0;a<recordArr.size();a++){
                for(int b=0;b<recordArr.get(a).length;b++){
                    recordArrdouble.get(a)[b]=Double.parseDouble(recordArr.get(a)[b]);
                    recordArrint.get(a)[b]=(int)recordArrdouble.get(a)[b];
                }
            }


            for( int y = 0; y< recordArrint.size(); y++){
                for(int z = 0; z < recordArrint.get(y).length; z++){
                    out.print(recordArrint.get(y)[z]+" ");
                }
                out.println("");
            }

        } catch(FileNotFoundException fnfe ){
            fnfe.printStackTrace();
        } catch(IOException ie) {
            ie.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(out != null) {
                out.close();
            }
        }

        for(int i = 0; i < recordArrint.size(); i++){

            System.out.print("PROCESSING LINE :\t");

            for(int j = 0; j< recordArrint.get(i).length; j++){
                System.out.print(recordArrint.get(i)[j] + "    ");
            }
            System.out.println("");
        }

        System.out.println("Files Proccessed And Converted!");
    }
    // End of main
}