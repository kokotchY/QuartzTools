package be.kokotchy.tools.model;

import be.kokotchy.quartzSorter.model.Job;
import com.vaadin.data.Item;
import com.vaadin.event.Transferable;
import com.vaadin.ui.Component;

import java.util.Collection;


/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 3/31/13
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class JobVaadin implements Transferable {
    
    private String name;
    
    private String group;
    
    private Job job;

    public JobVaadin(Job job) {
        this.job = job;
        this.name = job.getName();
        this.group = job.getGroup();
    }

    public String getGroup() {
        return group;
    }

    public Job getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object getData(String dataFlavor) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setData(String dataFlavor, Object value) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<String> getDataFlavors() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Component getSourceComponent() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
