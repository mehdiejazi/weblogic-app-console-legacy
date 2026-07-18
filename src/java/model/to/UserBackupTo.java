package model.to;

public class UserBackupTo {

    private long id;
    private long UserID;
    private String Username;
    private String Reason;
    private String IP;
    private String GDate;
    private String PDate;
    private String Time;

    public UserBackupTo() {
    }

    public long getId() {
        return id;
    }

    public long getUserID() {
        return UserID;
    }

    public String getUsername() {
        return Username;
    }

    public String getReason() {
        return Reason;
    }

    public String getIP() {
        return IP;
    }

    public String getGDate() {
        return GDate;
    }

    public String getPDate() {
        return PDate;
    }

    public String getTime() {
        return Time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserID(long UserID) {
        this.UserID = UserID;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setGDate(String GDate) {
        this.GDate = GDate;
    }

    public void setPDate(String PDate) {
        this.PDate = PDate;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }
}
