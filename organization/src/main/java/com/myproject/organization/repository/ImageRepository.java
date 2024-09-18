package com.myproject.organization.repository;

import com.myproject.organization.entites.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository <Image,Long> {
}
