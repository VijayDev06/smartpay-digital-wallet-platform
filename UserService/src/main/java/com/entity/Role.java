package com.entity;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Enumerated(EnumType.STRING)
	@Column(unique = true, nullable = false)
	private RoleEnum rolename;
	
//	@OneToMany(mappedBy = "role")
//	//@ToString.Exclude  // To prevemt stack overflow
//	@JsonIgnore
//	private List<User> users; //@JsonIgnore --> prevents circular JSON
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@CreationTimestamp
	@Column(name = "created_At", nullable = false)
	private Instant createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_At", nullable = false)
	private Instant updatedAt;

}
