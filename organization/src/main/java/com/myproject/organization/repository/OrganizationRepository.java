package com.myproject.organization.repository;

import com.myproject.organization.entites.Organization;
import com.myproject.organization.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {
    List<Organization> findByCategory(Category category);
    @Modifying
    @Query("UPDATE Organization o SET o.price = :price WHERE o.id = :id")
    void updatePrice(@Param("id") Long id, @Param("price") Double price);

}
