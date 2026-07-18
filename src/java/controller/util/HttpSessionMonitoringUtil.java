package controller.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;

public class HttpSessionMonitoringUtil {

    private static MBeanServerConnection connection;
    private static JMXConnector connector;
    private static final ObjectName service;

    static {
        try {
            service = new ObjectName(
                    "com.bea:Name=DomainRuntimeService,Type=weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean");
        } catch (MalformedObjectNameException e) {
            throw new AssertionError(e.getMessage());
        }
    }

    public static void initConnection(String hostname, String portString, String username, String password) throws IOException, MalformedURLException {
        String protocol = "t3";
        Integer portInteger = Integer.valueOf(portString);
        int port = portInteger.intValue();
        String jndiroot = "/jndi/";
        String mserver = "weblogic.management.mbeanservers.domainruntime";
        JMXServiceURL serviceURL = new JMXServiceURL(protocol, hostname, port, jndiroot + mserver);
        Hashtable h = new Hashtable();
        h.put(Context.SECURITY_PRINCIPAL, username);
        h.put(Context.SECURITY_CREDENTIALS, password);
        h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES, "weblogic.management.remote");
        connector = JMXConnectorFactory.connect(serviceURL, h);
        connection = connector.getMBeanServerConnection();
    }

    public static ObjectName[] getServerRuntimes() throws Exception {
        return (ObjectName[]) connection.getAttribute(service, "ServerRuntimes");
    }

    public void printNameAndState() throws Exception {
        ObjectName[] serverRT = getServerRuntimes();
        System.out.println(" Server   State");
        int length = (int) serverRT.length;
        for (int i = 0; i < length; i++) {
            String name = (String) connection.getAttribute(serverRT[i], "Name");
            String state = (String) connection.getAttribute(serverRT[i], "State");
            System.out.println(name + " : " + state);
        }
    }

    private String getUserLogin(String appname) throws Exception {
        ObjectName[] serverRT = getServerRuntimes();
        int length = (int) serverRT.length;

        for (int i = 0; i < length; i++) {
            ObjectName[] appRT = (ObjectName[]) connection.getAttribute(serverRT[i], "ApplicationRuntimes");
            int appLength = (int) appRT.length;

            for (int x = 0; x < appLength; x++) {
                String applicationName = (String) connection.getAttribute(appRT[x], "Name");
                if (applicationName.equals(appname) == false) {
                    continue;
                }
                ObjectName[] compRT = (ObjectName[]) connection.getAttribute(appRT[x], "ComponentRuntimes");
                int compLength = (int) compRT.length;
                for (int y = 0; y < compLength; y++) {
                    String componentType = (String) connection.getAttribute(compRT[y], "Type");
                    if (componentType.toString().equals("WebAppComponentRuntime")) {
                        String servletSessionsMonitoringIds[] = (String[]) connection.getAttribute(compRT[y], "ServletSessionsMonitoringIds");
                        return String.valueOf(servletSessionsMonitoringIds.length);
                    }
                }
            }
        }
        return "-1";
    }

    public void getServletData() throws Exception {
        ObjectName[] serverRT = getServerRuntimes();
        int length = (int) serverRT.length;

        for (int i = 0; i < length; i++) {
            ObjectName[] appRT = (ObjectName[]) connection.getAttribute(serverRT[i], "ApplicationRuntimes");
            int appLength = (int) appRT.length;

            for (int x = 0; x < appLength; x++) {
                String applicationName = (String) connection.getAttribute(appRT[x], "Name");
                if (applicationName.equals("sample-app") == false) {
                    continue;
                }

                if (applicationName.equals("sample-app")) {
                    System.out.println("sample-app");
                }

                ObjectName[] compRT = (ObjectName[]) connection.getAttribute(appRT[x], "ComponentRuntimes");
                int compLength = (int) compRT.length;

                for (int y = 0; y < compLength; y++) {
                    String componentType = (String) connection.getAttribute(compRT[y], "Type");
                    if (componentType.toString().equals("WebAppComponentRuntime")) {
                        ObjectName[] servletRTs = (ObjectName[]) connection.getAttribute(compRT[y], "Servlets");
                        int servletLength = (int) servletRTs.length;

                        for (int z = 0; z < servletLength; z++) {
                        }
                        String servletSessionsMonitoringIds[] = (String[]) connection.getAttribute(compRT[y], "ServletSessionsMonitoringIds");
                        System.out.println("\n\n\tTotal Number of Users Logged in to Application = " + servletSessionsMonitoringIds.length);;
                        for (int ids = 0; ids < servletSessionsMonitoringIds.length; ids++) {
                            System.out.println("\n\tCurrently Logged In Users List Below:\n ");
                            System.out.println("\t\tUserCount: " + (ids + 1) + "----------Name: " + servletSessionsMonitoringIds[ids]);
                        }
                    }
                }
            }
        }
    }

    public String getUserLoginCount(String hostname, String port, String username, String password, String appname) throws IOException, Exception {


        HttpSessionMonitoringUtil s = new HttpSessionMonitoringUtil();
        initConnection(hostname, port, username, password);
        s.printNameAndState();


        return s.getUserLogin(appname);
    }

    public static void main(String[] args) throws Exception {
        //copy jar file to lib tomcat
        String hostname = "weblogic-host";
        String port = "7001";
        String username = "weblogic";
        String password = "change-me";
        String appname = "sample-app";
        
        HttpSessionMonitoringUtil hsm = new HttpSessionMonitoringUtil();
        System.out.println(hsm.getUserLoginCount(hostname, port, username, password, appname));

        connector.close();
    }
}
