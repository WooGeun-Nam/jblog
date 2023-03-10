package com.douzone.jblog.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.douzone.jblog.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 1. handler 종류를 확인해 보는 방법
		if (!(handler instanceof HandlerMethod)) {
			// DefaultServletHandler가 처리하는 경우(정적 자원, /assets/**)
			return true;
		}

		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. Handler Method의 @Auth 가져오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. Handler Method에 @Auth 가 없으면 Type(Class)에 붙어 있는 지 확인한다.
		if (auth == null) {
			// auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}

		// 5. Type이나 Method에 @Auth 가 없는 경우
		if (auth == null) {
			return true;
		}

		// 6. @Auth가 붙어 있기 때문에 인증(Authenfication) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String id = (String) pathVariables.get("id");

		// 인증이 안된경우
		if (authUser == null) {
			response.sendRedirect(request.getContextPath());
			return false;
		}

		// 7. 권한(Authorization) 체크를 위해 @Auth의 role 가져오기("ADMIN", "USER")

		// 8. 인증 확인
		if (id.equals(authUser.getId())) {
			return true;
		}

		response.sendRedirect(request.getContextPath());
		return false;
	}

}
