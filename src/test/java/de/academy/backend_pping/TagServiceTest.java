package de.academy.backend_pping;

import de.academy.backend_pping.break_group.foodoptions.FoodOption;
import de.academy.backend_pping.break_group.foodoptions.FoodOptionDTO;
import de.academy.backend_pping.break_group.tags.Tag;
import de.academy.backend_pping.break_group.tags.TagDTO;
import de.academy.backend_pping.break_group.tags.TagRepository;
import de.academy.backend_pping.break_group.tags.TagService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagService tagService;
    @Test
    public void getTagsForFoodOptionTest() {
        // Arrange
        FoodOptionDTO foodOptionDTO = new FoodOptionDTO();
        List<Tag> tagList = new ArrayList<>();
        Tag tag1 = new Tag(1L, new FoodOption(foodOptionDTO), "vegan");
        Tag tag2 = new Tag(2L, new FoodOption(foodOptionDTO), "Kartenzahlung");
        tagList.add(tag1);
        tagList.add(tag2);
        when(tagRepository.findAllByFoodOption(any(FoodOption.class))).thenReturn(tagList);

        // Act
        List<TagDTO> tagDTOList = tagService.getTagsForFoodOption(foodOptionDTO);

        // Assert
        assertEquals(2, tagDTOList.size());
        assertEquals(1L, tagDTOList.get(0).getId());
        assertEquals("vegan", tagDTOList.get(0).getName());
        assertEquals(2L, tagDTOList.get(1).getId());
        assertEquals("Kartenzahlung", tagDTOList.get(1).getName());
    }

    @Test
    public void addTagForFoodOptionTest() {
        // Arrange
        FoodOptionDTO foodOptionDTO = new FoodOptionDTO();
        TagDTO tagDTO = new TagDTO(null, foodOptionDTO, "laktosefrei");
        Tag newTag = new Tag(1L, new FoodOption(foodOptionDTO), "laktosefrei");
        when(tagRepository.save(any(Tag.class)))
                .thenReturn(newTag);

        // Act
        TagDTO addedTagDTO = tagService.addTagForFoodOption(tagDTO, foodOptionDTO);

        // Assert
        assertEquals("laktosefrei", addedTagDTO.getName());
    }

    @Test
    public void deleteTagTest() {
        // Arrange
        Long tagId = 1L;

        // Act
        tagService.deleteTag(tagId);

        // Assert
        // verify that deleteById method of tagRepository is called with the correct argument
        verify(tagRepository, times(1)).deleteById(tagId);
    }
}