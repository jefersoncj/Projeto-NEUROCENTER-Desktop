/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.Tetes;

import java.io.File;

/**
 *
 * @author Jeferson
 */
public class Utils {

    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String png = "png";
    public final static String pdf = "pdf";
    public final static String xls = "xls";
    public final static String xlsm = "xlsm";
    public final static String doc = "doc";
    public final static String docx = "docx";
    public final static String gp = "3gp";
    public final static String mp4 = "mp4";

    /*
     * Get the extension of a file.
     */  
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}