package mappers;

import dto.TagDto;
import entities.TagEntity;

public class TagMapper {
    public static TagDto entityToDto(TagEntity tagEntity) {
        return new TagDto(
                tagEntity.getNodeId(),
                tagEntity.getK(),
                tagEntity.getV()
        );
    }

    public static TagEntity dtoToEntity(TagDto tagDto) {
        return new TagEntity(
                tagDto.getNodeId(),
                tagDto.getK(),
                tagDto.getV()
        );
    }
}
