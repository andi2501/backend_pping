package de.academy.backend_pping.break_group.tags;

import de.academy.backend_pping.break_group.foodoptions.FoodOption;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<TagDTO> getTagsForFoodOption(FoodOptionDTO foodOption) {
        List<Tag> tagList = tagRepository.findAllByFoodOption(new FoodOption(foodOption));
        return turnEntityToDto(tagList);
    }

    public TagDTO addTagForFoodOption(TagDTO tagDTO, FoodOptionDTO foodOption) {
        Tag newTag = tagRepository.save(new Tag(new FoodOption(foodOption), tagDTO.getName()));
        return new TagDTO(newTag);
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    private List<TagDTO> turnEntityToDto(List<Tag> tags) {
        List<TagDTO> tagDTOs = new ArrayList<>();
        for (Tag tag : tags) {
            tagDTOs.add(new TagDTO(tag));
        }
        return tagDTOs;
    }

}
