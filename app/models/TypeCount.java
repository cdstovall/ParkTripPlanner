package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TypeCount
{
    @Id
    private int typeId;
    private String label;
    private long count;

    public TypeCount(int typeId, String label, long count)
    {
        this.typeId = typeId;
        this.label = label;
        this.count = count;
    }

    public int getTypeId()
    {
        return typeId;
    }

    public String getLabel()
    {
        return label;
    }

    public long getCount()
    {
        return count;
    }
}
