package ru.tadzh.persist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tadzh.persist.entity.Picture;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    @Query("select p from Picture p where p.product.id = :productId")
    List<Picture> findByProductId(@Param("productId") Long productId);
}
