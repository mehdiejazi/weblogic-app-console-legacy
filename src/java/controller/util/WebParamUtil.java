package controller.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class WebParamUtil {

    private String strFileName;

    public void SetFileName(String fileName) {
        strFileName = fileName;
    }

    public String GetFileName() {
        return strFileName;
    }

    public void SetappName(String appName) throws FileNotFoundException, IOException, Exception {
        EncryptDESUtil des = new EncryptDESUtil();

        FileUtil fu = new FileUtil();
        ArrayList<String> arr = fu.ReadFile(strFileName);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).contains("appName")) {
                arr.set(i + 1, "        <param-value>" + des.encrypt(appName) + "</param-value>");
            }
        }
        fu.WriteFile(arr, strFileName);
    }

    public String GetappName() throws Exception {
        return getDecryptedOrPlainValue("appName");
    }

    public void SetwlAdminURL(String wlAdminURL) throws Exception {
        EncryptDESUtil des = new EncryptDESUtil();
        FileUtil fu = new FileUtil();
        ArrayList<String> arr = fu.ReadFile(strFileName);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).contains("wlAdminURL")) {
                arr.set(i + 1, "        <param-value>" + des.encrypt(wlAdminURL) + "</param-value>");
            }
        }
        fu.WriteFile(arr, strFileName);
    }

    public String GetwlAdminURL() throws Exception {
        return getDecryptedOrPlainValue("wlAdminURL");
    }

    public void SetwlUsername(String wlUsername) throws Exception {
        EncryptDESUtil des = new EncryptDESUtil();
        FileUtil fu = new FileUtil();
        ArrayList<String> arr = fu.ReadFile(strFileName);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).contains("wlUsername")) {
                arr.set(i + 1, "        <param-value>" + des.encrypt(wlUsername) + "</param-value>");
            }
        }
        fu.WriteFile(arr, strFileName);
    }

    public String GetwlUsername() throws Exception {
        return getDecryptedOrPlainValue("wlUsername");
    }

    public void SetwlPassword(String wlUsername) throws Exception {
        EncryptDESUtil des = new EncryptDESUtil();
        FileUtil fu = new FileUtil();
        ArrayList<String> arr = fu.ReadFile(strFileName);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).contains("wlPassword")) {
                arr.set(i + 1, "        <param-value>" + des.encrypt(wlUsername) + "</param-value>");
            }
        }
        fu.WriteFile(arr, strFileName);
    }

    public String GetwlPassword() throws Exception {
        return getDecryptedOrPlainValue("wlPassword");
    }

    public void SetsftpDeployHostIP(String sftpDeployHostIP) throws Exception {
        EncryptDESUtil des = new EncryptDESUtil();

        FileUtil fu = new FileUtil();
        ArrayList<String> arr = fu.ReadFile(strFileName);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).contains("sftpDeployHostIP")) {
                arr.set(i + 1, "        <param-value>" + des.encrypt(sftpDeployHostIP) + "</param-value>");
            }
        }
        fu.WriteFile(arr, strFileName);
    }

    public String GetsftpDeployHostIP() throws Exception {
        return getDecryptedOrPlainValue("sftpDeployHostIP");
    }

    public void SetsftpDeployWorkingDir(String sftpDeployWorkingDir) throws Exception {
        EncryptDESUtil des = new EncryptDESUtil();

        FileUtil fu = new FileUtil();
        ArrayList<String> arr = fu.ReadFile(strFileName);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).contains("sftpDeployWorkingDir")) {
                arr.set(i + 1, "        <param-value>" + des.encrypt(sftpDeployWorkingDir).replaceAll("\n", "").replaceAll("\r", "") + "</param-value>");
            }
        }
        fu.WriteFile(arr, strFileName);
    }

    public String GetsftpDeployWorkingDir() throws Exception {
        return getDecryptedOrPlainValue("sftpDeployWorkingDir");
    }

    public void SetsftpDeployUserName(String sftpDeployUserName) throws Exception {
        EncryptDESUtil des = new EncryptDESUtil();

        FileUtil fu = new FileUtil();
        ArrayList<String> arr = fu.ReadFile(strFileName);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).contains("sftpDeployUsername")) {
                arr.set(i + 1, "        <param-value>" + des.encrypt(sftpDeployUserName).replaceAll("\n", "").replaceAll("\r", "") + "</param-value>");
            }
        }
        fu.WriteFile(arr, strFileName);
    }

    public String GetsftpDeployUsername() throws Exception {
        return getDecryptedOrPlainValue("sftpDeployUsername");
    }

    public void SetsftpDeployPassword(String sftpDeployUserName) throws Exception {
        EncryptDESUtil des = new EncryptDESUtil();

        FileUtil fu = new FileUtil();
        ArrayList<String> arr = fu.ReadFile(strFileName);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).contains("sftpDeployPassword")) {
                arr.set(i + 1, "        <param-value>" + des.encrypt(sftpDeployUserName).replaceAll("\n", "").replaceAll("\r", "") + "</param-value>");
            }
        }
        fu.WriteFile(arr, strFileName);
    }

    public String GetsftpDeployPassword() throws Exception {
        return getDecryptedOrPlainValue("sftpDeployPassword");
    }

    public void SetsftpBackupHostIP(String sftpBackupHostIP) throws Exception {
        EncryptDESUtil des = new EncryptDESUtil();
        FileUtil fu = new FileUtil();
        ArrayList<String> arr = fu.ReadFile(strFileName);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).contains("sftpBackupHostIP")) {
                arr.set(i + 1, "        <param-value>" + des.encrypt(sftpBackupHostIP) + "</param-value>");
            }
        }
        fu.WriteFile(arr, strFileName);
    }

    public String GetsftpBackupHostIP() throws Exception {
        return getDecryptedOrPlainValue("sftpBackupHostIP");
    }

    public void SetsftpBackupWorkingDir(String sftpBackupWorkingDir) throws Exception {
        EncryptDESUtil des = new EncryptDESUtil();
        FileUtil fu = new FileUtil();
        ArrayList<String> arr = fu.ReadFile(strFileName);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).contains("sftpBackupWorkingDir")) {
                arr.set(i + 1, "        <param-value>" + des.encrypt(sftpBackupWorkingDir).replaceAll("\n", "").replaceAll("\r", "") + "</param-value>");
            }
        }
        fu.WriteFile(arr, strFileName);
    }

    public String GetsftpBackupWorkingDir() throws Exception {
        return getDecryptedOrPlainValue("sftpBackupWorkingDir");
    }

    private String getDecryptedOrPlainValue(String paramName) throws Exception {
        FileUtil fu = new FileUtil();
        ArrayList<String> arr = fu.ReadFile(strFileName);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).contains(paramName)) {
                String value = arr.get(i + 1).replaceAll("        <param-value>", "").replaceAll("</param-value>", "");
                EncryptDESUtil des = new EncryptDESUtil();
                String decryptedValue = des.decrypt(value);
                if (decryptedValue == null || decryptedValue.trim().equals("")) {
                    return value;
                }
                return decryptedValue;
            }
        }
        return "";
    }
}
