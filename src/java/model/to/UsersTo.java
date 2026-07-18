package model.to;

import java.util.List;

public class UsersTo {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    private String Username;
    private String Password;
    private String Fullname;
    private List<UserRoleTo> UserRoleTos;
    private List<String> lstPages;

    public UsersTo() {
    }

    public UsersTo(String username) {
        this.Username = username;
    }

    @Override
    public String toString() {
        return "model.Users[ username=" + getUsername() + " ]";
    }

    /**
     * @return the Username
     */
    public String getUsername() {
        return Username;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @return the UserRoleTos
     */
    public List<UserRoleTo> getUserRoleTos() {
        return UserRoleTos;
    }

    public String getUserroletostags() {
        String strReturn = "";
        for (UserRoleTo urto : UserRoleTos) {
            String str = "";
            if (urto.getRolename().equals("admin")) {
                str = "دسترسی مدیریتی";
            }
            if (urto.getRolename().equals("employee")) {
                str = "دسترسی کاربری";
            }
            strReturn = strReturn + str + "<br/> \n";
        }
        return strReturn;
    }

    /**
     * @param Username the Username to set
     */
    public void setUsername(String Username) {
        this.Username = Username;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * @param UserRoleTos the UserRoleTos to set
     */
    public void setUserRoleTos(List<UserRoleTo> UserRoleTos) {
        this.UserRoleTos = UserRoleTos;
    }

    /**
     * @return the lstPages
     */
    public List<String> getLstPages() {
        return lstPages;
    }

    /**
     * @param lstPages the lstPages to set
     */
    public void setLstPages(List<String> lstPages) {
        this.lstPages = lstPages;
    }

    /**
     * @return the Fullname
     */
    public String getFullname() {
        return Fullname;
    }

    /**
     * @param Fullname the Fullname to set
     */
    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }
}
