package com.springbootapp.users;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import lombok.Data;
@Entity
@Table(name = "user_task")
@Data//Lombook Usage for default getter and setter
@Component
public class User {
	//Annotations and Validatons
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
@Column(name="first_name",nullable=false)
private String fname;
@Column(name="last_name",nullable=false)
private String lname;
@Column(name="contact",nullable=false,unique=true)
private String contact;
@Column(name="email",nullable=false,unique=true)
private String email;
@Column(name="city",nullable=false)
private String city;
@Column(name="country",nullable=false)
private String country;
}
