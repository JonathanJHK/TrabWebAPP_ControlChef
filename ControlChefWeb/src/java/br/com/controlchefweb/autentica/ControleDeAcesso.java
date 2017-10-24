package br.com.controlchefweb.autentica;

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

@WebFilter(servletNames = { "Faces Servlet" })
public class ControleDeAcesso implements Filter {
        @Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		if ((session.getAttribute("USUARIOLogado") != null)
				|| (req.getRequestURI().endsWith("index.jsf"))
				|| (req.getRequestURI().contains("javax.faces.resource/"))) {

			

				//redireciona("/ControlChefWeb/main.jsf", response);
			
			chain.doFilter(request, response);
		}

		else {
			redireciona("/ControlChefWeb/index.jsf", response);
		}

	}

        @Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

        @Override
	public void destroy() {
	}

	private void redireciona(String url, ServletResponse response)
			throws IOException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.sendRedirect(url);
	}
}