package com.example.mapping.repository;

import com.example.mapping.dto.UserRequestDto;
import com.example.mapping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from users u inner join user_projects up on u.id=up.user_id where up.project_id = :projectId",nativeQuery = true)
    List<User> getUserByProjectId(@Param("projectId") Long id);


    List<User> findByProjects_ProjectName(String name);


    Optional<User> findByPhoneNo(String phoneNo);
}
