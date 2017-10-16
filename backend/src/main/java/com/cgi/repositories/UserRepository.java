package com.cgi.repositories;

import com.cgi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /*JpaRepository will have all the functions of CrudRepository and PagingAndSortingRepository. So if you don't need
     the repository to have the functions provided by JpaRepository and PagingAndSortingRepository , use CrudRepository.*/

    List<User> findByName(String name);

    @Query("select new com.cgi.entities.User(u.id, u.name, u.email) from User u where u.id = ?1")
    User findByIdNoDetail(Integer id);

}