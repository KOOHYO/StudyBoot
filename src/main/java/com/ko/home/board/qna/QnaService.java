package com.ko.home.board.qna;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ko.home.util.FileManager;
import com.ko.home.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
//로그객체를 생성하지않고 바로 로그를 찍을 수 있다.
@Slf4j
public class QnaService {

	@Autowired
	private QnaMapper qnaMapper;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.qna}")
	private String path;
	
	public boolean setSummerFileDelete(String fileName)throws Exception{
		fileName = fileName.substring(fileName.lastIndexOf("/")+1);
		log.info("fileName => {} ", fileName);
		File file = new File(path, fileName);
		return file.delete();
	}
	
	public String setSummerFile(MultipartFile files)throws Exception{
		
		log.info("Path => {} ",path);
		String fileName = fileManager.saveFile(files, path);
		log.info("Path => {} ",path);
		fileName = "/file/qna/"+fileName;
		return fileName;
	}
	
	public int setFileDelete(QnaFileVO qnaFileVO)throws Exception{
		qnaFileVO = qnaMapper.getFileDetail(qnaFileVO);
		int result = qnaMapper.setFileDelete(qnaFileVO);
		
		if(result>0) {
			File file = new File(path, qnaFileVO.getFileName());
			file.delete();
		}
		
		return result;
	}
	
	public int setUpdate(QnaVO qnaVO)throws Exception{
		return qnaMapper.setUpdate(qnaVO);
	}
	
	//예외가 발생하면 롤백을 시켜라
	@Transactional(rollbackFor = Exception.class)
	public int setAdd(QnaVO qnaVO)throws Exception{
		
		int result = qnaMapper.setAdd(qnaVO);
		
		File file = new File(path);
		
		if(!file.exists()) {
			boolean check =  file.mkdirs();
		}
		
		
		for(MultipartFile f : qnaVO.getFiles()) {

			if(!f.isEmpty()) {
				log.info("FileName {} ", f.getOriginalFilename());
				String fileName = fileManager.saveFile(f, path);
				QnaFileVO qnaFileVO = new QnaFileVO();
				qnaFileVO.setNum(qnaVO.getNum());
				qnaFileVO.setFileName(fileName);
				qnaFileVO.setOriName(f.getOriginalFilename());
				qnaFileVO.setNum(qnaVO.getNum());
				qnaMapper.setFileAdd(qnaFileVO);
				
			}
		}
		return result;
	}
	
	public List<QnaVO> getList(Pager pager)throws Exception{
		pager.getRowNum();
		return qnaMapper.getList(pager);
	}
	
	public QnaVO getDetail(QnaVO qnaVO)throws Exception{
		return qnaMapper.getDetail(qnaVO);
	}
	
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO)throws Exception{
		return qnaMapper.getFileDetail(qnaFileVO);
	}
	
}
