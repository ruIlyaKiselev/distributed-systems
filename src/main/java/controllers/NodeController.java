package controllers;

import entities.NodeEntity;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import services.NodeService;

@RestController
@RequestMapping("/node")
@AllArgsConstructor
@Log
public class NodeController {

    private final NodeService nodeService;

    @GetMapping("/get/{id}")
    public NodeEntity get(@PathVariable int id) {
        return nodeService.get(id);
    }

    @GetMapping("/post/")
    public NodeEntity post(@RequestBody int id) {
        return nodeService.get(id);
    }
}