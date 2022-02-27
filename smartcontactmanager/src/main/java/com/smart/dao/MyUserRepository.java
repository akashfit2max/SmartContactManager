package com.smart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smart.entities.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Integer> {

//	get the user by email or login security
	@Query("select mu from MyUser mu where mu.email = : emial")
	public MyUser getMyUserByUserName(@Param("email") String email);

}
