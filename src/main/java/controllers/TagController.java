package controllers;

import entities.TagEntity;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.TagService;

@RestController
@RequestMapping("/tag")
@AllArgsConstructor
@Log
public class TagController {

    private final TagService tagService;

    @GetMapping("/get/{id}")
    public TagEntity get(@PathVariable int id) {
        return tagService.get(id);
    }
}