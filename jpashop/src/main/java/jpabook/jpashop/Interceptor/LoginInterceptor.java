package jpabook.jpashop.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginMember") == null) {
            //로그인으로 redirect
//            response.sendRedirect("/login?redirectURL=" + requestURI);
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
