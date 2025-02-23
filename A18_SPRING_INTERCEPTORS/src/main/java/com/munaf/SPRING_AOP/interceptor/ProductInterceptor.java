package com.munaf.SPRING_AOP.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
1. Create a class and implement HandlerInterceptor and override 3 methods(preHandle, postHandle, afterCompletion)
2. Create a configuration class and implement WebMvcConfigurer and override 1 method called addInterceptors
3. Use registry of addInterceptor method and addInterceptor like this : registry.addInterceptor(new ProductInterceptor())
4. Add paths or exclude path like this : .addPathPatterns("/products/**"), .excludePathPatterns("/products/delete");

=> When to Use Interceptors :
✅ Authentication & Authorization
✅ Logging & Monitoring
✅ Modifying Requests & Responses
✅ Global Pre Post Processing


=> Difference Between Filters and Interceptors
    Feature	Filter	                                                    Interceptor
    Works at	  Servlet level	Spring MVC level
    Applied to	  All requests (including static resources)	            Only Spring MVC requests
    Execution     Order	Before the request enters Spring	            After Spring initializes controllers
    Use case	  General request modification (e.g., CORS, encoding)	Handling controller-specific logic (e.g., authentication, logging)

**/

public class ProductInterceptor implements HandlerInterceptor {

    @Override // After request come - Helps to modify request
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("PreHandler Interceptor Called For This API : " + request.getRequestURI());
        return true;
    }

    @Override // Before sending response - Helps to modify response
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("PostHandler Interceptor Called For This API : " + request.getRequestURI());
    }

    @Override // After sending response
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("AfterCompletion Interceptor Called For This API : " + request.getRequestURI());
    }
}
