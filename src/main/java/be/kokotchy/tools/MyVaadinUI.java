package be.kokotchy.tools;

import be.kokotchy.tools.components.SelectEnvironmentQuartz;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

        QuartzJobUploader quartzJobUploader = new QuartzJobUploader(label, table);
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

        File quartzJobDirectory = new File("/home/canas/Development/sources/theseos/theseos/theseos-trunk/applications/Theseos-Workflows/theseos-packaging/jboss-template/scheduler");
        List<String> environments = new ArrayList<String>();
        String[] list = quartzJobDirectory.list();
        for (String dir : list) {
            if (!".".equals(dir) && !"..".equals(dir) && !".svn".equals(dir)) {
                environments.add(dir);
            }
        }

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.addComponent(new SelectEnvironmentQuartz(environments, quartzJobDirectory));
        horizontalLayout.addComponent(new SelectEnvironmentQuartz(environments, quartzJobDirectory));
        layout.addComponent(horizontalLayout);
    }

}
