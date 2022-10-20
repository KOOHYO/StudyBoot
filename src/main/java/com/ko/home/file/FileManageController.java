package com.ko.home.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ko.home.board.qna.QnaFileVO;

@Controller
public class FileManageController {

	@GetMapping("/fileDown/qna")
	public ModelAndView fileDown(QnaFileVO qnaFileVO)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		//DB에서 정보 조회
		qnaFileVO.setFileName("cbc23d14-2560-4b6f-879f-100c1a2649ed_.jpg");
		qnaFileVO.setOriName("고양이.jpg");
		
		mv.addObject("fileVO", qnaFileVO);
		
		mv.setViewName("fileManager");
		
		return mv;
	}
	
}
