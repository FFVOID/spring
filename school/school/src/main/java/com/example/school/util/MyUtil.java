package com.example.school.util;

import org.springframework.stereotype.Service;

@Service
public class MyUtil {
	
	public int getPageCount(int numPerPage, int dataCount) {
		
		int pageCount = 0; 
		pageCount = dataCount / numPerPage;
		
		if(dataCount % numPerPage != 0) {
			pageCount++;
		}
		
		return pageCount;
	}
	
	public String pageIndexList(int currentPage, int totalPage, String listUrl) {
		StringBuffer sb = new StringBuffer();
		int numPerBlock = 5;
		int currentPageSetup;
		int page;
		
		if(currentPage == 0 || totalPage == 0) {
			return "";
		}
		
		if(listUrl.indexOf("?") != -1) {
			listUrl += "&";
		} else {
			listUrl += "?";
		}
		
		currentPageSetup = (currentPage / numPerBlock) * numPerBlock;
		
		if(currentPage % numPerBlock == 0) {
			currentPageSetup = currentPageSetup - numPerBlock;
		}
		
		if(totalPage > numPerBlock && currentPageSetup > 0) {
			sb.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + listUrl + "pageNum=" + currentPageSetup + "\">◀</a></li>");
		}
		
		page = currentPageSetup + 1;
		
		while(page <= totalPage && page <= (currentPageSetup + numPerBlock)) {
			
			if(page == currentPage) {
				sb.append("<li class=\"page-item\" colspan=\"5\"><a class=\"page-link active\" href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a></li>");
			} else {
				sb.append("<li class=\"page-item\" colspan=\"5\"><a class=\"page-link\" href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a></li>");
			}
			
			page++;
		}
		
		if(totalPage - currentPageSetup > numPerBlock) {
			sb.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + listUrl + "pageNum=" + page + "\">▶</a></li>");
		}
		
		return sb.toString();
	}
}
