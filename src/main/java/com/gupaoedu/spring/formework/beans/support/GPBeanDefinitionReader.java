package com.gupaoedu.spring.formework.beans.support;

import com.gupaoedu.spring.formework.beans.config.GPBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 加载配置文件的关键类
 */
public class GPBeanDefinitionReader {

    private String[] location;

    private Properties config = new Properties();

    private final String SCAN_PACKAGE = "scanPackage";

    //存放类名
    private List<String> registyBeanClasses = new ArrayList<String>();

    public GPBeanDefinitionReader(String... location){
        this.location = location;
        //定位URL路径找到配置文件并将其加载到Properties对象中.getClassLoader()
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location[0].replace("classpath:",""));
        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != is ){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //解析配置文件
        doSacnner(config.getProperty(SCAN_PACKAGE));
    }

    private void doSacnner(String scanPackage) {
        //转换为文件路径,实际上就是把.替换为/.getClassLoader()
        URL url  = this.getClass().getResource("/"+scanPackage.replaceAll("\\.","/"));
        String filePath = url.getFile();
        File classPath = new File(filePath);
        for (File file : classPath.listFiles()) {
            if(file.isDirectory()){//如果是文件夹,递归调用
                doSacnner(scanPackage+"."+file.getName());
            }else{
                if(!file.getName().endsWith(".class")){continue;}

                String className = (scanPackage + "." +file.getName()).replace(".class","");
                registyBeanClasses.add(className);

            }
            
        }

    }

    public Properties getConfig(){
        return  this.config;
    }

    //spring返回的是int类型，参数为String... location 之后的处理才会返回List,
    //这里为了简化设计就直接返回list,并把参数放入构造方法
    public List<GPBeanDefinition> loadBeanDefintions(){

        List<GPBeanDefinition> result = new ArrayList<GPBeanDefinition>();
        try{
            for (String className : registyBeanClasses) {

                Class<?> beanClass = Class.forName(className);

                //如果是接口直接返回,接口不能被实例化
                if(beanClass.isInterface()){ continue; }


                //封装beanDefintion
                GPBeanDefinition beanDefinition = doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()),className);

                result.add(beanDefinition);

                for (Class<?> classInterface : beanClass.getInterfaces()) {
                    //如果是多个实现类，只能覆盖
                    //为什么？因为Spring没那么智能，就是这么傻
                    //这个时候，可以自定义名字
                    result.add(doCreateBeanDefinition(classInterface.getName(),beanClass.getName()));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 创建GPBeanDefintion
     * @param factoryBeanName 简单类名首字母小写
     * @param beanClassName 全类名
     * @return GPBeanDefinition
     */
    private GPBeanDefinition doCreateBeanDefinition(String factoryBeanName,String beanClassName) {

            GPBeanDefinition beanDefinition = new GPBeanDefinition();
            beanDefinition.setBeanClassName(beanClassName);
            beanDefinition.setFactoryBeanName(factoryBeanName);
            return beanDefinition;

    }

    private String toLowerFirstCase(String className) {
        char[] chars = className.toCharArray();
        //取首字母将其ASCII码＋32就是小写字母
        chars[0] += 32;

        return String.valueOf(chars);
    }
}
