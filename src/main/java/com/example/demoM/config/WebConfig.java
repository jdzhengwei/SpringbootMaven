package com.example.demoM.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.DispatcherType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demoM.resolver.CurrentUserArgumentResolver;
import com.example.demoM.util.ApiResultResponseAdvice;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {


	@Autowired
    private Environment env;
	
	@Bean
	@Profile({ProfileNames.DEFAULT, ProfileNames.DEV})
	public FilterRegistrationBean corsFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new CorsFilter());
		registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
		registrationBean.setOrder(1);
		return registrationBean;
	}
	
	 @RestControllerAdvice("com.example.demoM.controller")
	 static class ResponseAdvice extends ApiResultResponseAdvice {
	 }
	 
	 @Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		// 对应无法直接返回String类型
		converters.add(0, new MappingJackson2HttpMessageConverter() {
			@Override
			public ObjectMapper getObjectMapper() {
				super.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
				return super.getObjectMapper();
			}

			@Override
			public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {
				List<MediaType> st = new ArrayList<MediaType>();
				MediaType text_plain = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
				MediaType text_html = new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8"));
				MediaType application_json = new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8"));
				MediaType application_xml = new MediaType(MediaType.APPLICATION_XML, Charset.forName("UTF-8"));
				MediaType application_xhtml_xml = new MediaType(MediaType.APPLICATION_XHTML_XML,
						Charset.forName("UTF-8"));
				MediaType x_www_form_urlencoded_utf8 = new MediaType(MediaType.APPLICATION_FORM_URLENCODED,
						Charset.forName("UTF-8"));
				st.add(text_html);
				st.add(text_plain);
				st.add(application_json);
				st.add(application_xml);
				st.add(application_xhtml_xml);
				st.add(MediaType.APPLICATION_JSON_UTF8);
				st.add(x_www_form_urlencoded_utf8);
				st.addAll(supportedMediaTypes);
				super.setSupportedMediaTypes(st);
			}
		});
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 只有非staging和非prod环境才启用swagger
		if (env.acceptsProfiles(ProfileNames.DEFAULT, ProfileNames.DEV)) {
			registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
	}
		
//	@Bean
//	public Filter tokenFilter() {
//		return new TokenFilter();
//	}
	
//	@Bean
//    public DispatcherServlet dispatcherServlet() {
//        return new DispatcherServlet();
//    }
//
//    @Bean
//    public ServletRegistrationBean dispatcherServletRegistration() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), "/*");
//        registration.setName("dispatcherServlet");
//        return registration;
//    }

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}
	
	@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(currentUserArgumentResolver());
    }
	
	@Bean
    public CurrentUserArgumentResolver currentUserArgumentResolver() {
        return new CurrentUserArgumentResolver();
    }


//	@Bean
//	public CommonsMultipartResolver commonsMultipartResolver() throws IOException {
//		final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//		commonsMultipartResolver.setMaxUploadSize(-1);
//		commonsMultipartResolver.setUploadTempDir(new FileSystemResource(uploadTmpPath));
//		commonsMultipartResolver.setMaxUploadSizePerFile(5120000L);
//		commonsMultipartResolver.setMaxUploadSize(20480000L);
//		return commonsMultipartResolver;
//	}

	@Bean
	public FilterRegistrationBean multipartFilterRegistrationBean() {
		final MultipartFilter multipartFilter = new MultipartFilter();
		final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(multipartFilter);
		filterRegistrationBean.addInitParameter("multipartResolverBeanName", "commonsMultipartResolver");
		return filterRegistrationBean;
	}

}