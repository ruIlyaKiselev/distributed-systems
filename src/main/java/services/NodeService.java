package services;

import dto.NodeDto;

public interface NodeService {

    NodeDto get(int id);
    NodeDto post(NodeDto nodeDto);
    NodeDto put(NodeDto nodeDto);
    void delete(int id);

}