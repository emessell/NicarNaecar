package com.prj.nicarnaecar.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class FaqVO {
int fnumber;
String eemail, ftitle, fcontent;
Date fcdate;
}
