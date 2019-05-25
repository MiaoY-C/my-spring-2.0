package com.gupaoedu.spring.formework.webmvc.servlet;

import com.gupaoedu.spring.formework.annotation.GPController;
import com.gupaoedu.spring.formework.annotation.GPRequestMapping;
import com.gupaoedu.spring.formework.context.GPApplicationContext;
import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: MiaoYongchang
 * Date: 2019/4/12
 * Time: 13:20
 * Description: serlvet只是作为MVC启动时的一个入口
 */
@Slf4j
public class GPDispatcherServlet extends HttpServlet {
    private final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private GPApplicationContext context;

    private List<GPHandlerMapping> handlerMappings = new ArrayList<GPHandlerMapping>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doDispatcher(req, resp);
    }

    private void doDispatcher(HttpServletRequest req,HttpServletResponse resp){

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1.初始化ApplicationContext

        context = new GPApplicationContext(config.getInitParameter(CONTEXT_CONFIG_LOCATION));

        //2.初始化Spring MVC九大组件
        initStrategies(context);
    }

    //初始化策略
    protected void initStrategies(GPApplicationContext context){
        //多文件上传组件
        initMultipartResover(context);
        //初始化本地语言环境
        initLocaleResolver(context);
        //初始化模板处理器
        initThemeResolver(context);

        //handlerMapping
        initHandlerMappings(context);


        //初始化参数适配器
        initHandlerAdapters(context);
        //初始化异常拦截器
        initHandlerExceptionResolvers(context);
        //初始化视图预处理器
        initRequestToViewNameTranslator(context);


        //初始化视图转换器
        initViewResolvers(context);
        //参数缓存缓存器
        initFlashMapManager(context);
    }

    private void initFlashMapManager(GPApplicationContext context) {
    }

    private void initViewResolvers(GPApplicationContext context) {
    }

    private void initRequestToViewNameTranslator(GPApplicationContext context) {
    }

    private void initHandlerExceptionResolvers(GPApplicationContext context) {
    }

    private void initHandlerAdapters(GPApplicationContext context) {
    }

    private void initHandlerMappings(GPApplicationContext context) {
        //拿到扫描完所有的beanName
        String [] beanNames = context.getBeanDefinitionNames();

        try{

            for(String beanName : beanNames){

                Object controller = context.getBean(beanName);

                Class<?> clazz =  controller.getClass();

                if(!clazz.isAnnotationPresent(GPController.class)){continue;}

                String baseUrl = "";
                if(clazz.isAnnotationPresent(GPRequestMapping.class)){
                    GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                    baseUrl = requestMapping.value();
                }

                Method[] methods = clazz.getMethods();
                //扫描方法上的注解
                for (Method method : methods) {

                    if(!method.isAnnotationPresent(GPRequestMapping.class)){
                        continue;
                    }

                    GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);

                    String regex = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("\\*",".*")).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);

                    this.handlerMappings.add(new GPHandlerMapping(pattern,controller,method));
                    log.info("Mapped " + regex + "," + method);





                }


            }
        }catch (Exception e ){
            e.printStackTrace();
        }

    }

    private void initThemeResolver(GPApplicationContext context) {
    }

    private void initLocaleResolver(GPApplicationContext context) {
    }

    private void initMultipartResover(GPApplicationContext context) {
    }


}
