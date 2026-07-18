package model.to;

public class PagesTo {

    private long id;
    private String Pagename;
    private String Pagetitle;
    
    public PagesTo() {
    }

    public PagesTo(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPagename() {
        return Pagename;
    }

    public void setPagename(String pagename) {
        this.Pagename = pagename;
    }

    /**
     * @return the Pagetitle
     */
    public String getPagetitle() {
        return Pagetitle;
    }

    /**
     * @param Pagetitle the Pagetitle to set
     */
    public void setPagetitle(String Pagetitle) {
        this.Pagetitle = Pagetitle;
    }
}
