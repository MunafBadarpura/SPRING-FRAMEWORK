package org.example;

/*
* Java based config -> AnnotationConfigApplicationContext, @Configuration
* Bean name -> method name, @Bean(name = "desk1"), @Bean(name = {"desk1", "desk2", "desk3"})
* Scope ->@Scope("prototype"),  @Scope(value = "prototype")
* Autowire -> @Autowired
* Qualifier -> @Qualifier("desktop") (is is similar as 'ref' in xml)
* Primary -> @Primary
* Constructor Injection
*
* Component Stereotype Annotation
* -> @Component, @Component("name")
* -> @ComponentScan("org.example");
* -> @Primary at top of class
* -> @Scope("prototype")
* -> @Value("21")
* -> @Qualifier("laptop") >>>> @Primary
*
* */

import org.example.config.JavaConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        Alien alien = context.getBean(Alien.class);

        System.out.println(alien.getAge());
        alien.code();


    }
}
