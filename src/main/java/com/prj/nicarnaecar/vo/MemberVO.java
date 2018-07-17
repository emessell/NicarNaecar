package com.prj.nicarnaecar.vo;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MemberVO {
   
   @Pattern(regexp="^[\\w=\\.]+@([\\w-]+\\.)+[\\w-]{2,4}",message="이메일 형식이 아닙니다.")
   String cemail;      // 고객이메일
   
   @Size(min=4,max=20,message="비밀번호는 4-20byte로 입력해주세요.")
   String cpasswd;   // 고객비밀번호
   
   @Size(min=3,max=12,message="이름은 3-12byte로 입력해주세요.")
   String cname;      // 고객이름
   
   @Size(min=8,max=8,message="생년월일은 '19990101' 형식으로 입력해주세요.")
   String cbirth;
   
   @Size(min=10,max=11,message="전화번호는 하이픈을 제외한 11자리 이내로 입력해주세요.")
   String cphone;      // 고객전화번호
   Date cdate;         // 가입일
   Date cmdate;      // 최근방문일
}