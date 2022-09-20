package com.rest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	
	@Column(name = "full_name", nullable = false)
	@NotEmpty
	@Size(min = 2,max=50, message = "user name should have at least 2 characters")
	String fullName;
	@Column(name = "birthday", nullable = false)
	Date birthDay;
	@Column(name = "class_name", nullable = false)
	@NotEmpty
	String className;
	@Column(name = "major", nullable = false)
	String major;
	@Column(name = "home_town", nullable = false)
	String hometown;
	@Column(name = "gender", nullable = false)
	int gender;
	@Column(name = "mark", nullable = false)
	Float mark;
}
