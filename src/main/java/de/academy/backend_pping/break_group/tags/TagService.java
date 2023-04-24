package de.academy.backend_pping.break_group.tags;

import de.academy.backend_pping.break_group.foodoptions.FoodOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<TagDTO> getTagsForFoodOption(FoodOption foodOption) {

        List<TagDTO> result = new ArrayList<>();
        List<Tag> tagList = tagRepository.findAllByFoodOption(foodOption);
        for (Tag tag : tagList) {
            result.add(new TagDTO(tag));
        }
        return result;
    }

    public TagDTO addTagForFoodOption(TagDTO tagDTO, FoodOption foodOption) {
        Tag newTag = tagRepository.save(new Tag(foodOption, tagDTO.getName()));
        return new TagDTO(newTag);
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

}
