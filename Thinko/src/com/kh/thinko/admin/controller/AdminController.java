package com.kh.thinko.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.thinko.admin.model.service.AdminService;
import com.kh.thinko.board.model.vo.PageInfo;
import com.kh.thinko.board.model.vo.ReportBoard;
import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.member.model.vo.Member;
import com.kh.thinko.reply.model.vo.Reply;
import com.kh.thinko.reply.model.vo.VReportReply;

/** 관리자 페이지 컨트롤러
 * @author 유현재
 *
 */
@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); 		
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/admin").length());

		String path = null; // forward 또는 redirect 경로를 저장할 변수
		RequestDispatcher view = null; // 요청 위임 객체
		
		String swalIcon = null; // sweet alert로 메세지 전달하는 용도
		String swalTitle = null;
		String swalText = null;
		
		String errorMsg = null; // 에러메시지 전달용 변수
		
		try {
			
			AdminService service = new AdminService();
			
			String cp = request.getParameter("cp");
			
			// 관리자 페이지로 이동 Controller **************************************
			if(command.equals("/adminPage.do")) {
			
				errorMsg = "관리자 페이지로 이동 중 에러 발생";
				
				int visitorCount = service.getVisitorCount();
				int boardCount = service.getBoardCount();
				List<VBoard> bList = service.selectBoardList();
				List<Reply> rList = service.selectReplyList();
				List<ReportBoard> rbList = service.selectReportBoard(); 
				List<VReportReply> rrList = service.selectReportReply();
				
				request.setAttribute("visitorCount", visitorCount);
				request.setAttribute("boardCount", boardCount);
				request.setAttribute("bList", bList);
				request.setAttribute("rList", rList);
				request.setAttribute("rbList", rbList);
				request.setAttribute("rrList", rrList);
				
				
				path = "/WEB-INF/views/admin/adminPage.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			// 회원관리 페이지로 이동 Controller *************************************
			else if(command.equals("/memberManagement.do")) {
				errorMsg = "회원관리 페이지로 이동 중 에러 발생";
				
				PageInfo pInfo = service.getMemberPageInfo(cp);
				
				List<Member> mList = service.selectMemberList(pInfo);
				
				request.setAttribute("mList", mList);
				request.setAttribute("pInfo", pInfo);
				
				path = "/WEB-INF/views/admin/memberManagement.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			// 게시글 관리 페이지로 이동 Controller *************************************
			else if(command.equals("/boardManagement.do")) {
				errorMsg = "게시글관리 페이지로 이동 중 에러 발생";
				
				int boardCount = service.getBoardCount();
				int replyCount = service.getReplyCount();
				List<ReportBoard> rbList = service.selectReportBoard(); 
				List<VReportReply> rrList = service.selectReportReply();
				
				request.setAttribute("boardCount", boardCount);
				request.setAttribute("replyCount", replyCount);
				request.setAttribute("rbList", rbList);
				request.setAttribute("rrList", rrList);
				
				path = "/WEB-INF/views/admin/boardManagement.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			// 모든 게시물 조회 페이지로 이동 Controller *************************************
			else if(command.equals("/allBoardList.do")) {
				errorMsg = "모든 게시글 조회페이지로 이동 중 에러 발생";
				
				PageInfo pInfo = service.getPageInfo(cp);
				
				//System.out.println(pInfo);
				List<VBoard> bList = service.selectBoardList(pInfo);
				
				request.setAttribute("bList", bList);
				request.setAttribute("pInfo", pInfo);
				
				path = "/WEB-INF/views/admin/allBoardList.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			// 모든 댓글 조회 페이지로 이동 Controller *************************************
			else if(command.equals("/allReplyList.do")) {
				errorMsg = "모든댓글 조회 페이지로 이동 중 에러 발생";
				
				PageInfo pInfo = service.getReplyPageInfo(cp);
				
				//System.out.println(pInfo);
				List<Reply> rList = service.selectReplyList(pInfo);
				
				request.setAttribute("rList", rList);
				request.setAttribute("pInfo", pInfo);
				
				path = "/WEB-INF/views/admin/allReplyList.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			// 신고 게시물 조회 페이지로 이동 Controller *************************************
			else if(command.equals("/reportedBoardList.do")) {
				errorMsg = "신고게시물 조회 페이지로 이동 중 에러 발생";
				
				PageInfo pInfo = service.getReportBoardPageInfo(cp);
				
				List<ReportBoard> rbList = service.selectReportedBoardList(pInfo);
				
				request.setAttribute("rbList", rbList);
				request.setAttribute("pInfo", pInfo);
				
				path = "/WEB-INF/views/admin/reportedBoardList.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			// 신고 댓글 조회 페이지로 이동 Controller *************************************
			else if(command.equals("/reportedReplyList.do")) {
				errorMsg = "신고 댓글 조회 페이지로 이동 중 에러 발생";
				
				PageInfo pInfo = service.getReportReplyPageInfo(cp);
				
				List<VReportReply> rrList = service.selectReportedReplyList(pInfo);
				
				request.setAttribute("rrList", rrList);
				request.setAttribute("pInfo", pInfo);
				
				path = "/WEB-INF/views/admin/reportedReplyList.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			// 회원관리 페이지에서 회원 상태 업데이트 Controller ******************************
			else if(command.equals("/updateMemberStatus.do")) {
				int memberNo = Integer.parseInt(request.getParameter("memNo"));
				
				String memberStatus = request.getParameter("memSt");
				
				int	result = service.updateMemberStatus(memberNo, memberStatus);
				
				if(result > 0) {
					swalIcon = "success";
					swalTitle = "회원상태가 변경되었습니다";
					path = "memberManagement.do";
				} else {
					swalIcon = "error";
					swalTitle = "회원상태 변경에 실패했습니다.";
					path = request.getHeader("referer");
				}
				
				request.getSession().setAttribute("swalIcon", swalIcon);
				request.getSession().setAttribute("swalTitle", swalTitle);
				
				response.sendRedirect(path);
			}
			
			// 디비 - 서버 이미지파일 정리 Controller ********************************************
			else if(command.equals("/fileClean.do")) {
				String root = request.getSession().getServletContext().getRealPath("/");
				
				root += "resources/uploadImages/";
				
				File dir = new File(root);
				
				List<File> fileList = Arrays.asList(dir.listFiles());
				
				List<String> dbFileList = service.getDbFileName();
				
				for(File file : fileList) {
					boolean flag = true;
					
					for(String dbFile : dbFileList) {
						if(file.getName().equals(dbFile)) {
							flag = false;
							break;
						}
					}
					
					if(flag) {
						file.delete();
					}
				}
				
				request.getSession().setAttribute("swalIcon", "success");
				request.getSession().setAttribute("swalTitle", "DB 청소 완료!");
				
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
