package com.javadeveloper.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javadeveloper.user_service.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
 Users findByUserName(String userName);
}
