package controller.util;

import java.util.ArrayList;

public class StartWeblogicThreadUtil implements Runnable {

    private String _ServletPath;
    private String _WlUser;
    private String _WlPassword;
    private String _HUser;
    private String _HPassword;

    public StartWeblogicThreadUtil(String ServletPath, String WlUser, String WlPassword, String HUser, String HPassword) {
        this._ServletPath = ServletPath;
        this._WlUser = WlUser;
        this._WlPassword = WlPassword;
        this._HUser = HUser;
        this._HPassword = HPassword;
    }

    public void run() {
        try {
            WebParamUtil wpu = new WebParamUtil();
            wpu.SetFileName(_ServletPath + "/WEB-INF/web-param.xml");
            String sftpDeployHostIP = wpu.GetsftpDeployHostIP();

            System.out.println("Executing remote start command on configured deploy host.");

            String restartShellScript = _ServletPath + "WEB-INF\\shcommands\\shellstartweblogic.sh";
            FileUtil fu = new FileUtil();

            ArrayList<String> lstCommands;
            lstCommands = fu.ReadFile(restartShellScript);
            ArrayList<String> lstCommands0 = new ArrayList<String>();

            for (String cmd : lstCommands) {
                String tmp = cmd.replace("$1", _WlUser).replace("$2", _WlPassword);
                lstCommands0.add(tmp);
                System.out.println("Prepared remote start command.");
            }

            controller.util.LinuxUtil lu = new LinuxUtil();

            if (lu.executeCommands(sftpDeployHostIP, _HUser, _HPassword, lstCommands0,false)) {
                System.out.println("lu.getOutPut() : " + lu.getOutPut());
            } else {
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
