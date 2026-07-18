package controller.util;

import java.io.*;
import java.util.ArrayList;

public class FileUtil {

    public void MakeApplicationStatusPy(String path, String xmlPath) throws Exception {

        ArrayList<String> arrayList = new ArrayList<String>();
        WebParamUtil wpu = new WebParamUtil();
        wpu.SetFileName(xmlPath);

        arrayList.add("#variable definitions");
        arrayList.add("username='"+wpu.GetwlUsername()+"' ");
        arrayList.add("password='"+wpu.GetwlPassword()+"'");
        arrayList.add("urladmin='"+wpu.GetsftpDeployHostIP()+ ":7001'");
        arrayList.add("appName='"+wpu.GetappName()+"'");
        arrayList.add("serverName='AdminServer'");
        arrayList.add("serverName='AdminServer'");
        arrayList.add("#connects to admin server");
        arrayList.add("connect(username, password, urladmin)");
        arrayList.add("cd('domainRuntime:/AppRuntimeStateRuntime/AppRuntimeStateRuntime')");
        arrayList.add("#get current real state for app in specific server");
        arrayList.add("currentState = cmo.getCurrentState(appName, serverName)");
        arrayList.add("print currentState");
        
        WriteFile(arrayList, path);

    }

    public ArrayList<String> ReadFile(String path) throws FileNotFoundException, IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> arrlst = new ArrayList<String>();
        while (br.ready()) {
            arrlst.add(br.readLine());
        }
        br.close();
        return arrlst;
    }

    public boolean WriteFile(ArrayList<String> arrayList, String path) throws IOException {
        File file = new File(path);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.flush();
        for (String strLine : arrayList) {
            bw.write(strLine);
            bw.newLine();
        }
        bw.close();
        return false;
    }
}
