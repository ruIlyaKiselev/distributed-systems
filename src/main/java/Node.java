import javax.xml.bind.annotation.*;

@XmlRootElement(name = "node")
public class Node {
    private long id;
    private int version;
    private String timestamp;
    private long uid;
    private String user;
    private long changeSet;
    private double lat;
    private double lon;

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    @XmlAttribute
    public void setVersion(int version) {
        this.version = version;
    }

    @XmlAttribute
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @XmlAttribute
    public void setUid(long uid) {
        this.uid = uid;
    }

    @XmlAttribute
    public void setUser(String user) {
        this.user = user;
    }

    @XmlAttribute
    public void setChangeSet(long changeSet) {
        this.changeSet = changeSet;
    }

    @XmlAttribute
    public void setLat(double lat) {
        this.lat = lat;
    }

    @XmlAttribute
    public void setLon(double lon) {
        this.lon = lon;
    }

    public Long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public long getUid() {
        return uid;
    }

    public String getUser() {
        return user;
    }

    public long getChangeSet() {
        return changeSet;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
