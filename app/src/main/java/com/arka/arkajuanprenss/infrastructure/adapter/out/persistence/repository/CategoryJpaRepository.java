package com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.repository;


import com.arka.arkajuanprenss.infrastructure.adapter.out.persistence.entity.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, Long> {  
}
