package controller.util;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpServletRequest;

public class WeblogicUtil {

    public String StartWeblogic(HttpServletRequest req, String WlUser, String WlPassword, String HUser, String HPassword) {
        try {
            String shell = req.getSession().getServletContext().getRealPath("/") + "WEB-INF\\shcommands\\shellstartweblogic.sh";
           StartWeblogicThreadUtil swtu = new StartWeblogicThreadUtil(shell, WlUser, WlPassword, HUser, HPassword);
           
           ExecutorService threadExecutor = Executors.newCachedThreadPool();
           threadExecutor.execute(swtu);
           threadExecutor.shutdown();
           
           return "true";
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "false";
    }

    public String StopWeblogic(HttpServletRequest req, String WlUser, String WlPassword, String HUser, String HPassword) {
        try {
            WebParamUtil wpu = new WebParamUtil();
            wpu.SetFileName(req.getSession().getServletContext().getRealPath("/") + "/WEB-INF/web-param.xml");
            String sftpDeployHostIP = wpu.GetsftpDeployHostIP();

            System.out.println("Executing remote stop command on configured deploy host.");

            String restartShellScript = req.getSession().getServletContext().getRealPath("/") + "WEB-INF\\shcommands\\shellstopweblogic.sh";
            FileUtil fu = new FileUtil();

            ArrayList<String> lstCommands;
            lstCommands = fu.ReadFile(restartShellScript);
            ArrayList<String> lstCommands0 = new ArrayList<String>();

            for (String cmd : lstCommands) {
                String tmp = cmd.replace("$1", WlUser).replace("$2", WlPassword);
                lstCommands0.add(tmp);
                System.out.println("Prepared remote stop command.");
            }

            controller.util.LinuxUtil lu = new LinuxUtil();

            if (lu.executeCommands(sftpDeployHostIP, HUser, HPassword, lstCommands0)) {
                System.out.println("lu.getOutPut() : " + lu.getOutPut());

                return lu.getOutPut();
            } else {
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "false";
    }
}
