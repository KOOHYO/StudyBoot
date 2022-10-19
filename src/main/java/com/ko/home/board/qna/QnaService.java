package com.ko.home.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ko.home.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
//로그객체를 생성하지않고 바로 로그를 찍을 수 있다.
@Slf4j
public class QnaService {

	@Autowired
	private QnaMapper qnaMapper;
	
	public List<QnaVO> getList(Pager pager)throws Exception{
		pager.getRowNum();
		return qnaMapper.getList(pager);
	}
	
	public int setAdd(QnaVO qnaVO)throws Exception{
		for(MultipartFile f : qnaVO.getFiles()) {
			if(!f.isEmpty()) {
				log.info("FileName {} ", f.getOriginalFilename());
			}
		}
		return 1;//qnaMapper.setAdd(qnaVO);
	}
	
}
