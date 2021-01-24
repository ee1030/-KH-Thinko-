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

import com.kh.thinko.wrapper.EncryptWrapper;

/**
 * @author jeonga
 *
 */
@WebFilter(urlPatterns = {"/member/login.do", "/member/join.do", "/member/myPage.do", "/member/updateMember.do",
							"/member/updatePwd.do", "/member/updateStatus.do", "/member2/memberLogin.do",
							"/member2/searchPwdChange.do"})
public class EncryptFilter implements Filter {

    public EncryptFilter() {}
    public void destroy() {}
    public void init(FilterConfig fConfig) throws ServletException {}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest req = (HttpServletRequest)request;
		
		EncryptWrapper encWrapper = new EncryptWrapper(req);
		
		
		chain.doFilter(encWrapper, response);
	}


}
