package be.kokotchy.tools;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

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

        Button displayTmpDir = new Button("Display tmp dir");
        displayTmpDir.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("Tmp dir: "+System.getProperty("java.io.tmpdir"));
            }
        });
        layout.addComponent(displayTmpDir);
        Button button = new Button("Click Me 2");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                layout.addComponent(new Label("Thank you for clicking"));
            }
        });
        layout.addComponent(button);
        Label label = new Label("Nothing");


        Upload upload = new Upload("quartz_jobs.xml", null);

        QuartzJobUploader quartzJobUploader = new QuartzJobUploader(label);
        upload.setReceiver(quartzJobUploader);
        upload.addSucceededListener(quartzJobUploader);
        upload.addFailedListener(quartzJobUploader);
        layout.addComponent(upload);
    }

}
