package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Territory
{
    @Id
    private String territoryId;

    private String territoryDescription;
    private int regionId;

    public String getTerritoryId()
    {
        return territoryId;
    }

    public String getTerritoryDescription()
    {
        return territoryDescription;
    }

    public int getRegionId()
    {
        return regionId;
    }
}

