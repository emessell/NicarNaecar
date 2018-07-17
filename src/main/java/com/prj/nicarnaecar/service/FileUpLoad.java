package com.prj.nicarnaecar.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prj.nicarnaecar.vo.UploadFile;


@Service
public class FileUpLoad {
	public boolean upload(UploadFile uploadFile, HttpServletRequest request, String vmodel){
		boolean isUpload = false;
		
		HttpSession session = request.getSession();
		
		// 웹어플리케이션 root정보 알기
		String rootPath = session.getServletContext().getRealPath("/");
		String attachPath = "resources\\IMG\\car\\";
		
		// 메모리상의 임시파일을 읽어옴
		MultipartFile uploadfile = uploadFile.getFile();
		
		if(uploadfile != null ) {
			// 파일의 오리지널이름을 얻어온 다음 뒤에 생성시간을 붙이면 유니크해짐,,!
			
			// 새 파일 생성~
			File file = new File(rootPath+attachPath+vmodel+".png");
			System.out.println(rootPath+attachPath);
			try {
				// 임시파일을 서버에 저장
				uploadfile.transferTo(file);
				isUpload = true;
			} catch (IOException e) {
				isUpload = false;
				e.printStackTrace();
			}
		}
		return isUpload;
	}
}
