package models;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Park
{
    @Id
    private int parkId;
    private int typeId;
    private String parkName;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String website;
    private Integer distance;
    private String duration;
    //private byte[] picture;

    public int getParkId()
    {
        return parkId;
    }

    public int getTypeId()
    {
        return typeId;
    }

    public String getParkName()
    {
        return parkName;
    }

    public BigDecimal getLatitude()
    {
        return latitude;
    }

    public BigDecimal getLongitude()
    {
        return longitude;
    }

    public String getWebsite()
    {
        return website;
    }

    public Integer getDistance()
    {
        return distance;
    }

    public void setDistance(Integer distance)
    {
        this.distance = distance;
    }

    public String getDuration()
    {
        return duration;
    }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }

    /*
    public byte[] getPicture()
    {
        return picture;
    }
    */
}
