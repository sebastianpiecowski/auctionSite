package com.zai.projekt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zai.projekt.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity findByEmail(String email);
	UserEntity findById(int id);
}
