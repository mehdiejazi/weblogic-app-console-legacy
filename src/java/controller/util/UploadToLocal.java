package controller.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadFile;

public class UploadToLocal {

    private UploadBean upBean;
    private MultipartFormDataRequest mrequest;
    private String fileName;
    private String fileSize;
    private String infilename;

    /**
     *
     * @throws Exception
     * @param over_write_status
     * @param max_byte_size
     * @param store_address
     * @param param_name
     * @param request
     */
    public UploadToLocal(HttpServletRequest request, String param_name,
            String store_address, long max_byte_size, boolean over_write_status)
            throws Exception {
        this.infilename = param_name;
        this.upBean = new UploadBean();
        upBean.setFolderstore(store_address);
        upBean.setFilesizelimit(max_byte_size);
        upBean.setOverwrite(over_write_status);

        if (MultipartFormDataRequest.isMultipartFormData(request)) {
            mrequest = new MultipartFormDataRequest(request);

            Hashtable files = mrequest.getFiles();

            if ((files != null) && (!files.isEmpty())) {
                UploadFile file = (UploadFile) files.get(param_name);
                this.fileName = file.getFileName();
                this.fileSize = file.getFileSize() + "";
            }
        }
    }

    public boolean save() throws Exception {
        try {
            upBean.store(mrequest, infilename);
            return true;
        } catch (Exception e) {  
               return false;
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public HashMap getParameters() {
        HashMap<String, String> hshmp = new HashMap<String, String>();
        Enumeration enmParameters = mrequest.getParameterNames();
        while (enmParameters.hasMoreElements()) {
            String strKey = enmParameters.nextElement().toString();
            String strValue = mrequest.getParameter(strKey);
            hshmp.put(strKey, strValue);
        }
        return hshmp;
    }
}
