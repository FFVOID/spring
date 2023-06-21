package com.example.school.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		public String index() {
			return "list";
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
}
