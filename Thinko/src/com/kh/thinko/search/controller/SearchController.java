package com.kh.thinko.search.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kh.thinko.board.model.vo.PageInfo2;
import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.search.model.service.SearchService;

@WebServlet("/search.do")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchKey = request.getParameter("sk");
		String searchValue = request.getParameter("sv");
		String cp = request.getParameter("cp");
		int type = 0;
		if(request.getParameter("type") != null) {
			if(!request.getParameter("type").equals("")) {
				type = Integer.parseInt(request.getParameter("type"));
			}
		}
		
		try {
		SearchService service = new SearchService();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		map.put("currentPage", cp);
		map.put("type", type);
		
		PageInfo2 pInfo = service.getPageInfo(map);
		
		List<VBoard> bList = service.searchBoardList(map, pInfo);
		
		String path = "/WEB-INF/views/board/boardList.jsp";
        
        request.setAttribute("bList", bList);
        request.setAttribute("pInfo", pInfo);
        
        RequestDispatcher view = request.getRequestDispatcher(path);
        view.forward(request, response);
		
		
		}catch (Exception e) {
	         e.printStackTrace();
	         String path = "/WEB-INF/views/common/errorPage.jsp";
	         request.setAttribute("errorMsg", "검색 과정에서 오류 발생");
	         RequestDispatcher view = request.getRequestDispatcher(path);
	         view.forward(request, response);
	      }
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
