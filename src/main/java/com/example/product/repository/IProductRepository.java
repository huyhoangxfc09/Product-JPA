package com.example.product.repository;

import com.example.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select p from Product p where p.name like :name")
    List<Product> findByName(@Param("name") String name);

}
