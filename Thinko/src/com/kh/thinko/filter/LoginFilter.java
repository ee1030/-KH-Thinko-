package com.kh.thinko.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.thinko.member.model.vo.Member;

/**
 * @author jeonga
 *
 */
@WebFilter(urlPatterns = 
			{"/member/myPage.do", "/member/changePwdForm.do", "/member/secessionForm.do", "/myList.do", "/myBoardList.do", "/myReplyList.do",
			 "/member/updateMember.do", "/member/updatePwd.do", "/member/updateStatus.do", "/deleteMyBoard.do", "/deleteMyReply.do",
			 "/insertForm.do", "/insert.do", "/updateForm.do", "/update.do", "/updateStatus.do", "/admin/*"})
public class LoginFilter implements Filter {

    public LoginFilter() {}
    public void destroy() {}
    public void init(FilterConfig fConfig) throws ServletException {}

    					// 모든 요청을 받기위해 부모 매개변수
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		// ServletRequest 매개변수를 HttpServletRequest로 다운캐스팅
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		// Session 얻어오기
		HttpSession session = req.getSession();
		
		// Session에 있는 로그인된 회원 정보를 얻어옴.
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(loginMember == null) { // 로그인이 되어있지 않은 경우
			
			// 메인 페이지로 강제 이동
			res.sendRedirect( req.getContextPath() );
		}else {
			chain.doFilter(request, response);
			// 다음 필터 호출
			// 다음 필터가 없으면 Servlet 또는 JSP로 이동
		}
		
		
	}


}
