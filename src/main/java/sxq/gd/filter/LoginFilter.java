package sxq.gd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {
	private String[] urlPatterns;
	private String[] escapeUrls;
	public void destroy() {
 
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsr = (HttpServletRequest)request;
		HttpServletResponse hsr2 = (HttpServletResponse)response;
		String url = hsr.getRequestURI();
		
		if(checkUrlParttern(url)){
			if(!checkEscapeUrl(url)){
				if(hsr.getSession().getAttribute("LOGIN_USER")==null){
					hsr2.sendRedirect(hsr.getContextPath()+"/login.jsp");
					return;
				}
			}
		}
		chain.doFilter(hsr, hsr2);
		
	}
	
	private boolean checkUrlParttern(String url){
		for(int i=0;i<urlPatterns.length;i++){
			if(url.indexOf(urlPatterns[i])>-1){
				return true;
			}
		}
		return false;
	}
	
	private boolean checkEscapeUrl(String url){
		for(int i=0;i<escapeUrls.length;i++){
			if(url.indexOf(escapeUrls[i])>-1){
				return true;
			}
		}
		return false;
	}
	
	public void init(FilterConfig config) throws ServletException {
		String	urlPattern = config.getInitParameter("urlPattern");
		String escapeUrl = config.getInitParameter("escapeUrl");
		urlPattern = urlPattern.replace("，", ",");
		escapeUrl = escapeUrl.replace("，", ",");
		
		urlPatterns = urlPattern.split(",");
		escapeUrls = escapeUrl.split(",");
		
	}

}
