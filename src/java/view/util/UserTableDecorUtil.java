package view.util;

import model.to.UsersTo;
import org.displaytag.decorator.TableDecorator;

public class UserTableDecorUtil extends TableDecorator  {

    public int getIndex(){
        return super.getListIndex()+1;
    }
    public String getEdit() {
        UsersTo usersTo = (UsersTo) getCurrentRowObject();
        long lId = usersTo.getId();

        String strReturn = "";

        strReturn = strReturn.concat("<a href=\"./edituser.do?id=");
        strReturn = strReturn.concat(String.valueOf(lId));
        strReturn = strReturn.concat("\">");
        strReturn = strReturn.concat("<img src=\"../theme/images/edit.png\" />");
        strReturn = strReturn.concat("</a>");
        return strReturn;
    }

    public String getDelete() {
        UsersTo usersTo = (UsersTo) getCurrentRowObject();
        long lId = usersTo.getId();

        String strReturn = "";

        strReturn = strReturn.concat("<a href=\"./deluser.do?id=");
        strReturn = strReturn.concat(String.valueOf(lId));
        strReturn = strReturn.concat("\">");
        strReturn = strReturn.concat("<img src=\"../theme/images/del.png\" />");
        strReturn = strReturn.concat("</a>");
        return strReturn;
    }

    public String getLog() {
        UsersTo usersTo = (UsersTo) getCurrentRowObject();
        long lId = usersTo.getId();

        String strReturn = "";

        strReturn = strReturn.concat("<a href=\"./showlog.do?id=");
        strReturn = strReturn.concat(String.valueOf(lId));
        strReturn = strReturn.concat("\">");
        strReturn = strReturn.concat("<img src=\"../theme/images/log.png\" />");
        strReturn = strReturn.concat("</a>");
        return strReturn;
    }
}
