package com.example.demoM.resolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 在Controller的方法参数中使用此注解，会自动注入当前登录的用户
 * 此注解依赖＠See {@link Authorization}
 * 必须是经过token校验才能确认是否登录状态，并影响注入结果
 * 
 * @author gexiangping
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {

}
