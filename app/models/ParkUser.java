package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParkUser
{
    @Id
    private int userId;
    private String phone;

    public int getUserId()
    {
        return userId;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }

}
