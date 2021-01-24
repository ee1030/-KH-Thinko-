package com.kh.thinko.admin.controller;

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

import com.kh.thinko.admin.model.service.AdminSearchService;
import com.kh.thinko.board.model.vo.PageInfo;
import com.kh.thinko.board.model.vo.ReportBoard;
import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.member.model.vo.Member;
import com.kh.thinko.reply.model.vo.Reply;
import com.kh.thinko.reply.model.vo.VReportReply;

@WebServlet("/adminSearch/*")
public class AdminSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); 		
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/adminSearch").length());

		String path = null; // forward 또는 redirect 경로를 저장할 변수
		RequestDispatcher view = null; // 요청 위임 객체
		
		try {
			String searchKey = request.getParameter("sk");
			String searchValue = request.getParameter("sv");
			String cp = request.getParameter("cp");
			
			AdminSearchService service = new AdminSearchService();
			
			// 전체 게시글 검색 Controller *****************************************************************
			if(command.equals("/allBoardSearch.do")) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("searchKey", searchKey);
				map.put("searchValue", searchValue);
				map.put("currentPage", cp);
		
				PageInfo pInfo = service.getPageInfo(map);
				
				List<VBoard> bList = service.searchBoardList(map, pInfo);
				
				path = "/WEB-INF/views/admin/allBoardList.jsp";
		        
		        request.setAttribute("bList", bList);
		        request.setAttribute("pInfo", pInfo);
		        
		        view = request.getRequestDispatcher(path);
		        view.forward(request, response);
			}
			
			// 전체 댓글 검색 Controller *****************************************************************
			else if(command.equals("/allReplySearch.do")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("searchKey", searchKey);
				map.put("searchValue", searchValue);
				map.put("currentPage", cp);
				
				PageInfo pInfo = service.getReplyPageInfo(map);
				
				List<Reply> rList = service.searchReplyList(map, pInfo);
				
				path = "/WEB-INF/views/admin/allReplyList.jsp";
		        
		        request.setAttribute("rList", rList);
		        request.setAttribute("pInfo", pInfo);
		        
		        view = request.getRequestDispatcher(path);
		        view.forward(request, response);
			}
			
			// 신고된 게시글 검색 Controller ************************************************************
			else if(command.equals("/reportedBoardSearch.do")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("searchKey", searchKey);
				map.put("searchValue", searchValue);
				map.put("currentPage", cp);
				
				PageInfo pInfo = service.getReportBoardPageInfo(map);
				
				List<ReportBoard> rbList = service.searchReportBoardList(map, pInfo);
				
				path = "/WEB-INF/views/admin/reportedBoardList.jsp";
		        
		        request.setAttribute("rbList", rbList);
		        request.setAttribute("pInfo", pInfo);
		        
		        view = request.getRequestDispatcher(path);
		        view.forward(request, response);
			}
			
			// 신고된 댓글 검색 Controller **************************************************************
			else if(command.equals("/reportedReplySearch.do")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("searchKey", searchKey);
				map.put("searchValue", searchValue);
				map.put("currentPage", cp);
				
				PageInfo pInfo = service.getReportReplyPageInfo(map);
				
				List<VReportReply> rrList = service.searchReportReplyList(map, pInfo);
				
				path = "/WEB-INF/views/admin/reportedReplyList.jsp";
		        
		        request.setAttribute("rrList", rrList);
		        request.setAttribute("pInfo", pInfo);
		        
		        view = request.getRequestDispatcher(path);
		        view.forward(request, response);
			}
			
			// 회원관리 페이지 회원 검색 Controller ****************************************************
			else if(command.equals("/memberSearch.do")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("searchKey", searchKey);
				map.put("searchValue", searchValue);
				map.put("currentPage", cp);
				
				PageInfo pInfo = service.getMemberPageInfo(map);
				
				List<Member> mList = service.searchMemberList(map, pInfo);
				
				path = "/WEB-INF/views/admin/memberManagement.jsp";
		        
		        request.setAttribute("mList", mList);
		        request.setAttribute("pInfo", pInfo);
		        
		        view = request.getRequestDispatcher(path);
		        view.forward(request, response);
			}
		
		} catch (Exception e) {
	         e.printStackTrace();
	         path = "/WEB-INF/views/common/errorPage.jsp";
	         request.setAttribute("errorMsg", "검색 과정에서 오류 발생");
	         view = request.getRequestDispatcher(path);
	         view.forward(request, response);
	    }
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
