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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)
public class PruebaPeticionesProductos {
     @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductUseCase productUseCase;

    @MockBean
    private ProductWebMapper productWebMapper;

    @Test
    void testGetAllProducts() throws Exception {
        Product p = new Product(); p.setId(1L); p.setNombre("Mouse"); p.setPrecioUnitario(new BigDecimal("25.00"));
        ProductDTO pDto = new ProductDTO(); pDto.setId(1L); pDto.setNombre("Mouse"); pDto.setPrecioUnitario(new BigDecimal("25.00"));

        Mockito.when(productUseCase.getAllProducts()).thenReturn(List.of(p));
        Mockito.when(productWebMapper.toDTO(List.of(p))).thenReturn(List.of(pDto));

        mockMvc.perform(get("/productos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Mouse"))
                .andExpect(jsonPath("$[0].precioUnitario").value(25.00)); // ajusta si tu DTO expone "precio"
    }

    @Test
    void testGetProductById() throws Exception {
        Product p = new Product(); p.setId(2L); p.setNombre("Teclado"); p.setPrecioUnitario(new BigDecimal("45.50"));
        ProductDTO pDto = new ProductDTO(); pDto.setId(2L); pDto.setNombre("Teclado"); pDto.setPrecioUnitario(new BigDecimal("45.50"));

        Mockito.when(productUseCase.getProductById(2L)).thenReturn(p);
        Mockito.when(productWebMapper.toDTO(p)).thenReturn(pDto);

        mockMvc.perform(get("/productos/{id}", 2L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nombre").value("Teclado"))
                .andExpect(jsonPath("$.precioUnitario").value(45.50));
    }

    @Test
    void testCreateProduct() throws Exception {
        String jsonRequest = """
                {
                  "nombre": "Auriculares",
                  "descripcion": "Inalámbricos",
                  "categoriaId": 1,
                  "marca": "BrandX",
                  "precio": 79.99,
                  "stock": 10
                }
                """;

        Product incoming = new Product(); incoming.setNombre("Auriculares"); incoming.setPrecioUnitario(new BigDecimal("79.99")); incoming.setStock(10);
        Product saved = new Product(); saved.setId(3L); saved.setNombre("Auriculares"); saved.setPrecioUnitario(new BigDecimal("79.99"));
        ProductDTO responseDto = new ProductDTO(); responseDto.setId(3L); responseDto.setNombre("Auriculares"); responseDto.setPrecioUnitario(new BigDecimal("79.99"));

        Mockito.when(productWebMapper.toDomain(any())).thenReturn(incoming);
        Mockito.when(productUseCase.createProduct(any())).thenReturn(saved);
        Mockito.when(productWebMapper.toDTO(saved)).thenReturn(responseDto);

        mockMvc.perform(post("/productos").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.nombre").value("Auriculares"))
                .andExpect(jsonPath("$.precioUnitario").value(79.99));
    }
}
