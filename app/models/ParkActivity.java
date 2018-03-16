package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParkActivity
{
    @Id
    private int parkActivityId;
    private int parkId;
    private int activityId;

    public int getParkActivityId()
    {
        return parkActivityId;
    }

    public int getParkId()
    {
        return parkId;
    }

    public int getActivityId()
    {
        return activityId;
    }
}
