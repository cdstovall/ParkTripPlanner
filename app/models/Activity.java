package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Activity
{
    @Id
    private int activityId;
    private String activityName;

    public int getActivityId()
    {
        return activityId;
    }

    public String getActivityName()
    {
        return activityName;
    }
}
