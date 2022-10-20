package com.ko.home.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ko.home.board.qna.QnaFileVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileManageController {

	@GetMapping("/fileDown/{path}") //RestFul, RestAPI
	public ModelAndView fileDown(@PathVariable String path, QnaFileVO qnaFileVO)throws Exception{
		log.info("path {} "+path);
		
		ModelAndView mv = new ModelAndView();
		
		//DB에서 정보 조회
		qnaFileVO.setFileName("cbc23d14-2560-4b6f-879f-100c1a2649ed_.jpg");
		qnaFileVO.setOriName("고양이.jpg");
		
		mv.addObject("fileVO", qnaFileVO);
		mv.addObject("path", path);
		mv.setViewName("fileManager");
		
		return mv;
	}
	
//	@GetMapping("/fileDown/{path}")
//	public ModelAndView fileDownNotice(QnaFileVO qnaFileVO)throws Exception{
//		
//		ModelAndView mv = new ModelAndView();
//		
//		//DB에서 정보 조회
//		qnaFileVO.setFileName("cat3.jpg");
//		qnaFileVO.setOriName("cat3.jpg");
//		
//		mv.addObject("fileVO", qnaFileVO);
//		mv.addObject("path", "notice");
//		mv.setViewName("fileManager");
//		
//		return mv;
//	}
	
}
