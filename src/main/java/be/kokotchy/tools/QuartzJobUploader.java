package be.kokotchy.tools;

import be.kokotchy.quartzSorter.model.Job;
import be.kokotchy.quartzSorter.model.QuartzJob;
import be.kokotchy.quartzSorter.util.QuartzJobUtil;
import com.vaadin.data.Item;
import com.vaadin.ui.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

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
    private File directory;
    private Table table;

    public QuartzJobUploader(File file, Label label, Table table) {
        this.file = file;
        this.label = label;
        this.table = table;
        directory = new File(System.getProperty("java.io.tmpdir"), "quartz");
    }

    public QuartzJobUploader(Label label, Table table) {
        this.label = label;
        this.table = table;
        directory = new File(System.getProperty("java.io.tmpdir"), "quartz");
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                displayError("Impossible to create quartz directory");
            }
        }
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        FileOutputStream fos = null;
        try {
            file = new File(directory, filename);
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            Notification.show("Could not open file\n", e.getMessage(), Notification.Type.ERROR_MESSAGE);
            return null;
        }
        return fos;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent succeededEvent) {
        label.setValue("Quartz job uploaded: " + file.getAbsolutePath());
        label.setVisible(true);


        QuartzJob quartzJob = QuartzJobUtil.parseQuartzJob(file);
        System.out.println("There is "+quartzJob.getJobs().size()+" parsed jobs for the file "+file.getAbsolutePath());
        int itemPos = 0;
        int error = 0;
        for (Job job : quartzJob.getJobs()) {
            String jobClass = job.getData().get("JOB_CLASS");
            if (jobClass == null) {
                jobClass = "Undefined";
            }
            Object[] cells = {new CheckBox(), job.getGroup(), job.getName(), job.getJobClass(),
                    jobClass, job.getData().size()};
            Object item = table.addItem(cells, itemPos++);
            if (item == null) {
                error++;
            }
        }
        if (error > 0) {
            displayError("There is " + error + " errors");
        }
        table.setVisible(true);
    }

    private void displayError(String message) {
        Notification.show(message, Notification.Type.ERROR_MESSAGE);
    }

    @Override
    public void uploadFailed(Upload.FailedEvent event) {
        Notification.show("Asshole, it fails...");
    }
}
