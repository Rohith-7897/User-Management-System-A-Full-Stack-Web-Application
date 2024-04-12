package org.jsp.UserFullStack.repository;

import org.jsp.UserFullStack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
