package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ActivityCount
{
    @Id
    private int activityId;
    private String label;
    private long count;

    public ActivityCount(int activityId, String label, long count)
    {
        this.activityId = activityId;
        this.label = label;
        this.count = count;
    }

    public int getActivityId()
    {
        return activityId;
    }

    public String getLabel()
    {
        return label;
    }

    public long getCount()
    {
        return count;
    }
}
