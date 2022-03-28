package com.buri.restfulservice.domain.user;

import com.buri.restfulservice.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
