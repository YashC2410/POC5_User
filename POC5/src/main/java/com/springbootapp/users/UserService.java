package com.springbootapp.users;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class UserService {//Service Class
@Autowired
UserRepository userRepository;
//Used to list all users in a particular page
public Page<User> listAll(int pageNum) {
    int pageSize = 5;
    Pageable pageable = PageRequest.of(pageNum - 1, pageSize);    
    return userRepository.findAll(pageable);
}
//Fetching and displaying the user
public List<User> getAllUsers(){
	List<User> user=(List<User>) userRepository.findAll();
	if(user.size()>0) {
		return user;
	}
	else {
		return new ArrayList<User>();
	}
}
//Fetchhing user based on ID
public User getUserById(Integer id) throws UserNotFoundException{
	Optional<User> user= userRepository.findById(id);
	if(user.isPresent()) {
		return user.get();
	}
	else {
		//if user is not available throws an exception
		throw new UserNotFoundException("User with ID "+id+" Not Found!!");
	}
	
}
//Creating or updating a particular user
public User createOrUpdateUser(User user) {
	if(user.getId()==null) {
		user=userRepository.save(user);
		return user;
	}
	else {
		Optional<User> user1= userRepository.findById(user.getId());
		if(user1.isPresent()) {
			User newUser=new User();
			newUser.setFname(user.getFname());
			newUser.setLname(user.getLname());
			newUser.setEmail(user.getEmail());
			newUser.setCity(user.getCity());
			newUser.setCountry(user.getContact());
			newUser.setContact(user.getContact());
			newUser=userRepository.save(user);
			return newUser;
		}
		else {
		    user=userRepository.save(user);
		    return user;
		}
	}
}
//Deleting a user based on ID
public void deleteUserById(Integer id) throws UserNotFoundException{
	Optional<User> user=userRepository.findById(id);
	if(user.isPresent()) {
		userRepository.deleteById(id);
	}
	else {
		throw new UserNotFoundException("User with ID "+id+" Not Found!!");
	}
}
//For Pagination , Sorting and keeping page size default as 5 per page
public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
	Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		Sort.by(sortField).descending();
	
	Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
	return userRepository.findAll(pageable);
}
}
