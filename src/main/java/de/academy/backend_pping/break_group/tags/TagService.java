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

    /**
     * READ
     * find tags for corresponding foodOption in database and return list of tags
     * @param foodOption
     * @return List<Tag>
     */
    public List<TagDTO> getTagsForFoodOption(FoodOptionDTO foodOption) {
        List<Tag> tagList = tagRepository.findAllByFoodOption(new FoodOption(foodOption));
        return turnEntityToDto(tagList);
    }

    /**
     * CREATE
     * adds new Tag to existing list of tags for a specific foodOption
     * @param tagDTO
     * @param foodOption
     * @return TagDTO
     */
    public TagDTO addTagForFoodOption(TagDTO tagDTO, FoodOptionDTO foodOption) {
        Tag newTag = tagRepository.save(new Tag(new FoodOption(foodOption), tagDTO.getName()));
        return new TagDTO(newTag);
    }

    /**
     * DELETE
     * deletes a Tag by using its id attribute
     * @param id
     */
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    /**
     * turns list of entities into list of dtos
     * @param tags
     * @return List<TagDTO>
     */
    private List<TagDTO> turnEntityToDto(List<Tag> tags) {
        List<TagDTO> tagDTOs = new ArrayList<>();
        for (Tag tag : tags) {
            tagDTOs.add(new TagDTO(tag));
        }
        return tagDTOs;
    }

}
