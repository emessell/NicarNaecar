package com.prj.nicarnaecar.vo;

import java.util.Collection;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Entity
@Data
public class securityLoginVO extends User{
	
	private String username;
	private String password;
	private String name;
	private String birth;
	private String phone;
	
	private static final long serialVersionUID = 1L;

	public securityLoginVO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.username = username;
		this.password = password;
	}
	
}
