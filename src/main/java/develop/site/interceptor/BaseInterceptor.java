package develop.site.interceptor;


import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BaseInterceptor extends HandlerInterceptorAdapter {

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String controllerName = "";
        String actionName = "";

        if( handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //controllerName = handlerMethod.getBean().getClass().getSimpleName().replace("Controller", "");
            controllerName  = handlerMethod.getBeanType().getSimpleName().replace("Controller", "");
            actionName = handlerMethod.getMethod().getName();
        }

        modelAndView.addObject("controllerName", controllerName );
        modelAndView.addObject("actionName", actionName );
    }

}