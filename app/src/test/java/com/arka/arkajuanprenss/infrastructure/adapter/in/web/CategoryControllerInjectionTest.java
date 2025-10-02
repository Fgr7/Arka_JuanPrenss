package com.arka.arkajuanprenss.infrastructure.adapter.in.web;
import com.arka.arkajuanprenss.domain.model.Category;
import com.arka.arkajuanprenss.domain.port.in.CategoryUseCase;
import com.arka.arkajuanprenss.infrastructure.adapter.in.web.dto.CategoryDTO;
import com.arka.arkajuanprenss.infrastructure.adapter.in.web.mapper.CategoryWebMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerInjectionTest {
      @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryUseCase categoryUseCase;

    @MockBean
    private CategoryWebMapper webMapper;

    @Test
    void shouldReturnAllCategories() throws Exception {
        // Arrange
        Category category1 = new Category(1L, "Periféricos");
        Category category2 = new Category(2L, "Componentes");

        List<Category> categories = Arrays.asList(category1, category2);
        List<CategoryDTO> categoriesDTO = Arrays.asList(
                new CategoryDTO(1L, "Periféricos"),
                new CategoryDTO(2L, "Componentes")
        );

        when(categoryUseCase.getAllCategories()).thenReturn(categories);
        when(webMapper.toDTO(categories)).thenReturn(categoriesDTO);

        // Act & Assert
        mockMvc.perform(get("/categorias")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Periféricos"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nombre").value("Componentes"));
    }

    @Test
    void shouldReturnCategoryById() throws Exception {
        Category category = new Category(1L, "Periféricos");
        CategoryDTO categoryDTO = new CategoryDTO(1L, "Periféricos");

        when(categoryUseCase.getCategoryById(1L)).thenReturn(category);
        when(webMapper.toDTO(category)).thenReturn(categoryDTO);

        mockMvc.perform(get("/categorias/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Periféricos"));
    }
}
