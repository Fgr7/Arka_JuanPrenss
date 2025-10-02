package com.arka.arkajuanprenss.infrastructure.adapter.in.web;
import com.arka.arkajuanprenss.domain.model.Product;
import com.arka.arkajuanprenss.domain.port.in.ProductUseCase;
import com.arka.arkajuanprenss.infrastructure.adapter.in.web.dto.ProductDTO;
import com.arka.arkajuanprenss.infrastructure.adapter.in.web.mapper.ProductWebMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerInjectionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductUseCase productUseCase;

    @MockBean
    private ProductWebMapper webMapper;

    @Test
    void shouldReturnAllProducts() throws Exception {
        // Entidad de dominio
        Product product = new Product();
        product.setId(1L);
        product.setNombre("Mouse");
        product.setPrecioUnitario(new BigDecimal("25.00")); // 👈 CORREGIDO

        // DTO
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setNombre("Mouse");
        productDTO.setPrecioUnitario(new BigDecimal("25.00")); // 👈 DTO puede seguir usando "precio"

        // Mocking del caso de uso y mapper
        Mockito.when(productUseCase.getAllProducts()).thenReturn(List.of(product));
        Mockito.when(webMapper.toDTO(List.of(product))).thenReturn(List.of(productDTO));

        // Ejecución de la petición y validaciones
        mockMvc.perform(get("/productos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Mouse"))
                .andExpect(jsonPath("$[0].precioUnitario").value(25.00));; // 👈 validación en el DTO
    }
}
