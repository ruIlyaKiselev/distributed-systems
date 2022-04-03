import java.util.HashMap;
import java.util.Map;

public class OsmProcessingResult {
    Map<String, Integer> redactions = new HashMap<>();
    Map<String, Integer> tagKeys = new HashMap<>();

    public Map<String, Integer> getRedactions() {
        return redactions;
    }

    public Map<String, Integer> getTagKeys() {
        return tagKeys;
    }

    public void setRedactions(Map<String, Integer> redactions) {
        this.redactions = redactions;
    }

    public void setTagKeys(Map<String, Integer> tagKeys) {
        this.tagKeys = tagKeys;
    }

    public void sortRedactions() {
        redactions = MapUtils.sortByValue(this.redactions);
    }

    public void sortTagsKey() {
        tagKeys = MapUtils.sortByValue(this.tagKeys);
    }

    @Override
    public String toString() {
        return "OsmProcessingResult:" +
                "redaction: " + redactions +
                "\n" +
                "tagKeys: " + tagKeys;
    }
}
