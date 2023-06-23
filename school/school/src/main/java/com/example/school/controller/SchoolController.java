package com.example.school.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.school.dto.School;
import com.example.school.service.SchoolService;
import com.example.school.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SchoolController {
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	MyUtil myUtil;
	
	@RequestMapping(value = "/")
	public String index(School school, HttpServletRequest request, Model model) {
		return list(school, request, model);
	}
	
	@RequestMapping(value = "/created" , method = RequestMethod.GET)
	public String created() {
		return "bbs/created";
	}
	
	//등록
	@RequestMapping(value = "/created" , method = RequestMethod.POST)
	public String createdOK(School school, HttpServletRequest request, Model model ) {
		
		
		try {
			int maxId = schoolService.maxId();
			
			school.setId(maxId + 1);
			
			schoolService.insertData(school);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage" , "학생정보 등록 중 에러가 발생했습니다");
			return "bbs/created";
		}
		
		return "redirect:/list";
	}
	
	//리스트
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(School school, HttpServletRequest request, Model model) {
		
		try {
			
			String pageNum = request.getParameter("pageNum");
			int currentPage = 1;
			
			if(pageNum != null) {
				currentPage = Integer.parseInt(pageNum);
			}
			
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");
			
			if(searchValue == null) {
				searchKey = "name";
				searchValue = "";
			} else {
				if(request.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			}
			
			int dataCount = schoolService.getDataCount(searchKey, searchValue);
			
			int numPerPage = 5;
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);
			
			if(currentPage > totalPage) {
				currentPage = totalPage;
			}
			
			int start = (currentPage - 1) * numPerPage + 1;
			int end = currentPage * numPerPage;
			 
			List<School> lists = schoolService.getLists(searchKey, searchValue, start, end);
			
			String param = "";
			
			if(searchValue != null && !searchValue.equals("")) {
				param = "searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}
			
			String listUrl = "/list";
			
			if(!param.equals("")) {
				listUrl += "?" + param;
			}
			
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
			
			String articleUrl = "/article?pageNum=" + currentPage;
			
			if(!param.equals("")) {
				articleUrl += "&" + param;
			}
			
			model.addAttribute("lists", lists);
			model.addAttribute("articleUrl" , articleUrl);
			model.addAttribute("pageIndexList" , pageIndexList);
			model.addAttribute("dataCount" , dataCount);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage" , "리스트를 불러오는 중 에러가 발생");
		}
		
		return "bbs/list"; 
	}
	
	//학생 상세정보
	@RequestMapping(value = "/article" , method = RequestMethod.GET)
	public String article(HttpServletRequest request, Model model) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String pageNum = request.getParameter("pageNum");
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");
			
			if(searchValue != null) {
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}
			
			School school = schoolService.getReadData(id);
			
			if(school == null) {
				return "redirect:/list?pageNum=" + pageNum;
			}
			
			String param = "pageNum=" + pageNum;
			
			if(searchValue != null && !searchValue.equals("")) {
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue,"UTF-8");
			}
			
			model.addAttribute("school", school);
			model.addAttribute("params", param);
			model.addAttribute("pageNum", pageNum);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "학생정보 불러오는 중 에러발생");
		}
		
		return "bbs/article";
	}
	
	//수정
	@RequestMapping(value = "/updated", method = RequestMethod.GET)
	public String updated(HttpServletRequest request, Model model) {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String pageNum = request.getParameter("pageNum");
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");
			
			if(searchValue != null) {
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}
			
			School school = schoolService.getReadData(id);
			
			if(school == null) {
				return "redirect:/list?pageNum=" + pageNum;
			}
			
			String param = "pageNum=" + pageNum;
			
			if(searchValue != null && !searchValue.equals("")) {
				param += "&searchKey" + searchKey;
				param += "&searchValue" + URLEncoder.encode(searchValue,"UTF-8");
			}
			
			model.addAttribute("school", school);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("params", param);
			model.addAttribute("searchKey", searchKey);
			model.addAttribute("searchValue", searchValue);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "bbs/updated";
	}
	
	//학생정보수정
	@RequestMapping(value = "/updated_ok" , method = RequestMethod.POST)
	public String updatedOK(School school, HttpServletRequest request, Model model) {
		String pageNum = request.getParameter("pageNum");
		String searchKey = request.getParameter("pageNum");
		String searchValue = request.getParameter("searchValue");
		String param = "?pageNum=" + pageNum;
		
		try {
			schoolService.updateData(school);
			
			if(searchValue != null && !searchValue.equals("")) {
				param += "&searchKey=" + searchKey;
				param += "&searchValue=" + URLEncoder.encode(searchValue,"UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				param += "&errorMessage=" + URLEncoder.encode("학생 정보 수정 중 에러발생","UTF-8");
			} catch (Exception e2) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/list" + param;
	}
	
	
	@RequestMapping(value = "/deleted_ok" , method= RequestMethod.GET)
	public String deleteOK(HttpServletRequest request, Model model) {
		String message = "";
		
		int id = Integer.parseInt(request.getParameter("id"));
		String pageNum = request.getParameter("pageNum");
		String searchkey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		String param = "?pageNum=" + pageNum;
		
		try {
			schoolService.deleteData(id);
			
			if(searchValue != null && !searchValue.equals("")) {
				
				
				param += "&searchkey=" + searchkey;
				param += "&searchValue=" + URLEncoder.encode(searchValue,"UTF-8");
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				param += "&errorMessage=" + URLEncoder.encode("학생 삭제 중 에러발생","UTF-8");
			} catch (Exception e2) {
			
			}
		}
		return "redirect:/list" + param;
	}
	
}
