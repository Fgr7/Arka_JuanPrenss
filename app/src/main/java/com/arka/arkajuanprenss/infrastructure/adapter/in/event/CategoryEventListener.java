package com.arka.arkajuanprenss.infrastructure.adapter.in.event;
import com.arka.arkajuanprenss.domain.event.CategoryCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CategoryEventListener {
    
    @EventListener
    public void handleCategoryCreated(CategoryCreatedEvent event) {
        System.out.println("✅ Evento recibido -> Nueva categoría creada: " 
                           + event.getNombre() + " (ID: " + event.getId() + ")");
    }
}
