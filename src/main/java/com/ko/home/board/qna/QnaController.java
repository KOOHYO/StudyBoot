package com.ko.home.board.qna;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ko.home.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QnaService qnaService;
	
	@ResponseBody
	@PostMapping("fileDelete")
	public int setFileDelete(QnaFileVO qnaFileVO)throws Exception{
		int result = qnaService.setFileDelete(qnaFileVO);
		return result;
	}
	
	@GetMapping("update")
	public ModelAndView setUpdate(QnaVO qnaVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.getDetail(qnaVO);
//		int result = qnaService.setUpdate(qnaVO);
//		String message = "❗글 수정을 실패하였습니다..❗";
//		String url = "./update";
//		if(result>0) {
//			message = "🎉글 수정을 성공했습니다!!🎉";
//			url = "../";
//		}
//		mv.addObject("message", message);
//		mv.addObject("url", url);
		mv.addObject("vo", qnaVO);
		mv.setViewName("board/update");
		
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getDetail(QnaVO qnaVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		QnaFileVO qnaFileVO = new QnaFileVO();
		qnaVO = qnaService.getDetail(qnaVO);
//		for(QnaFileVO f :qnaVO.getQnaFileVOs()) {
//			qnaFileVO.setFileName(f.getFileName());
//			qnaFileVO.setFileNum();
//		}
		mv.addObject("vo", qnaVO);
		mv.setViewName("board/detail");
		return mv;
	}
	
	@PostMapping("add")
	public String setAdd(QnaVO qnaVO, RedirectAttributes redirectAttributes)throws Exception {
		int result = qnaService.setAdd(qnaVO);
		redirectAttributes.addAttribute("result", result);
		return "redirect:./list";
	}
	
	@GetMapping("add")
	public String setAdd()throws Exception{
		return "board/write";
	}
	
	@GetMapping("list")
	public ModelAndView getList(Pager pager)throws Exception{
		log.info("List접속");
		ModelAndView mv = new ModelAndView();
		List<QnaVO> ar = qnaService.getList(pager);
		
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		mv.setViewName("board/list");
		
		return mv;
	}
	
}
