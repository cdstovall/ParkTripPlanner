package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Trip
{
    @Id
    private int tripId;
    private int parkId;
    private int userId;
    private String code;
    private int rating;
    private String review;

    public int getTripId()
    {
        return tripId;
    }

    public int getParkId()
    {
        return parkId;
    }

    public int getUserId()
    {
        return userId;
    }

    public String getCode()
    {
        return code;
    }

    public int getRating()
    {
        return rating;
    }

    public String getReview()
    {
        return review;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public void setReview(String review)
    {
        this.review = review;
    }
}
