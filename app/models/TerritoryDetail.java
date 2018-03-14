package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TerritoryDetail
{
    @Id
    private String territoryId;
    private int regionId;
    public String territoryDescription;
    public String regionDescription;

    public TerritoryDetail(String territoryId, String territoryDescription, int regionId, String regionDescription)
    {
        this.territoryId = territoryId;
        this.territoryDescription = territoryDescription;
        this.regionId = regionId;
        this.regionDescription = regionDescription;
    }

    public String getTerritoryId()
    {
        return territoryId;
    }

    public int getRegionId()
    {
        return regionId;
    }

    public String getTerritoryDescription()
    {
        return territoryDescription;
    }

    public String getRegionDescription()
    {
        return regionDescription;
    }
}
