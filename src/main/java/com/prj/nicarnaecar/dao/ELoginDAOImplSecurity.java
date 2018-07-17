package com.prj.nicarnaecar.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.prj.nicarnaecar.vo.ESecurityLoginVO;
import com.prj.nicarnaecar.vo.EmployeeVO;

@Repository
public class ELoginDAOImplSecurity implements UserDetailsService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeVO employeeVO = null;
		StringBuffer str = new StringBuffer();
		
		str.append("select eemail, epassword, ephone, ebirth, ename, eposition, eoffice, estate"
				+ " from employee where eemail=?");
		
		try {
			employeeVO = this.jdbcTemplate.queryForObject(str.toString(), new Object[] {username}, 
					new BeanPropertyRowMapper<>(EmployeeVO.class));
		}catch(DataAccessException e) {
			employeeVO = new EmployeeVO();
		}
		if(employeeVO == null) {
			throw new UsernameNotFoundException("사용자가 존재하지 않습니다.");
		}
		
		//권한정보 가져오기
		List<String> auth = new ArrayList<>();
		StringBuffer str2 = new StringBuffer();
		str2.append("select role from user_role where email=?");
		auth = this.jdbcTemplate.queryForList(str2.toString(), new Object[] {username}, String.class);
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		
		for(String role : auth) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		
		ESecurityLoginVO loginVO = new ESecurityLoginVO(employeeVO.getEemail(),employeeVO.getEpassword(), authorities);
		
		loginVO.setName(employeeVO.getEname());
		loginVO.setBirth(employeeVO.getEbirth());
		loginVO.setAddress(employeeVO.getEaddress());
		loginVO.setOffice(employeeVO.getEoffice());
		loginVO.setPhone(employeeVO.getEphone());
		loginVO.setState(employeeVO.getEstate());
		
		
		return loginVO;
	}

}
