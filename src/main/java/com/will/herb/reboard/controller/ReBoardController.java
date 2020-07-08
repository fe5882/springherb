package com.will.herb.reboard.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.will.herb.common.FileUploadUtil;
import com.will.herb.common.PaginationInfo;
import com.will.herb.common.SearchVO;
import com.will.herb.common.Utility;
import com.will.herb.reboard.model.ReBoardService;
import com.will.herb.reboard.model.ReBoardVO;

@Controller
public class ReBoardController {
	private static final Logger logger
	=LoggerFactory.getLogger(ReBoardController.class);

	@Autowired private ReBoardService reBoardService;
	@Autowired
	private FileUploadUtil fileUploadUtil;
	
	@RequestMapping(value="/reBoard/write.do", method = RequestMethod.GET)
	public String write_get() {
		logger.info("글쓰기 화면");

		return "reBoard/write";
	}

	@RequestMapping(value="/reBoard/write.do", method=RequestMethod.POST)
	public String write_post(@ModelAttribute ReBoardVO reBoardVo, 
			HttpServletRequest request,
			Model model) {
		//1
		logger.info("글 등록, 파라미터 vo={}", reBoardVo);
		
		//파일 업로드 처리
		List<Map<String, Object>> list = fileUploadUtil.fileUpload(request);
		
		String fileName = "", originalFileName = "";
		long fileSize = 0;
		
		for(Map<String, Object> map : list) {
			fileSize = (Long) map.get("fileSize");
			fileName = (String)map.get("fileName");
			originalFileName = (String)map.get("originalFName");
		}
		reBoardVo.setFileName(fileName);
		reBoardVo.setFileSize(fileSize);
		reBoardVo.setOriginalFileName(originalFileName);
		
		//2
		int cnt=reBoardService.insertReBoard(reBoardVo);
		logger.info("글 등록 결과, cnt={}", cnt);

		String msg="글등록 실패", url="/reBoard/write.do";
		if(cnt>0) {
			msg="글 등록되었습니다.";
			url="/reBoard/list.do";
		}

		//3
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		//4
		return "common/message";
	}
	
	@RequestMapping("/reBoard/list.do")
	public String list(@ModelAttribute SearchVO searchVo, Model model) {
		//1
		logger.info("글 목록 파라미터 searchVo={}", searchVo);
		
		//[1] PaginationInfo 생성
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(Utility.BLOCKSIZE);
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		
		//[2] SearchVo 에 값 셋팅
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		searchVo.setRecordCountPerPage(Utility.RECORD_COUNT);
		
		//2
		List<ReBoardVO> list=reBoardService.selectAll(searchVo);
		logger.info("글목록 결과, list.size="+list.size());

		int totalRecord=reBoardService.selectTotalRecord(searchVo);
		logger.info("글목록, 전체 레코드 개수 : " + totalRecord);
		
		pagingInfo.setTotalRecord(totalRecord);
		
		//3
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
		
		//4
		return "reBoard/list";
	}
	
	@RequestMapping("/reBoard/countUpdate.do")
	public String countUpdate(@RequestParam(defaultValue = "0") int no,
			Model model) {
		//1
		logger.info("조회수 증가, 파라미터 no={}", no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url 입니다.");
			model.addAttribute("url", "/reBoard/list.do");
			
			return "common/message";
		}
		
		//2
		int cnt=reBoardService.updateReadCount(no);
		logger.info("조회수 증가 결과, cnt={}", cnt);
		
		//3		
		//4
		return "redirect:/reBoard/detail.do?no="+no;
	}
	
	@RequestMapping("/reBoard/detail.do")
	public String detail(@RequestParam(defaultValue = "0") int no,
			Model model, HttpServletRequest request) {
		//1
		logger.info("상세보기, 파라미터 no={}", no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url 입니다.");
			model.addAttribute("url", "/reBoard/list.do");
			
			return "common/message";
		}
		
		//2
		ReBoardVO vo=reBoardService.selectByNo(no);
		logger.info("상세보기 조회 결과, vo={}", vo);
		
		
		String fileInfo = "", downInfo = "";
		if(vo.getOriginalFileName() != null && !vo.getOriginalFileName().isEmpty()) {
			float fileSize = Math.round(vo.getFileSize()/1024f*10)/10f;
			fileInfo = Utility.getFileInfo(vo.getOriginalFileName(), request)
				+ "("+fileSize + " KB)";
			downInfo = "다운 : " + vo.getDownCount();
		}
		//3		
		model.addAttribute("vo", vo);
		model.addAttribute("fileInfo", fileInfo);
		model.addAttribute("downInfo", downInfo);
		
		
		
		
		//4
		return "reBoard/detail";
	}
	
	@RequestMapping(value="/reBoard/edit.do", method=RequestMethod.GET)
	public String edit_get(@RequestParam(defaultValue = "0") int no, HttpServletRequest request,
			Model model) {
		//1
		logger.info("수정화면, 파라미터  no={}", no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/reBoard/list.do");
			
			return "common/message";
		}
		
		//2
		ReBoardVO vo=reBoardService.selectByNo(no);
		logger.info("수정화면 조회 결과, vo={}", vo);
		
		//3
		if(vo.getOriginalFileName() != null && !vo.getOriginalFileName().isEmpty()) {
			String fileInfo = Utility.getFileInfo(vo.getOriginalFileName(), request);
			model.addAttribute("fileInfo", fileInfo);
		}
		model.addAttribute("vo", vo);
		
		//4
		return "reBoard/edit";
	}
	
	@RequestMapping(value="/reBoard/edit.do", method=RequestMethod.POST)
	public String edit_post(@ModelAttribute ReBoardVO reBoardVo, @RequestParam String oldFileName, HttpServletRequest request,
			Model model) {
		//1
		logger.info("수정처리 파라미터 vo={}", reBoardVo);
		//파일 업로드 처리
				List<Map<String, Object>> list = fileUploadUtil.fileUpload(request);
				
				String fileName = "", originalFileName = "";
				long fileSize = 0;
				if(list != null && !list.isEmpty()) {
				
				
				for(Map<String, Object> map : list) {
					fileSize = (Long) map.get("fileSize");
					fileName = (String)map.get("fileName");
					originalFileName = (String)map.get("originalFName");
				}
				reBoardVo.setFileName(fileName);
				reBoardVo.setFileSize(fileSize);
				reBoardVo.setOriginalFileName(originalFileName);
				}
		//2
		String msg="글수정 실패", url="/reBoard/edit.do?no="+reBoardVo.getNo()+"&oldFileName="+oldFileName;
		if(reBoardService.checkPwd(reBoardVo.getNo(), reBoardVo.getPwd())) {
			int cnt=reBoardService.updateReBoard(reBoardVo);
			if(cnt>0) {
				if(oldFileName != null && !oldFileName.isEmpty()) {
					String upPath = fileUploadUtil.getUploadPath(request);
					File file = new File(upPath, oldFileName);
					if(file.exists()) {
						boolean bool=file.delete();
						logger.info("파일 삭제 여부: {}", bool);
					}
				}
				
				msg="글수정 처리되었습니다";
				url="/reBoard/detail.do?no="+reBoardVo.getNo();
			}
		}else {
			msg="비밀번호가 일치하지 않습니다.";
		}
				
		//3
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		//4
		return "common/message";
	}
	
	@RequestMapping(value="/reBoard/delete.do",method = RequestMethod.GET)
	public String delete_get(@RequestParam(defaultValue="0") int no,
			ModelMap model) {
		//1
		logger.info("삭제 화면, 파라미터 no={}", no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/reBoard/list.do");
			
			return "common/message";
		}
		
		//2
		//3
		
		//4
		return "reBoard/delete";
	}
	
	@RequestMapping(value="/reBoard/delete.do",method=RequestMethod.POST)
	public String delete_post(@ModelAttribute ReBoardVO reBoardVo, HttpServletRequest request,
			Model model) {
		//1
		logger.info("삭제처리 파라미터 vo={}", reBoardVo);
		
		//2
		String msg="글삭제 실패", url="/reBoard/delete.do?no="+reBoardVo.getNo()+"&fileName="+reBoardVo.getFileName()+"&groupNo="
									+reBoardVo.getGroupNo()+"&step="+reBoardVo.getStep();
		
		if(reBoardService.checkPwd(reBoardVo.getNo(), reBoardVo.getPwd())) {
			//삭제시 프로시저 사용
			Map<String, String> map = new HashMap<String, String>();
			map.put("no", reBoardVo.getNo()+"");
			map.put("step", reBoardVo.getStep()+"");
			map.put("groupNo", reBoardVo.getGroupNo()+"");


			reBoardService.deleteReBoard(map);


			//파일이 첨부된 경우 파일 삭제 처리
			String oldFileName = reBoardVo.getFileName();
			if(oldFileName != null && !oldFileName.isEmpty()) {
				String upPath = fileUploadUtil.getUploadPath(request);
				File file = new File(upPath, oldFileName);
				if(file.exists()) {
					boolean bool=file.delete();
					logger.info("파일 삭제 여부: {}", bool);
				}

			
			}
				msg="글삭제 처리되었습니다";
				url="/reBoard/list.do";



		}else {
			msg="비밀번호가 일치하지 않습니다.";
		}

		//3
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		//4
		return "common/message";
	}
	
	@RequestMapping("/reBoard/download.do")
	public ModelAndView download(@RequestParam(defaultValue = "0") int no, @RequestParam String fileName
			, HttpServletRequest request) {
		//1.
		logger.info("다운로드 파라미터 no={}, fileName={}", no, fileName);
		//2.
		int cnt = reBoardService.updateDownCount(no);
		
		//다운로드 처리를 위한 페이지로 넘겨준다
		String upPath = fileUploadUtil.getUploadPath(request);
		File file = new File(upPath, fileName);
		
		//3.
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("file", file);
		
		
		//4.
		//ModelAndView(String viewName, Map<String, ?> model)
		ModelAndView mav = new ModelAndView("reBoardDownloadView", map);
		return mav;
	}
	
	@RequestMapping(value = "/reBoard/reply.do", method = RequestMethod.GET)
	public String reply_get(@RequestParam(defaultValue = "0") int no, Model model) {
		logger.info("답변하기 파라미터, no={}", no);
		
		if(no == 0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/reBoard/list.do");
			
			return "common/message";
		}
		
		ReBoardVO vo=reBoardService.selectByNo(no);
		logger.info("답변하기 조회 결과 vo={}", vo);
		model.addAttribute("vo", vo);
		
		return "reBoard/reply";
		
	}
	
	@RequestMapping(value = "/reBoard/reply.do", method = RequestMethod.POST)
	public String reply_post(@ModelAttribute ReBoardVO vo, Model model) {
		logger.info("답변처리 파라미터 vo={}", vo);
		
		int cnt = reBoardService.reply(vo);
		logger.info("답변처리 결과, cnt = {}", cnt);
		
		return "redirect:/reBoard/list.do";
		
		
	}
	
}












