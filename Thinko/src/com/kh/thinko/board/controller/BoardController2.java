package com.kh.thinko.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.thinko.board.model.service.BoardService2;
import com.kh.thinko.board.model.vo.Attachment;
import com.kh.thinko.board.model.vo.Board;
import com.kh.thinko.board.model.vo.PageInfo;
import com.kh.thinko.board.model.vo.VBoard;

@WebServlet("/board2/*")
public class BoardController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); 		// 	  /thinko/board/list.do?type=10
		String contextPath = request.getContextPath();//  /thinko/board
		String command = uri.substring((contextPath + "/board2").length()); // /list.do             ?type=10

		String path = null; // forward 또는 redirect 경로를 저장할 변수
		RequestDispatcher view = null; // 요청 위임 객체
		
		String swalIcon = null; // sweet alert로 메세지 전달하는 용도
		String swalTitle = null;
		String swalText = null;
		
		String errorMsg = null; // 에러메시지 전달용 변수
		
		// 질문게시판 Controller
		try {
			
			BoardService2 service = new BoardService2();
			
			// 현재 페이지를 얻어옴
	         String cp = request.getParameter("cp");
			
			if(command.equals("/list.do")) {
				errorMsg = "게시판 조회 과정에서 오류 발생";
				int type = 0;
				if(request.getParameter("type") != null) {
				 type = Integer.parseInt(request.getParameter("type"));
				}
				
				
				PageInfo pInfo = null;
				List<VBoard> bList = null;
				
				if(type != 20) {
					pInfo = service.getPageInfo(cp, type);
					bList = service.selectBoardList(pInfo, type); 
				}
				else {
					pInfo = new PageInfo(1, 10);
					bList = service.selectPopularPosts(); 
				}
				

				if(bList != null) {
					
					List<Attachment> fList = service.selectThumbnailList(pInfo);
					
					if(!fList.isEmpty()) {
	            		request.setAttribute("fList", fList);
	            	}
					
				}
				 
				request.setAttribute("pInfo", pInfo);
				request.setAttribute("bList", bList);
				request.setAttribute("type", type);
				/*
				 * path = "/WEB-INF/views/member/list.do.jsp"; view =
				 * request.getRequestDispatcher(path); view.forward(request, response);
				 */
				
				path = "/WEB-INF/views/board/boardList.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			else if(command.equals("/insertForm.do?" )) {
				path = "/WEB-INF/views/board/boardInsert.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
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
