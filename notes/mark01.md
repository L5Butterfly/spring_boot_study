
# spring 启动类的实现，实现spring mvc 
```
@SpringBootApplication
// 扫描 mybatis mapper 包路径
@MapperScan(basePackages = "com.leecx.mapper")
// 扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
@ComponentScan(basePackages= {"com.leecx", "org.n3r.idworker"})
// 开启定时任务
@EnableScheduling
// 开启异步调用方法
@EnableAsync
public class Application {
	
	/**
	* 配置JSP视图解析器
	* 如果没有配置视图解析器。Spring会使用BeanNameViewResolver，通过查找ID与逻辑视图名称匹配且实现了View接口的beans
	* 
	* @return
	*/
	@Bean
	public InternalResourceViewResolver setupViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		/** 设置视图路径的前缀 */
		resolver.setPrefix("/WEB-INF/jsp/");
		/** 设置视图路径的后缀 */
		resolver.setSuffix(".jsp");
		return resolver;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

/**
mybatis 数据库操作异常：
tk.mybatis.mapper.provider.base.BaseInsertProvider
import org.mybatis.spring.annotation.MapperScan;

SpringBoot启动错误原因是：
springboot启动时会自动注入数据源和配置jpa 
解决：在@SpringBootApplication中排除其注入 
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})

**/


```

#  开始

##################################################################################
org.springframework.web.servlet.view.InternalResourceViewResolver
##################################################################################

# 1. 在spring boot中配置方式：

```
/**
* 配置JSP视图解析器
* 如果没有配置视图解析器。Spring会使用BeanNameViewResolver，通过查找ID与逻辑视图名称匹配且实现了View接口的beans
* 
* @return
*/
@Bean
public InternalResourceViewResolver setupViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		/** 设置视图路径的前缀 */
		resolver.setPrefix("/WEB-INF/jsp/");
		/** 设置视图路径的后缀 */
		resolver.setSuffix(".jsp");
		return resolver;
}
他的作用是在Controller返回的时候进行解析视图
prefix 这个表示目录
suffix 这个表示后缀

```


# 2. 在xml中配置方式：

```
<bean id="jsp"  class="org.springframework.web.servlet.view.InternalResourceViewResolver" >
        <property name="order" value="44" />
        <property name="contentType" value="text/html"/>
        <property name="prefix" value="/WEB-INF/"/>
        <property name="suffix" value=".jsp"/>
</bean>

```


# 我WEB-INF下面有ta.html和tas.jsp
```
<mvc:view-controller path="/" view-name="ta"/>//注意是以这个为重点
    <mvc:annotation-driven  />
    <mvc:default-servlet-handler/>
    <!-- 自动扫描(自动注入) -->
    <context:component-scan base-package="com.wwzuizz.**.controller"/>
    <mvc:resources mapping="/WEB-INF/**" location="/WEB-INF/"/>
    <bean  id="html" class="org.springframework.web.servlet.view.InternalResourceViewResolver"  >
        <property name="order" value="3344" />
        <property name="prefix" value="/WEB-INF/"></property>
        <property name="suffix" value=".html"/>
        <property name="contentType" value="text/html"></property>
    </bean>
    <bean id="jsp"  class="org.springframework.web.servlet.view.InternalResourceViewResolver" >
        <property name="order" value="44" />
        <property name="contentType" value="text/html"/>
        <property name="prefix" value="/WEB-INF/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    
```


> 进行如上配置的时候，根据优先级它会先取jsp，然后在html。可是发现它只是检查一次而已，如如果是jsp的order比较低，则只会解析jsp，如果找不到就直接异常了。
如果是html的order比较低，则只会解析html，如果找不到也会直接报异常。
我想要的效果是优先解析jsp，找不到再解析htnl。
查看源码UrlBasedViewResolver（InternalResourceViewResolver的父类）
发现这个方法，AbstractUrlBasedView.checkResource()是永远返回true的，也就是如果找不到，也会返回一个result，但是这个result是找不到的。
为此只需要重写AbstractUrlBasedView这个类


```
<mvc:view-controller path="/" view-name="ta"/>
    <mvc:annotation-driven  />
    <mvc:default-servlet-handler/>
    <!-- 自动扫描(自动注入) -->
    <context:component-scan base-package="com.wwzuizz.**.controller"/>
    <mvc:resources mapping="/WEB-INF/**" location="/WEB-INF/"/>
    <bean  id="html" class="org.springframework.web.servlet.view.InternalResourceViewResolver"  >
        <property name="order" value="3344" />
        <property name="viewClass" value="com.wwzuizz.common.DefaultJstlView"/>
        <property name="prefix" value="/WEB-INF/"></property>
        <property name="suffix" value=".html"/>
        <property name="contentType" value="text/html"></property>
    </bean>
    <bean id="jsp"  class="org.springframework.web.servlet.view.InternalResourceViewResolver" >
        <property name="order" value="44" />
        <property name="viewClass" value="com.wwzuizz.common.DefaultJstlView"/>
        <property name="contentType" value="text/html"/>
        <property name="prefix" value="/WEB-INF/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

```

# com.wwzuizz.common.DefaultJstlView

```
public class DefaultJstlView extends JstlView {
 
    @Override
    public boolean checkResource(Locale locale) throws Exception {
        File file = new File(this.getServletContext().getRealPath("/") + getUrl());
        return file.exists();//判断该jsp页面是否存在
    }
}

# com.wwzuizz.common.DefaultJstlView,为此只需要重写AbstractUrlBasedView这个类
这样子如果不存在就传递给下一个InternalResourceViewResolve;其实我很好奇order是在哪里实现的代码，我带入tomcat源码页找不到是哪里的
这样子就可以做到

<mvc:view-controller path="/" view-name="ta"/>
ta如果jsp解析不到，就作为html解析


```


#####################################################################################
org.springframework.web.servlet.view.InternalResourceViewResolver
#####################################################################################

补充：
org.springframework.web.servlet.DispatcherServlet
如下视图解析的时候会for的去找 所以如果不为空就直接返回

```
@Nullable
	protected View resolveViewName(String viewName, @Nullable Map<String, Object> model,
			Locale locale, HttpServletRequest request) throws Exception {
 
		if (this.viewResolvers != null) {
			for (ViewResolver viewResolver : this.viewResolvers) {
				View view = viewResolver.resolveViewName(viewName, locale);
				if (view != null) {
					return view;
				}
			}
		}
		return null;
	}

```

















