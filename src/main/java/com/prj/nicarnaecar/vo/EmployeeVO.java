package com.prj.nicarnaecar.vo;

import java.sql.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EmployeeVO {
	@Pattern(regexp="^[\\w=\\.]+@([\\w-]+\\.)+[\\w-]{2,4}",message="이메일 형식이 아닙니다.")
	String eemail;
	
	@Size(min=4,max=20,message="비밀번호는 4-20byte로 입력해주세요.")
	String epassword;
	
	@Size(min=3,max=12,message="이름은 3-12byte로 입력해주세요.")
	String ename;
	
	@Size(min=8,max=8,message="생년월일은 '19990101' 형식으로 입력해주세요.")
	String ebirth;
	
	@Size(min=10,max=11,message="전화번호는 하이픈을 제외한 11자리 이내로 입력해주세요.")
	String ephone;
	
	String eaddress;
	Date edate;
	String eoffice;
	String eposition;
	String estate;

}
