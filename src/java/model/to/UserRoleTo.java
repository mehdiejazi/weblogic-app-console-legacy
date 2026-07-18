/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.to;

public class UserRoleTo {

    public UserRoleTo() {
        Appname = "app-console";
    }
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    private String Username;
    private String Rolename;
    private String Appname;

    public String getUsername() {
        return Username;
    }

    public String getRolename() {
        return Rolename;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setRolename(String Rolename) {
        this.Rolename = Rolename;
    }

    public String getAppname() {
        return Appname;
    }

    public void setAppname(String Appname) {
        this.Appname = Appname;
    }
}
