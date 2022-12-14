package com.ko.home.board.qna;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
	
	@GetMapping("hack")
	@ResponseBody
	public int hack(QnaVO qnaVO)throws Exception{
		qnaService.setAdd(qnaVO);
		return 1;
	}
	
	@PostMapping("summerFileDelete")
	@ResponseBody
	public boolean setSummerFileDelete(String fileName)throws Exception{
		log.info("fileName => {} ",fileName);
		return qnaService.setSummerFileDelete(fileName);
	}
	
	@PostMapping("summerFile")
	@ResponseBody
	public String setSummerFile(MultipartFile files)throws Exception{
		log.info("Files => {} ", files);
		
		String result = qnaService.setSummerFile(files);
		
		return result;
	}
	
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
//		String message = "βκΈ μμ μ μ€ν¨νμμ΅λλ€..β";
//		String url = "./update";
//		if(result>0) {
//			message = "πκΈ μμ μ μ±κ³΅νμ΅λλ€!!π";
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
	
	@GetMapping("add")
	public String setAdd(@ModelAttribute QnaVO qnaVO)throws Exception{
		return "board/write";
	}
	
	@PostMapping("add")
	public ModelAndView setAdd(@Valid QnaVO qnaVO, BindingResult bindingResult)throws Exception {
		ModelAndView mv = new ModelAndView();
		if(bindingResult.hasErrors()) {
			log.info("------------------ κ²μ¦μλ¬ λ°μ ------------------");
			mv.setViewName("board/write");
			return mv;
		}
		int result = qnaService.setAdd(qnaVO);
		mv.setViewName("board/list");
		
		return mv;
	}
	
	@GetMapping("list")
	public ModelAndView getList(Pager pager)throws Exception{
		log.info("Listμ μ");
		ModelAndView mv = new ModelAndView();
		List<QnaVO> ar = qnaService.getList(pager);
		
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		mv.setViewName("board/list");
		
		return mv;
	}
	
}
