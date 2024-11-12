package com.munaf.A12_SPRINGBOOT_WEB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
1. JSP : Create "webapp" folder in main, inside webapp create jsp(Jakarta Server Pages) pages and add this dependency "tomcat-jasper".
2. @Controller : This annotation is used to create a controller
3. tomcat-jasper : this is a dependency for convert jsp to servlet
4. @RequestMapping : this annotation is used to map HTTP requests to handler methods in controller classes.
5. HttpServletRequest : HttpServletRequest is used to retrieve data sent from a client in the request URL.
6. Model : Model is an interface that allows you to add data to be displayed in a view
7. HttpSession :HttpSession allows you to store attributes that persist across different requests from the same client. This is useful for things like login sessions.
8. spring.mvc.view : spring.mvc.view.prefix=/folderName/  ,  spring.mvc.view.suffix=extension(.jsp) [in application.properties]
9. ModelAndView : 1)mv.addObject(attributeName, variableName)   2)mv.setViewName(viewPageName)
10.@ModelAttribute : to accept object from http request url

*/


@SpringBootApplication
public class A12SpringbootWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(A12SpringbootWebApplication.class, args);
	}

}
