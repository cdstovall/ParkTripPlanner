package models;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Park
{
    @Id
    private int parkId;
    private String parkName;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public int getParkId()
    {
        return parkId;
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
}