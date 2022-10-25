package com.ko.home.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.AbstractView;

import com.ko.home.board.qna.QnaFileVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileManager extends AbstractView {

	@Value("${app.download.base}")
	private String base;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		
		QnaFileVO qnaFileVO = (QnaFileVO) model.get("fileVO");
		String path = (String) model.get("path");
		log.info("=================================");
		log.info("FILEVO {} ", qnaFileVO);
		
		File file = new File(base+path, qnaFileVO.getFileName());
		
		//한글 처리 (인코딩처리)
		response.setCharacterEncoding("UTF-8");
		
		//총 파일의 크기
		//file.length()가 Long타입이기 때문에 처음부터 setContentLengthLong을 쓰는것이 좋다
		response.setContentLengthLong(file.length());
		
		//다운로드시 파일의 이름을 인코딩
		//java.net.URLEncoder
		String oriName = URLEncoder.encode(qnaFileVO.getOriName(), "UTF-8");
		
		//header 설정
		response.setHeader("Content-Disposition", "attachment;filename=\""+oriName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary"); //전송할때 인코딩 텍스트가 아니다 이진파일이다
		
		//HDD에서 파일을 읽고
		FileInputStream fi = new FileInputStream(file);
		//Client 로 전송 준비
		OutputStream os = response.getOutputStream();
		
		//전송 
		//MultipartFile 이 없어서 FileCopyUtils.copy를 써야함
		FileCopyUtils.copy(fi, os);
		
		//자원 해제
		os.close();
		fi.close();
		
	}
	
	public String saveFile(MultipartFile multipartFile, String path)throws Exception {
		
		//1. 중복되지 않는 파일명 생성 (UUID, Date)
		String fileName = UUID.randomUUID().toString();
		
		//2. 확장자
		StringBuffer bf = new StringBuffer();
		bf.append(fileName);
		bf.append("_");
		
		//파일명과 확장자 분리
		String ex = multipartFile.getOriginalFilename();//고양이.jpg
		ex = ex.substring(ex.lastIndexOf("."));
		
		//bf.append(multipartFile.getOriginalFilename());
		bf.append(ex);
		
		fileName = bf.toString();
		
		//3. File Save
		File file = new File(path, bf.toString());
		
		//FileCopyUtils
		//MultipartFile
		//FileCopyUtils.copy(multipartFile.getBytes(), file);
		multipartFile.transferTo(file);
		
		log.info("============================");
		log.info("fileName {} ", fileName);
		
		return fileName;
	}
	
}
