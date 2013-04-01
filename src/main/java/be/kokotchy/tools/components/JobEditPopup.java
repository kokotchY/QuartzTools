package be.kokotchy.tools.components;

import be.kokotchy.quartzSorter.model.Job;
import com.vaadin.ui.*;

/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 3/31/13
 * Time: 12:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class JobEditPopup extends Window {

    private FormLayout layout = new FormLayout();

    private TextField group = new TextField("Group");
    private TextField name = new TextField("Name");
    private Button save = new Button("Save");
    private Button reset = new Button("Reset");
    private final Job job;

    public JobEditPopup(final Job job) {
        this.job = job;
        setFieldValues(job);
        layout.addComponent(group);
        layout.addComponent(name);

        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                job.setName(name.getValue());
                job.setGroup(group.getValue());
                close();
            }
        });

        reset.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                setFieldValues(job);
            }
        });
        setContent(layout);
    }

    private void setFieldValues(Job job) {
        group.setValue(job.getGroup());
        name.setValue(job.getName());
    }
}
