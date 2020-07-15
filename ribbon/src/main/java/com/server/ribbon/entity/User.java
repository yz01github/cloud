package com.server.ribbon.entity;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
	
	private Long id;
	
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
