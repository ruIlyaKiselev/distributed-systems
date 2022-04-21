package entities;

import database.DatabaseContract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = DatabaseContract.Node.tableName)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeEntity {

    @Id
    @Column(name = DatabaseContract.Node.columnNameId, nullable = false)
    private long id;

    @Column(name = DatabaseContract.Node.columnNameVersion, nullable = false)
    private long version;

    @Column(name = DatabaseContract.Node.columnNameTimestamp, nullable = false)
    private LocalDateTime timestamp;

    @Column(name = DatabaseContract.Node.columnNameUid, nullable = false)
    private long uid;

    @Column(name = DatabaseContract.Node.columnNameUser, nullable = false)
    private String user;

    @Column(name = DatabaseContract.Node.columnNameChangeSet, nullable = false)
    private long changeset;

    @Column(name = DatabaseContract.Node.columnNameLat, nullable = false)
    private double lat;

    @Column(name = DatabaseContract.Node.columnNameLon, nullable = false)
    private double lon;
}
