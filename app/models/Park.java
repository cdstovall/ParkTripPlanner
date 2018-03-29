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
    private Integer durationValue;
    private String durationText;
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

    public Integer getDurationValue()
    {
        return durationValue;
    }

    public void setDurationValue(Integer distance)
    {
        this.durationValue = distance;
    }

    public String getDurationText()
    {
        return durationText;
    }

    public void setDurationText(String duration)
    {
        this.durationText = duration;
    }

    /*
    public byte[] getPicture()
    {
        return picture;
    }
    */
}
