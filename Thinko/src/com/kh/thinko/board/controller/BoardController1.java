package com.kh.thinko.board.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.thinko.board.model.service.BoardService1;
import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.member.model.vo.Member;

@WebServlet("/board1/*")
public class BoardController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); 		
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/board1").length());

		String path = null; // forward 또는 redirect 경로를 저장할 변수
		RequestDispatcher view = null; // 요청 위임 객체
		
		String swalIcon = null; // sweet alert로 메세지 전달하는 용도
		String swalTitle = null;
		String swalText = null;
		
		String errorMsg = null; // 에러메시지 전달용 변수
		
		try {
			
			BoardService1 service = new BoardService1();
			
			String cp = request.getParameter("cp");
			
			if(command.equals("/boardView.do")) {
				errorMsg = "게시글 상세 조회 중 오류 발생";
				
				int boardNo = Integer.parseInt(request.getParameter("no"));				
				Member loginMember = (Member) request.getSession().getAttribute("loginMember");
				
				String memberId = "";
				
				if(loginMember != null) {
					memberId = loginMember.getMemberId();					
				}
				
				Map<String, Object> map = service.selectLocation(boardNo);
				
				if(!map.isEmpty()) {
					request.setAttribute("lat", (String)map.get("lat"));
					request.setAttribute("lng", (String)map.get("lng"));
				}
				
				VBoard board = service.selectBoard(boardNo, memberId);
							
				if(board != null) {
					
					path = "/WEB-INF/views/board/boardView.jsp";
					request.setAttribute("board", board);
					
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				} else {
					request.getSession().setAttribute("swalIcon", "error");
					request.getSession().setAttribute("swalTitle", "게시글 상세조회 실패");
					response.sendRedirect("../board2/list.do?cp=1");
				}
			}
			
			else if(command.equals("/reportBoard.do")) {
				errorMsg = "게시글 신고 과정 중 오류 발생";
				
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				int memberNo = Integer.parseInt(request.getParameter("memberNo"));
				int reportCategoryNo = Integer.parseInt(request.getParameter("rc"));
				
				int findReport = service.findReport(boardNo, memberNo);
				
				if(findReport > 0) {
					swalIcon = "error";
					swalTitle = "게시글 신고는 한번만 가능합니다.";
					path = request.getHeader("referer");
				} else {
					int result = service.reportBoard(boardNo, memberNo, reportCategoryNo);
					
					// 8. result 값에 따라 View 연결 처리
					path = request.getHeader("referer");
					
					if(result > 0) {
						swalIcon = "success";
						swalTitle = "게시글 신고 성공";
					} else { 
						swalIcon = "error";
						swalTitle = "게시글 신고 실패";
					}
				}
				
				request.getSession().setAttribute("swalIcon", swalIcon);
				request.getSession().setAttribute("swalTitle", swalTitle);
				
				response.sendRedirect(path);
			}
			
			else if(command.equals("/updateLike.do")) {
				errorMsg = "좋아요 기능 수행 중 오류 발생";
				
				int memberNo = Integer.parseInt(request.getParameter("mn"));
				int boardNo = Integer.parseInt(request.getParameter("no"));
				
				//System.out.println(memberNo + " / " + boardNo);
				
				int checkLike = service.checkLike(boardNo, memberNo);
				
				if(checkLike > 0) {
					String fl = service.updateLike(boardNo, memberNo);
					
					if(fl.equals("Y")) {
						swalIcon = "success";
						swalTitle = "좋아요!";
					} else { 
						swalIcon = "error";
						swalTitle = "좋아요를 취소합니다!";
					}
					
				} else {
					int insertLike = service.insertLike(boardNo, memberNo);
					
					if(insertLike > 0) {
						swalIcon = "success";
						swalTitle = "좋아요!";
					} else {
						swalIcon = "error";
						swalTitle = "좋아요 동작에 실패했습니다.";
					}
				}
				
				
				request.getSession().setAttribute("swalIcon", swalIcon);
				request.getSession().setAttribute("swalTitle", swalTitle);
				
				response.sendRedirect(request.getHeader("referer"));
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
