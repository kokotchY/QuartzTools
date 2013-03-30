package be.kokotchy.tools;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import java.io.File;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);



        Upload upload = new Upload("Upload quartz_jobs.xml", null);
        Label label = new Label("No uploaded quartz");
        Table table = new Table("Content of quartz_jobs.xml");

        File directory = new File(System.getProperty("java.io.tmpdir"), "quartz");
        File file = new File(directory, "quartz_jobs.xml");
        QuartzJobUploader quartzJobUploader = new QuartzJobUploader(file, label, table);
        upload.setReceiver(quartzJobUploader);
        upload.addSucceededListener(quartzJobUploader);
        upload.addFailedListener(quartzJobUploader);
        layout.addComponent(upload);

        label.setVisible(false);
        layout.addComponent(label);

        table.addContainerProperty("Select", CheckBox.class, null);
        table.addContainerProperty("Job Group", String.class, null);
        table.addContainerProperty("Job Name", String.class, null);
        table.addContainerProperty("Quartz Job Class", String.class, null);
        table.addContainerProperty("Job Class", String.class, null);
        table.addContainerProperty("# parameters", Integer.class, null);
        table.setVisible(false);
        layout.addComponent(table);
        quartzJobUploader.uploadSucceeded(null);
    }

}
