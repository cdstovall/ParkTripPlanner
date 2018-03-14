package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Activity
{
    @Id
    private int ActivityId;
    private String activityName;

    public int getActivityId()
    {
        return ActivityId;
    }

    public String getActivityName()
    {
        return activityName;
    }
}
