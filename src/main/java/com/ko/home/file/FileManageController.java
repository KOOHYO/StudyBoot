package com.ko.home.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ko.home.board.qna.QnaFileVO;
import com.ko.home.board.qna.QnaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileManageController {

	@Autowired
	private QnaService qnaService;
	
	@GetMapping("/fileDown/{p}") //RestFul, RestAPI
	public ModelAndView fileDown(@PathVariable(name = "p") String path, QnaFileVO qnaFileVO)throws Exception{
		log.info("path {} "+path);
		
		ModelAndView mv = new ModelAndView();
		
		if(path.equals("qna")) {
			qnaFileVO = qnaService.getFileDetail(qnaFileVO);
		}else if(path.equals("notice")) {
			//DB에서 정보 조회
			qnaFileVO.setFileName("cbc23d14-2560-4b6f-879f-100c1a2649ed_.jpg");
			qnaFileVO.setOriName("고양이.jpg");
		}
		
		mv.addObject("fileVO", qnaFileVO);
		mv.addObject("path", path);
		//FileManager 클래스를 찾아간다
		mv.setViewName("fileManager");
		
		return mv;
	}
	
}
