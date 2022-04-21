package services;

import dto.NodeDto;
import lombok.AllArgsConstructor;
import mappers.NodeMapper;
import org.springframework.stereotype.Service;
import repository.NodeRepository;

@AllArgsConstructor
@Service
public class NodeServiceImpl implements NodeService {

    private final NodeRepository repository;

    @Override
    public NodeDto get(int id) {
        return NodeMapper.entityToDto(repository.findById(id).orElse(null));
    }

    @Override
    public NodeDto post(NodeDto nodeDto) {
        return NodeMapper.entityToDto(repository.save(NodeMapper.dtoToEntity(nodeDto)));
    }

    @Override
    public NodeDto put(NodeDto nodeDto) {
        return NodeMapper.entityToDto(repository.save(NodeMapper.dtoToEntity(nodeDto)));
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}