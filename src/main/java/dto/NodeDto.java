package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NodeDto {
    long id;
    long version;
    LocalDateTime timestamp;
    long uid;
    String user;
    long changeset;
    double lat;
    double lon;
}
