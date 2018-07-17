package com.prj.nicarnaecar.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeVO {
int nnumber;
String eemail, ntitle, ncontent;
Date ncdate;
}
