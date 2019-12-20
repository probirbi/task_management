package de.neasy.task.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component

public class UserServiceInterceptorAppConfig implements WebMvcConfigurer {

    @Autowired
    UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(userInterceptor);
        System.out.println("Intercepter Registry");
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/user/*")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/list")
                .excludePathPatterns("/user/register");

   /*     registry.addInterceptor(new LocaleChangeInterceptor());
        registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/user/add/**", "/user/profile/**");*/
    }

}
