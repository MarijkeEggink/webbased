package nl.bioinf.meggink.webbased.webfilters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/edit")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /*not interesting here*/
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = ((HttpServletRequest)request);
            System.out.println("[AuthenticationFilter] Intercepted URL: " + req.getRequestURL().toString());
            final HttpSession session = req.getSession();
            if (session.getAttribute("user") == null){
                System.out.println("[AuthenticationFilter] no authentication user: redirecting to /login");
                ((HttpServletResponse)response).sendRedirect("/login");
            } else {
                System.out.println("[AuthenticationFilter] authentication status checked; user= " + session.getAttribute("user"));
                try {
                    chain.doFilter(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void destroy() {
        /*not interesting here*/
    }
}
