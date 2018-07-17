package com.prj.nicarnaecar.vo;


import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UploadFile {			//File 처리용 dto
	private MultipartFile file;
}
