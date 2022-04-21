package mappers;

import dto.NodeDto;
import entities.NodeEntity;

public class NodeMapper {
    public static NodeDto entityToDto(NodeEntity nodeEntity) {
        return new NodeDto(
                nodeEntity.getId(),
                nodeEntity.getVersion(),
                nodeEntity.getTimestamp(),
                nodeEntity.getUid(),
                nodeEntity.getUser(),
                nodeEntity.getChangeset(),
                nodeEntity.getLat(),
                nodeEntity.getLon()
        );
    }

    public static NodeEntity dtoToEntity(NodeDto nodeDto) {
        return new NodeEntity(
                nodeDto.getId(),
                nodeDto.getVersion(),
                nodeDto.getTimestamp(),
                nodeDto.getUid(),
                nodeDto.getUser(),
                nodeDto.getChangeset(),
                nodeDto.getLat(),
                nodeDto.getLon()
        );
    }
}
