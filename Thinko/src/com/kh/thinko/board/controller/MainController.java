package com.kh.thinko.board.controller;

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

import com.google.gson.Gson;
import com.kh.thinko.board.model.service.MainService;
import com.kh.thinko.board.model.vo.VBoard;

@WebServlet("/main/*")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/main").length());

		String path = null; 
		RequestDispatcher view = null;
		
		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;
		
		String errorMsg = null;
		
		try {
			
			MainService service = new MainService();
			
			if(command.equals("/list.do")) {
				errorMsg = "게시판 조회 과정에서 오류 발생";
				
				int type = 10;
				List<VBoard> bList1 = service.BoardList(type);
				
				type = 20;
				List<VBoard> bList2 = service.BoardList(type);
				
				type = 30;
				List<VBoard> bList3 = service.BoardList(type);
				
				type = 40;
				List<VBoard> bList4 = service.BoardList(type);
				
				type = 50;
				List<VBoard> bList5 = service.BoardList(type);
				
				type = 60;
				List<VBoard> bList6 = service.BoardList(type);
				
				Map<String , Object> map = new HashMap<>();
				
				map.put("bList1", bList1);
				map.put("bList2", bList2);
				map.put("bList3", bList3);
				map.put("bList4", bList4);
				map.put("bList5", bList5);
				map.put("bList6", bList6);
				
				request.setAttribute("map", map);
				
				Gson gson = new Gson();
				gson.toJson(map,response.getWriter());			
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errorMsg", errorMsg);
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
