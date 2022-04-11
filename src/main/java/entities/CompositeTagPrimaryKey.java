package entities;

import java.io.Serializable;
import java.util.Objects;

public class CompositeTagPrimaryKey implements Serializable {

    private long nodeId;
    private String k;

    public CompositeTagPrimaryKey(Integer nodeId, String k) {
        this.nodeId = nodeId;
        this.k = k;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeTagPrimaryKey compositeTagPrimaryKey = (CompositeTagPrimaryKey) o;
        return Objects.equals(nodeId, compositeTagPrimaryKey.nodeId) && Objects.equals(k, compositeTagPrimaryKey.k);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, k);
    }
}
