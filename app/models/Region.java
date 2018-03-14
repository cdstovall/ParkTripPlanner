package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Region
{
    @Id
    private int regionId;
    private String regionDescription;

    public int getRegionId()
    {
        return regionId;
    }

    public String getRegionDescription()
    {
        return regionDescription;
    }
}
