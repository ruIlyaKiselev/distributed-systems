package services;

import dto.TagDto;

public interface TagService {

    TagDto get(int id);
    TagDto post(TagDto tagDto);
    TagDto put(TagDto tagDto);
    void delete(int id);

}
