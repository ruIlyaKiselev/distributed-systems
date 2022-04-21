package services;

import dto.TagDto;
import lombok.AllArgsConstructor;
import mappers.TagMapper;
import org.springframework.stereotype.Service;
import repository.TagRepository;

@AllArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository repository;

    @Override
    public TagDto get(int id) {
        return TagMapper.entityToDto(repository.findById(id).orElse(null));
    }

    @Override
    public TagDto post(TagDto tagDto) {
        return TagMapper.entityToDto(repository.save(TagMapper.dtoToEntity(tagDto)));
    }

    @Override
    public TagDto put(TagDto tagDto) {
        return TagMapper.entityToDto(repository.save(TagMapper.dtoToEntity(tagDto)));
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}