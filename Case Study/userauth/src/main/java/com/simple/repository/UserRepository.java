package com.simple.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.simple.model.UserDao;
public interface UserRepository extends CrudRepository<UserDao, Integer> {
    UserDao findByUsername(String username);
    List<UserDao> findByRole(String role);

    @Query("select u from UserDao u where u.id =:id")
    UserDao findByid(@Param("id") Long id);
}