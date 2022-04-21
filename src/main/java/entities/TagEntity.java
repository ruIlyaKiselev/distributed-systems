package entities;

import database.DatabaseContract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(CompositeTagPrimaryKey.class)
@Table(name = DatabaseContract.Tag.tableName)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagEntity {

    @Id
    @Column(name = DatabaseContract.Tag.columnNodeId, nullable = false)
    private long nodeId;

    @Id
    @Column(name = DatabaseContract.Tag.columnNameK, nullable = false)
    private String k;

    @Column(name = DatabaseContract.Tag.columnNameV, nullable = false)
    private String v;
}
