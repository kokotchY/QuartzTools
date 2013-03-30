package be.kokotchy.tools;

import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: kokotchY2
 * Date: 29/03/13
 * Time: 11:29
 * To change this template use File | Settings | File Templates.
 */
public class QuartzJobUploader implements Upload.Receiver, Upload.SucceededListener, Upload.FailedListener {

    private Label label;
    private File file;

    public QuartzJobUploader(Label label) {
        this.label = label;
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        FileOutputStream fos = null;
        try {
            file = new File(System.getProperty("java.io.tmpdir"), filename);
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            Notification.show("Could not open file<br />", e.getMessage(), Notification.Type.ERROR_MESSAGE);
            return null;
        }
        return fos;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent succeededEvent) {
        label.setValue("Quartz job uploaded: "+file.getAbsolutePath());
    }

    @Override
    public void uploadFailed(Upload.FailedEvent event) {
        Notification.show("Asshole, it fails...");
    }
}
