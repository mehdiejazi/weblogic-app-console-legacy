/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jhlabs.image.CircleFilter;
import com.jhlabs.image.MarbleFilter;
import com.jhlabs.image.TwirlFilter;
import com.jhlabs.image.WaterFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ejazi
 */
public class CaptchaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int width = 100;
        int height = 50;

        char data[][] = new char[10][5];

        String strAlphabet = "abcdefghkmnprstuvwxyzABCDEFGHKMNPQRSTUVWXYZ23456789";
        Random rnd = new Random();


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                data[i][j] = strAlphabet.charAt(rnd.nextInt(strAlphabet.length()));
            }
        }


        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = bufferedImage.createGraphics();

        Font font = new Font("Georgia", Font.BOLD, 14);
        g2d.setFont(font);

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

//        GradientPaint gp = new GradientPaint(rnd.nextInt(2), 0,
//                Color.white, 0, 5, new Color(100 + rnd.nextInt(128), 100 + rnd.nextInt(128), 100 + rnd.nextInt(128)), true);
//
//        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(new Color(0, 90 + rnd.nextInt(128), 100 + rnd.nextInt(128)));

        Random r = new Random();
        int index = Math.abs(r.nextInt()) % 5;

        String captcha = String.copyValueOf(data[index]);
        request.getSession().setAttribute("captcha", captcha);

        MarbleFilter mbf = new MarbleFilter();
        mbf.filter(bufferedImage, bufferedImage);

        WaterFilter wf = new WaterFilter();
        wf.filter(bufferedImage, bufferedImage);

        int x = -5;
        int y = 0;

        for (int i = 0; i < data[index].length; i++) {
            g2d.setColor(new Color(0, 0, 0));
            x += 15;
            y = 40;
            g2d.drawChars(data[index], i, 1, x, y);
        }

        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();

        TwirlFilter tf = new TwirlFilter();
        tf.setAngle(rnd.nextInt(4) * 0.1f);
        tf.filter(bufferedImage, bufferedImage);



        CircleFilter cf = new CircleFilter();
        cf.setRadius(0);
        cf.setCentreY(1f);
        cf.setHeight(105f);
        cf.filter(bufferedImage, bufferedImage);



        for (int i = 0; i < rnd.nextInt(10); i++) {
            g2d.setColor(new Color(0, 0, 0));
            g2d.drawLine(rnd.nextInt(width), rnd.nextInt(height), rnd.nextInt(width), rnd.nextInt(height));
        }

        g2d.dispose();

        ImageIO.write(bufferedImage, "png", os);
        os.close();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}