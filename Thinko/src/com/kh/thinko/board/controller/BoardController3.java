package com.kh.thinko.board.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.common.MyFileRenamePolicy;
import com.kh.thinko.member.model.vo.Member;
import com.kh.thinko.board.model.service.BoardService3;
import com.oreilly.servlet.MultipartRequest;

/**
 * 게시글 등록, 수정, 삭제 Controller
 * 
 * @author jeonga
 *
 */
@WebServlet("/board3/*")
public class BoardController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI(); // /thinko/board/list.do
		String contextPath = request.getContextPath(); // /thinko
		String command = uri.substring((contextPath + "/board3").length());

		String path = null;
		RequestDispatcher view = null;

		String swalIcon = null;
		String swalTitle = null;

		String errorMsg = null;

		try {
			BoardService3 service = new BoardService3();

			String cp = request.getParameter("cp");

			// 게시글 등록 화면 Controller
			if (command.equals("/insertForm.do")) {

				request.setAttribute("type", Integer.parseInt(request.getParameter("type")));
				path = "/WEB-INF/views/board/boardInsert.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			}

			// 이미지 업로드 Controller
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

			// 게시글 등록 Controller
			else if (command.equals("/insert.do")) {
				errorMsg = "게시글 등록 과정에서 오류가 발생했습니다.";

				String root = request.getSession().getServletContext().getRealPath("/");
				String filePath = root + "resources/uploadImages/";

				String boardTitle = request.getParameter("boardTitle");
				String boardContent = request.getParameter("boardContent");
				int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
				int boardType = Integer.parseInt(request.getParameter("type"));
				String lat = request.getParameter("lat");
				String lng = request.getParameter("lng");

				Member loginMember = (Member) request.getSession().getAttribute("loginMember");
				int boardWriter = loginMember.getMemberNo();

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("boardTitle", boardTitle);
				map.put("boardContent", boardContent);
				map.put("categoryCode", categoryCode);
				map.put("boardType", boardType);
				map.put("boardWriter", boardWriter);
				map.put("filePath", filePath);

				if (lat != null) {
					map.put("lat", lat);
					map.put("lng", lng);
				}

				int result = service.insertBoard(map);

				if (result > 0) {
					path = "../board1/boardView.do?cp=1&type=" + boardType + "&no=" + result;
				} else {
					swalIcon = "error";
					swalTitle = "게시글 등록에 실패했습니다.";
					path = "../board2/list.do?cp1&type=" + boardType;
				}

				request.getSession().setAttribute("swalIcon", swalIcon);
				request.getSession().setAttribute("swalTitle", swalTitle);
				response.sendRedirect(path);

			}
			// 게시글 수정 화면 Controller
			else if (command.equals("/updateForm.do")) {
				errorMsg = "게시글 수정 화면 전환 과정에서 오류 발생";

				int boardNo = Integer.parseInt(request.getParameter("no"));
				int boardType = Integer.parseInt(request.getParameter("type"));

				VBoard board = service.updateView(boardNo);

				if (board != null) {
					request.setAttribute("board", board);
					request.setAttribute("boardType", boardType);
					path = "/WEB-INF/views/board/boardUpdate.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				} else {
					request.getSession().setAttribute("swalIcon", "error");
					request.getSession().setAttribute("swalTitle", "게시글 조회에 실패했습니다.");
					response.sendRedirect(request.getHeader("referer"));
				}
			}

			// 게시글 수정 Controller
			else if (command.equals("/update.do")) {
				errorMsg = "게시글 수정 과정에서 오류가 발생했습니다.";

				String root = request.getSession().getServletContext().getRealPath("/");
				String filePath = root + "resources/uploadImages/";

				String boardTitle = request.getParameter("boardTitle");
				String boardContent = request.getParameter("boardContent");
				int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
				int boardType = Integer.parseInt(request.getParameter("type"));
				int boardNo = Integer.parseInt(request.getParameter("no"));

				Member loginMember = (Member) request.getSession().getAttribute("loginMember");
				int boardWriter = loginMember.getMemberNo();

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("boardTitle", boardTitle);
				map.put("boardContent", boardContent);
				map.put("categoryCode", categoryCode);
				map.put("boardType", boardType);
				map.put("boardWriter", boardWriter);
				map.put("boardNo", boardNo);
				map.put("filePath", filePath);

				int result = service.updateBoard(map);

				path = "../board1/boardView.do?cp=" + cp + "&no=" + boardNo + "&type=" + boardType;

				String sk = request.getParameter("sk");
				String sv = request.getParameter("sv");

				if (sk != null && sv != null) {
					path += "&sk=" + sk + "&sv=" + sv;
				}

				if (result == 0) {
					swalIcon = "error";
					swalTitle = "게시글 수정에 실패했습니다.";
				}

				request.getSession().setAttribute("swalIcon", swalIcon);
				request.getSession().setAttribute("swalTitle", swalTitle);

				response.sendRedirect(path);
			}

			// 게시글 삭제 Controller
			else if (command.equals("/updateStatus.do")) {
				errorMsg = "게시글 삭제 과정에서 오류가 발생했습니다.";
				int boardNo = Integer.parseInt(request.getParameter("no"));
				int boardType = Integer.parseInt(request.getParameter("type"));

				int result = service.updateBoardStatus(boardNo);

				if (result == 0) {
					swalIcon = "error";
					swalTitle = "게시글 삭제에 실패했습니다.";
					request.getSession().setAttribute("swalIcon", swalIcon);
					request.getSession().setAttribute("swalTitle", swalTitle);
					response.sendRedirect(request.getHeader("referer"));
				} else {
					response.sendRedirect("../board2/list.do?cp=" + cp + "&type=" + boardType);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
