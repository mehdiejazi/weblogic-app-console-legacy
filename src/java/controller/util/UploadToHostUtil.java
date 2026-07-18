package controller.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.File;
import java.io.FileInputStream;

public class UploadToHostUtil {

    public boolean uploadToHost(String sftpHostIP, String sftpUser, String sftpPass, String sftpWorkingDir, String fileName, String sftpMakingDir) {
        int SFTPPORT = 22;
        Session session;
        Channel channel;
        ChannelSftp channelSftp;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(sftpUser, sftpHostIP, SFTPPORT);
            session.setPassword(sftpPass);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;

            try {
                channelSftp.cd(sftpWorkingDir);
            } catch (Exception ex) {
                channelSftp.mkdir(sftpWorkingDir);
                channelSftp.cd(sftpWorkingDir);
            }
            
            if (sftpMakingDir.equals("") == false) {
                channelSftp.mkdir(sftpMakingDir);
                sftpWorkingDir = sftpWorkingDir.concat("/" + sftpMakingDir);
                channelSftp.cd(sftpWorkingDir);
            }
            File f = new File(fileName);
            channelSftp.put(new FileInputStream(f), f.getName());
            channel.disconnect();
            session.disconnect();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}