package be.kokotchy.tools.util;

import be.kokotchy.quartzSorter.model.Job;
import be.kokotchy.quartzSorter.model.JobDigest;
import be.kokotchy.quartzSorter.model.QuartzJob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 3/31/13
 * Time: 12:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuartzJobUtil2 {
    public static List<Job> getJobWithGroup(QuartzJob quartzJob, String group) {
        List<Job> result = new ArrayList<Job>();
        for (Job job : quartzJob.getJobs()) {
            if (group.equals(job.getGroup())) {
                result.add(job);
            }
        }
        Collections.sort(result);
        return result;
    }

    public static List<String> getSortedGroup(QuartzJob quartzJob) {
        List<String> result = new ArrayList<String>();
        for (JobDigest jobDigest : quartzJob.getAllJobDigest()) {
            if (!result.contains(jobDigest.getGroup())) {
                result.add(jobDigest.getGroup());
            }
        }
        Collections.sort(result);
        return result;
    }
}
