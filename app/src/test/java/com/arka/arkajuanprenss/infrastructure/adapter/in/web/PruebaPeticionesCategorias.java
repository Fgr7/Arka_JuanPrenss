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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * PruebaPeticionesCategorias — prueba 5 endpoints CRUD para CategoryController
 */
@WebMvcTest(controllers = CategoryController.class)
public class PruebaPeticionesCategorias {
     @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryUseCase categoryUseCase;

    @MockBean
    private CategoryWebMapper webMapper;

    // 1) GET /categorias
    @Test
    void testGetAllCategories() throws Exception {
        Category c1 = new Category(1L, "Periféricos");
        Category c2 = new Category(2L, "Componentes");

        CategoryDTO dto1 = new CategoryDTO(1L, "Periféricos");
        CategoryDTO dto2 = new CategoryDTO(2L, "Componentes");

        List<Category> domainList = List.of(c1, c2);
        List<CategoryDTO> dtoList = List.of(dto1, dto2);

        Mockito.when(categoryUseCase.getAllCategories()).thenReturn(domainList);
        Mockito.when(webMapper.toDTO(domainList)).thenReturn(dtoList);

        mockMvc.perform(get("/categorias")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Periféricos"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nombre").value("Componentes"));
    }

    // 2) GET /categorias/{id}
    @Test
    void testGetCategoryById() throws Exception {
        Category c = new Category(1L, "Periféricos");
        CategoryDTO dto = new CategoryDTO(1L, "Periféricos");

        Mockito.when(categoryUseCase.getCategoryById(1L)).thenReturn(c);
        Mockito.when(webMapper.toDTO(c)).thenReturn(dto);

        mockMvc.perform(get("/categorias/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Periféricos"));
    }

    // 3) POST /categorias
    @Test
    void testCreateCategory() throws Exception {
        String jsonRequest = """
                {
                  "nombre": "Monitores"
                }
                """;

        Category domainIncoming = new Category(null, "Monitores");
        Category domainSaved = new Category(10L, "Monitores");
        CategoryDTO responseDto = new CategoryDTO(10L, "Monitores");

        Mockito.when(webMapper.toDomain(any(CategoryDTO.class))).thenReturn(domainIncoming);
        Mockito.when(categoryUseCase.createCategory(any(Category.class))).thenReturn(domainSaved);
        Mockito.when(webMapper.toDTO(domainSaved)).thenReturn(responseDto);

        mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.nombre").value("Monitores"));
    }

    // 4) PUT /categorias/{id}
    @Test
    void testUpdateCategory() throws Exception {
        String jsonRequest = """
                {
                  "nombre": "Monitores LED"
                }
                """;

        Category domainIncoming = new Category(null, "Monitores LED");
        Category domainUpdated = new Category(10L, "Monitores LED");
        CategoryDTO responseDto = new CategoryDTO(10L, "Monitores LED");

        Mockito.when(webMapper.toDomain(any(CategoryDTO.class))).thenReturn(domainIncoming);
        Mockito.when(categoryUseCase.updateCategory(10L, domainIncoming)).thenReturn(domainUpdated);
        Mockito.when(webMapper.toDTO(domainUpdated)).thenReturn(responseDto);

        mockMvc.perform(put("/categorias/{id}", 10L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.nombre").value("Monitores LED"));
    }

    // 5) DELETE /categorias/{id}
    @Test
    void testDeleteCategory() throws Exception {
        // Mock de existsById si tu usecase lo usa internamente; aquí simplemente verificamos el resultado HTTP
        Mockito.doNothing().when(categoryUseCase).deleteCategory(5L);

        mockMvc.perform(delete("/categorias/{id}", 5L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
