package com.ko.home.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
//@Rollback(true)
@Transactional
class QnaMapperTest {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QnaMapper qnaMapper;
	
	private QnaVO qnaVO;
	
	//@BeforeAll
	static void beforeAll() {
		System.out.println("전체 Test 실행전 !!!!");
	}
	
	//@AfterAll
	static void afterAll() {
		System.out.println("전체 Test 실행 후 !!!!");
	}
	
	//@BeforeEach
	void beforeEach() {
		System.out.println("Test 메서드 실행 전");
		qnaVO = new QnaVO();
		qnaVO.setNum(1L);
		qnaVO.setContents("contents");
	}
	
	//@AfterEach
	void afterEach() {
		System.out.println("Test 메서드 실행 후");
	}
	
	@Test
	void test()throws Exception {
//		qnaMapper.setAdd(qnaVO);
//		qnaMapper.setDelete(qnaVO);
		QnaVO qnaVO = new QnaVO();
		qnaVO.setWriter("ㅎㅇ");
		qnaVO.setTitle("gd");
		qnaVO.setContents("gd");
		int result = qnaMapper.setAdd(qnaVO);
		assertEquals(1, result);
	}
	
	//@Test
	void getListTest()throws Exception {
		//List<QnaVO> ar = qnaMapper.getList();
//		log.info("List {} ", ar);
//		assertNotEquals(0, ar.size());
	}
	
	//@Test
	void setAddTest()throws Exception{
		for(int i=0; i<100; i++) {
			QnaVO qnaVO = new QnaVO();
			qnaVO.setWriter("hi"+i);
			qnaVO.setTitle("안녕하세요"+i);
			qnaVO.setContents("반갑습니다"+i);
			int result = qnaMapper.setAdd(qnaVO);
			
			if(i%10==0) {
				Thread.sleep(500);
			}
		}
		System.out.println("for문 끝");
	}
	
	//@Test
	void getDetailTest()throws Exception{
		QnaVO qnaVO = new QnaVO();
		qnaVO.setNum(103L);
		qnaVO = qnaMapper.getDetail(qnaVO);
		assertNotNull(qnaVO);
	}

}
