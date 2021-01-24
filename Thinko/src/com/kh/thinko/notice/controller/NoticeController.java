package com.kh.thinko.notice.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.thinko.board.model.vo.PageInfo;
import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.common.MyFileRenamePolicy;
import com.kh.thinko.member.model.vo.Member;
import com.kh.thinko.notice.model.service.NoticeService;
import com.kh.thinko.notice.model.vo.Notice;
import com.oreilly.servlet.MultipartRequest;


@WebServlet("/notice/*")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); 		
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/notice").length());

		String path = null; // forward 또는 redirect 경로를 저장할 변수
		RequestDispatcher view = null; // 요청 위임 객체
		
		String swalIcon = null; // sweet alert로 메세지 전달하는 용도
		String swalTitle = null;
		String swalText = null;
		
		String errorMsg = null; // 에러메시지 전달용 변수
		
		try {
			
			NoticeService service = new NoticeService();
			
			String searchKey = request.getParameter("sk");
			String searchValue = request.getParameter("sv");
			String cp = request.getParameter("cp");
			
			if(command.equals("/noticeList.do")) {
				errorMsg = "공지사항 조회 페이지로 이동 중 에러 발생";
				
				PageInfo pInfo = service.getPageInfo(cp);
				
				// System.out.println(pInfo);
				List<Notice> nList = service.selectNoticeList(pInfo);
				
				//System.out.println(nList);
				
				request.setAttribute("nList", nList);
				request.setAttribute("pInfo", pInfo);
				
				path = "/WEB-INF/views/notice/noticeList.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			else if(command.equals("/noticeInsertForm.do")) {
				errorMsg = "공지사항 작성 페이지로 이동 중 에러 발생";
				
				path = "/WEB-INF/views/notice/noticeInsert.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			else if (command.equals("/imageUpload.do")) {
				int maxSize = 20 * 1024 * 1024;

				String root = request.getSession().getServletContext().getRealPath("/");
				String filePath = root + "resources/uploadImages/";

				MultipartRequest multiRequest = new MultipartRequest(request, filePath, maxSize, "UTF-8",
						new MyFileRenamePolicy());

				Enumeration<String> files = multiRequest.getFileNames();
				String file = (String) files.nextElement();

				String fileName = multiRequest.getFilesystemName(file);

				response.getWriter().print(fileName);

			}
			
			else if(command.equals("/noticeInsert.do")) {
				errorMsg = "공지사항 삽입 과정에서 오류가 발생했습니다.";

				String root = request.getSession().getServletContext().getRealPath("/");
				String filePath = root + "resources/uploadImages/";

				String noticeTitle = request.getParameter("noticeTitle");
				String noticeContent = request.getParameter("noticeContent");
				Member loginMember = (Member) request.getSession().getAttribute("loginMember");
				int noticeWriter = loginMember.getMemberNo();

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("noticeTitle", noticeTitle);
				map.put("noticeContent", noticeContent);
				map.put("noticeWriter", noticeWriter);
				map.put("filePath", filePath);

				int result = service.insertNotice(map);

				if (result > 0) { 
					path = "../notice/noticeView.do?cp=1&no=" + result;
				} else {
					swalIcon = "error";
					swalTitle = "게시글 등록에 실패했습니다.";
					path = "../notice/noticeList.do?cp1"; 
				}
				
				 request.getSession().setAttribute("swalIcon", swalIcon);
				 request.getSession().setAttribute("swalTitle", swalTitle);
				 response.sendRedirect(path);
			}
			
			else if(command.equals("/noticeView.do")) {
				errorMsg = "공지상 상세 조회 중 오류 발생";
				
				int noticeNo = Integer.parseInt(request.getParameter("no"));
				
				Notice notice = service.selectNotice(noticeNo);
							
				if(notice != null) {
					
					path = "/WEB-INF/views/notice/noticeView.jsp";
					request.setAttribute("notice", notice);
					
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				} else {
					request.getSession().setAttribute("swalIcon", "error");
					request.getSession().setAttribute("swalTitle", "게시글 상세조회 실패");
					response.sendRedirect("list.do?cp=1");
				}
			}
			
			else if(command.equals("/updateForm.do")) {
				 errorMsg = "게시글 수정 화면 전환 과정에서 오류 발생";
				 
				 int noticeNo = Integer.parseInt(request.getParameter("no"));
				 
				 Notice notice = service.updateView(noticeNo);
				 
				 if(notice != null) {
				 request.setAttribute("notice", notice);
		
				 path = "/WEB-INF/views/notice/noticeUpdate.jsp";
				 view = request.getRequestDispatcher(path);
				 view.forward(request, response);
				 }else {
					 request.getSession().setAttribute("swalIcon", "error");
					 request.getSession().setAttribute("swalTitle", "게시글 조회에 실패했습니다.");
					 response.sendRedirect(request.getHeader("referer"));
				 }
			}
			
			// 게시글 수정 Controller ****************************************
			else if(command.equals("/update.do")) {
				errorMsg = "공지사항 수정 과정에서 오류가 발생했습니다.";

				int maxSize = 20 * 1024 * 1024;

				String root = request.getSession().getServletContext().getRealPath("/");
				String filePath = root + "resources/uploadImages/";

				MultipartRequest multiRequest = new MultipartRequest(request, filePath, maxSize, "UTF-8",
						new MyFileRenamePolicy());

				String noticeTitle = multiRequest.getParameter("noticeTitle");
				String noticeContent = multiRequest.getParameter("noticeContent");
				int noticeNo = Integer.parseInt(multiRequest.getParameter("no"));
				
				
				Member loginMember = (Member) request.getSession().getAttribute("loginMember");
				int noticeWriter = loginMember.getMemberNo();

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("noticeTitle", noticeTitle);
				map.put("noticeContent", noticeContent);
				map.put("noticeWriter", noticeWriter);
				map.put("noticeNo", noticeNo);

				int result = service.updateNotice(map);

				 path = "../notice/noticeView.do?cp=" + cp + "&no=" + noticeNo;

				 String sk = multiRequest.getParameter("sk");
				 String sv = multiRequest.getParameter("sv");
				 
				 if(sk != null && sv != null) {
					 path += "&sk=" + sk + "&sv=" + sv;
				 }

				 if(result == 0) {
					 swalIcon = "error";
					 swalTitle = "게시글 수정에 실패했습니다.";
				 }
				 
				 request.getSession().setAttribute("swalIcon", swalIcon);
				 request.getSession().setAttribute("swalTitle", swalTitle);
				 
				 response.sendRedirect(path);
			}
			
			else if(command.equals("/noticeSearch.do")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("searchKey", searchKey);
				map.put("searchValue", searchValue);
				map.put("currentPage", cp);
		
				PageInfo pInfo = service.getSearchPageInfo(map);
				
				List<Notice> nList = service.searchNoticeList(map, pInfo);
				
				path = "/WEB-INF/views/notice/noticeList.jsp";
		        
		        request.setAttribute("nList", nList);
		        request.setAttribute("pInfo", pInfo);
		        
		        view = request.getRequestDispatcher(path);
		        view.forward(request, response);
			}
			
			 // 공지사항 삭제 Controller
			else if(command.equals("/updateStatus.do")) {
				errorMsg = "공지사항 삭제 과정에서 오류가 발생했습니다.";
				int noticeNo = Integer.parseInt(request.getParameter("no"));
				
				int result = service.updateNoticeStatus(noticeNo);

				 if(result == 0) {
					 swalIcon = "error";
					 swalTitle = "공지사항 삭제에 실패했습니다.";
					 request.getSession().setAttribute("swalIcon", swalIcon);
					 request.getSession().setAttribute("swalTitle", swalTitle);
					 response.sendRedirect(request.getHeader("referer"));
				 }else {
					 response.sendRedirect("../notice/noticeList.do?cp=" + cp);
				 }

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
