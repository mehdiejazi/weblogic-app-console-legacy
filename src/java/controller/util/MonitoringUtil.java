/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author ejazi
 */
public class MonitoringUtil {

    public String GetResponsCode(String URL) throws MalformedURLException, IOException {
        try {
            URL url = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            return String.valueOf(con.getResponseCode());
        } catch (Exception ex) {
        }
        return "Error";
    }

}
