package com.medpatientrecord.repository;

import com.medpatientrecord.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<UserInfo, Integer>  {
    UserInfo findByUsernameAndPassword(String username, String password);
}
