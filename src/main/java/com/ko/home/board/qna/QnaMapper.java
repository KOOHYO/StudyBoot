package com.ko.home.board.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ko.home.util.Pager;

//@Repository 생략 가능
@Mapper
public interface QnaMapper {

	public List<QnaVO> getList(Pager pager)throws Exception;
	
	public int setAdd(QnaVO qnaVO)throws Exception;
	
	public int setFileAdd(QnaFileVO qnaFileVO)throws Exception;
	
	public QnaVO getDetail(QnaVO qnaVO)throws Exception;
	
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO)throws Exception;
	
}