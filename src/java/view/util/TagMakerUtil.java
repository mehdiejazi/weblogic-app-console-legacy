/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.util;

/**
 *
 * @author ejazi
 */
public class TagMakerUtil {

    public String GetImageTag(String src, String alt) {
        String strReturn = "";
        String strTag = "<img src=\"src0\" alt=\"alt0\" title=\"alt0\" />";
        strReturn = strTag.replaceAll("src0", src).replaceAll("alt0", alt);
        
        System.out.println(strReturn);
        return strReturn;
    }
}
