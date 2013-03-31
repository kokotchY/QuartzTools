package be.kokotchy.tools.components;

import be.kokotchy.quartzSorter.model.Job;
import be.kokotchy.quartzSorter.model.QuartzJob;
import be.kokotchy.quartzSorter.util.QuartzJobUtil;
import be.kokotchy.tools.util.QuartzJobUtil2;
import com.vaadin.data.Property;
import com.vaadin.ui.*;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 3/31/13
 * Time: 10:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class SelectEnvironmentQuartz extends CustomComponent implements Property.ValueChangeListener {

    private VerticalLayout layout = new VerticalLayout();
    private ComboBox comboBox = new ComboBox("Environment");
    private ListSelect listSelect = new ListSelect("Jobs");
    private final File configDirectory;
    private Tree tree = new Tree();

    public SelectEnvironmentQuartz(List<String> environments, File configDirectory) {
        this.configDirectory = configDirectory;
        setCompositionRoot(layout);
        layout.addComponent(comboBox);
        for (String environment : environments) {
            comboBox.addItem(environment);
        }
        comboBox.setImmediate(true);
        comboBox.addValueChangeListener(this);

        listSelect.setVisible(false);
        listSelect.setSizeFull();
        layout.addComponent(listSelect);
        tree.setImmediate(true);
        tree.setSizeFull();
        layout.addComponent(tree);
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        String environment = (String) event.getProperty().getValue();
        if (environment != null) {
            String pathSeparator = File.separator;
            String quartzFilePath = environment + pathSeparator + "server" + pathSeparator + "theseos" + pathSeparator + "conf" + pathSeparator + "scheduler" + pathSeparator + "quartz_jobs.xml";
            File quartzFile = new File(configDirectory, quartzFilePath);
            if (quartzFile.exists()) {
                listSelect.removeAllItems();
                tree.removeAllItems();
                QuartzJob quartzJob = QuartzJobUtil.parseQuartzJob(quartzFile);
                for (Job job : quartzJob.getJobs()) {
                    listSelect.addItem(job);
                }
                listSelect.setImmediate(true);
                listSelect.setVisible(true);
                List<String> groups = QuartzJobUtil2.getSortedGroup(quartzJob);
                for (String group : groups) {
                    tree.addItem(group);
                    List<Job> jobs = QuartzJobUtil2.getJobWithGroup(quartzJob, group);
                    for (Job job : jobs) {
                        tree.addItem(job);
                        tree.setParent(job, group);
                        tree.setChildrenAllowed(job, false);
                    }

                    tree.expandItemsRecursively(group);
                }

                tree.setVisible(true);
            }
        }
    }
}
