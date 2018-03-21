package models;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Type
{
    @Id
    private int typeId;
    private String typeName;

    public int getTypeId()
    {
        return typeId;
    }

    public String getTypeName()
    {
        return typeName;
    }
}
