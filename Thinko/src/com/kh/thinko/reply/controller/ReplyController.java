package com.kh.thinko.reply.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.thinko.reply.model.service.ReplyService;
import com.kh.thinko.reply.model.vo.Reply;
import com.kh.thinko.reply.model.vo.VReply;

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/reply").length());

		String path = null; // forward 또는 redirect 경로를 저장할 변수
		RequestDispatcher view = null; // 요청 위임 객체
		
		String swalIcon = null; // sweet alert로 메세지 전달하는 용도
		String swalTitle = null;
		String swalText = null;
		
		String errorMsg = null;
		
		
		try {
			ReplyService service = new ReplyService();

			String cp = request.getParameter("cp");
			
			// 댓글 목록 조회
			if (command.equals("/selectList.do")) {

				int parentBoardNo = Integer.parseInt(request.getParameter("parentBoardNo"));

				List<VReply> rList = service.selectList(parentBoardNo);

				Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일 HH:mm").create();
				gson.toJson(rList, response.getWriter());

			}

			// 댓글 삽입
			else if (command.equals("/insertReply.do")) {

				String replyWriter = request.getParameter("replyWriter");
				int parentBoardNo = Integer.parseInt(request.getParameter("parentBoardNo"));
				String replyContent = request.getParameter("replyContent");

				Reply reply = new Reply();
				reply.setMemberId(replyWriter);
				reply.setReplyContent(replyContent);
				reply.setParentBoardNo(parentBoardNo);

				int result = service.insertReply(reply);

				response.getWriter().print(result);

			}

			// 댓글 수정
			else if (command.equals("/updateReply.do")) {

				// 파라미터(댓글 번호, 댓글 내용) 얻어오기
				int replyNo = Integer.parseInt(request.getParameter("replyNo"));
				String replyContent = request.getParameter("replyContent");

				Reply reply = new Reply();
				reply.setReplyNo(replyNo);
				reply.setReplyContent(replyContent);

				int result = service.updateReply(reply);

				response.getWriter().print(result);
			}

			// 댓글 삭제
			else if (command.equals("/deleteReply.do")) {

				int replyNo = Integer.parseInt(request.getParameter("replyNo"));

				int result = service.updateReplyStatus(replyNo);

				response.getWriter().print(result);
			}
			
			// 댓글 신고
			else if (command.equals("/reportReply.do")) {
				
				int replyNo = Integer.parseInt(request.getParameter("replyNo"));
				int memberNo = Integer.parseInt(request.getParameter("memberNo"));
				int reportCategoryNo = Integer.parseInt(request.getParameter("rc"));
				
				int findResult = service.findReport(replyNo, memberNo);
				
				if(findResult > 0) {
					swalIcon = "error";
					swalTitle = "댓글 신고는 한번만 가능합니다.";
				}else {
					int result = service.reportReply(replyNo, memberNo, reportCategoryNo);
					
					if(result > 0) {
						swalIcon = "success";
						swalTitle = "댓글이 신고되었습니다";
					} else { 
						swalIcon = "error";
						swalTitle = "댓글 신고 중 오류가 발생하였습니다";
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
