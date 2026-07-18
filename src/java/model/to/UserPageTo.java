package model.to;

public class UserPageTo {

    private long Userid;
    
    private long Pageid;
    
    private long id;

    public UserPageTo() {
    }

    public UserPageTo(long id) {
        this.id = id;
    }

    public long getUserid() {
        return Userid;
    }

    public void setUserid(long Userid) {
        this.Userid = Userid;
    }

    public long getPageid() {
        return Pageid;
    }

    public void setPageid(long Pageid) {
        this.Pageid = Pageid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
