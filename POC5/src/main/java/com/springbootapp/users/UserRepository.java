package com.springbootapp.users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends CrudRepository<User , Integer>{
	//Definiting Page for pagination
	Page<User> findAll(Pageable pageable);

}
