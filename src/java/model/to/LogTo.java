package model.to;

public class LogTo {

    private long id;
    private String IP;
    private String Username;
    private String Event;
    private String GDate;
    private String PDate;
    private String Time;

    public long getId() {
        return id;
    }

    public String getIP() {
        return IP;
    }

    public String getUsername() {
        return Username;
    }

    public String getEvent() {
        return Event;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setEvent(String Event) {
        this.Event = Event;
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
