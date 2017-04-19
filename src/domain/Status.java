package domain;

/**
 * Created by Thomas on 15/04/2017.
 */
public enum Status
{
    ONLINE,
    BUSY,
    AWAY,
    OFFLINE
    ;
    private String description;

    Status(String description)
    {
        this.description = description;
    }

    Status(){}

    public String getDescription() {
        return description;
    }
}
