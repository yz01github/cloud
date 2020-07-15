package com.provider.user.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
	
	private Long id;
	
	private String name;
	
	private List<User> sub;
	
	private String parentType;

	/**
	 * 创建时间
	 */
	private Date date;
	
	private String type;

	public User(Long id) {
		this.id = id;
		this.type = "user";
		this.date = new Date();
	}
	
	
}
