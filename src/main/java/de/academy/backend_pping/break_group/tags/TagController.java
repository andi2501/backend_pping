package de.academy.backend_pping.break_group.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/break/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public List<TagDTO> getTagsForFoodOption() {

        return new ArrayList<>();
    }
}
