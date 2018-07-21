package com.prompt.marginplus.repositories;

import com.prompt.marginplus.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("userRepo")
public interface UserRepository extends CrudRepository<User, String>{
	@Query("select u from User u where u.username=:email")
	public User checkUserExistenceByEmail(@Param("email") String email);
	
	@Query("select u from User u where u.contactnumber=:contactnumber")
	public User checkUserExistenceByContactNumber(@Param("contactnumber") String contactnumber);
	
	@Query("select u from User u where u.username=:username")
	public User getUser(@Param("username") String username);
}
