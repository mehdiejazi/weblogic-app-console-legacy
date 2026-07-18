package controller.util;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

public class LinuxUtil {

    private int SFTPPORT = 22;
    private String strOutPut = "";
    private String strError = "";
    public boolean executeCommand(String sftpHostIP, String sftpUser, String sftpPass, String command) throws JSchException, IOException {

        return executeCommand(sftpHostIP, sftpUser, sftpPass, command, true);
    }
    public boolean executeCommand(String sftpHostIP, String sftpUser, String sftpPass, String command, boolean Output) throws JSchException, IOException {
        this.setError("");
        Session session_;
        ChannelExec channel;
        try {
            JSch jsch_ = new JSch();
            session_ = jsch_.getSession(sftpUser, sftpHostIP, SFTPPORT);
            session_.setPassword(sftpPass);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session_.setConfig(config);
            session_.connect();

            channel = (ChannelExec) session_.openChannel("exec");
            BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));

            channel.setCommand(command);
            channel.connect();

            String msg = null;
            this.strOutPut = "";
            while ((msg = in.readLine()) != null) {
                if (Output) {
                    System.out.println(msg);
                }
                this.strOutPut = this.strOutPut.concat(msg + "\n");
            }
        } catch (Exception ex) {
            //setError(ex);
            return false;
        }


        channel.disconnect();
        session_.disconnect();

        return true;
    }

    public boolean executeCommands(String sftpHostIP, String sftpUser, String sftpPass, List<String> lstCommand) throws JSchException, IOException {

        return executeCommands(sftpHostIP, sftpUser, sftpPass, lstCommand, true);
    }

    public boolean executeCommands(String sftpHostIP, String sftpUser, String sftpPass, List<String> lstCommand, boolean Output) throws JSchException, IOException {
        Session session_;
        ChannelExec channel;
        try {
            JSch jsch_ = new JSch();
            session_ = jsch_.getSession(sftpUser, sftpHostIP, SFTPPORT);
            session_.setPassword(sftpPass);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session_.setConfig(config);
            session_.connect();

            channel = (ChannelExec) session_.openChannel("exec");
            BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));

            for (String command : lstCommand) {
                channel.setCommand(command);
                channel.connect();
            }

            String msg = null;
            this.strOutPut = "";
            while ((msg = in.readLine()) != null) {

                if (Output) {
                    System.out.println(msg);
                }
                this.strOutPut = this.strOutPut.concat(msg + "\n");
            }
            channel.disconnect();
            session_.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean executeDeployShellScript(
            String sftpHostIP,
            String sftpUser,
            String sftpPass,
            String deployShellScript,
            String wlUser,
            String wlPass,
            String wlAdminURL,
            String warPath,
            String appName) {
        String strCommand;
        strCommand = ". " + deployShellScript + " " + wlAdminURL + " " + wlUser + " " + wlPass + " " + warPath + " " + appName;
        boolean blnReturned;
        try {
            blnReturned = this.executeCommand(sftpHostIP, sftpUser, sftpPass, strCommand);
        } catch (JSchException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        return blnReturned;
    }

    public boolean executeStopStartShellScript(
            String sftpHostIP,
            String sftpUser,
            String sftpPass,
            String deployShellScript,
            String wlUser,
            String wlPass,
            String wlAdminURL,
            String appName) {
        String strCommand;
        strCommand = ". " + deployShellScript + " " + wlAdminURL + " " + wlUser + " " + wlPass + " " + appName;
        boolean blnReturned;
        try {
            blnReturned = this.executeCommand(sftpHostIP, sftpUser, sftpPass, strCommand);
        } catch (JSchException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        return blnReturned;
    }

    /**
     * @return the strOutPut
     */
    public String getOutPut() {
        return strOutPut;
    }

    /**
     * @return the strError
     */
    public String getError() {
        return strError;
    }

    /**
     * @param strError the strError to set
     */
    public void setError(String strError) {
        this.strError = strError;
    }
}
