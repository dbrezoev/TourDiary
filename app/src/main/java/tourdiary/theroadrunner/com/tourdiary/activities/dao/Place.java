package tourdiary.theroadrunner.com.tourdiary.activities.dao;

/**
 * Created by MIRO on 10/12/2014.
 */
public class Place {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return name;
    }
}
