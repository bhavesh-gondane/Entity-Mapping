package com.example.mapping.repository;

import com.example.mapping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
//    @Query(value = "FROM products where user_id=:userId",nativeQuery = false)
//    List<Product> productsByUserId(@Param("userId") Integer id);

//    @Query(value = "select * from products p join users u on p.user_id=u.id where u.name=:name", nativeQuery = true)
//    List<Product> productsByName(@Param("name") String name);

    List<Product> findByUser_Name(String name);
}
