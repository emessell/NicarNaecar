package com.prj.nicarnaecar.vo;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginVO {
	
	@Pattern(regexp="^[\\w=\\.]+@([\\w-]+\\.)+[\\w-]{2,4}",message="이메일 형식이 아닙니다.")
	private String cemail;
	
	@Size(min=4,max=30,message="비밀번호는 4-30byte로 입력해주세요.")
	private String cpasswd;
}
