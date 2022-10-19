package com.ko.home.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ko.home.util.Pager;

@Service
public class QnaService {

	@Autowired
	private QnaMapper qnaMapper;
	
	public List<QnaVO> getList(Pager pager)throws Exception{
		pager.getRowNum();
		return qnaMapper.getList(pager);
	}
	
	public int setAdd(QnaVO qnaVO)throws Exception{
		return qnaMapper.setAdd(qnaVO);
	}
	
}
