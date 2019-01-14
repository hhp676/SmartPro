package code.utils;


import code.exception.EteException;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;
/**
 * 读取配置文件信息  <br />
 * create date at 2015年11月13日 下午2:08:47 <br />
 * modified by qiujingde at 2017-09-20 <br />
 *
 * @author markie
 */
public final class ConfigUtil {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ConfigUtil.class);
    private static Environment env;
    private static ApplicationContext ctx;

    static {
        ctx = initConf(EnvConfig.class);
    }

    private ConfigUtil() {}
	
	public static String getString(String key){
		return env.getRequiredProperty(key);
	}
	
    public static String getTrimString(String key) {
        return (getString(key));
    }

    public static boolean getBoolean(String key) {
        return env.getRequiredProperty(key, Boolean.TYPE);
    }

    public static int getInteger(String key) {
        return env.getRequiredProperty(key, Integer.TYPE);
    }

    public static long getLong(String key) {
        return env.getRequiredProperty(key, Long.TYPE);
    }

    public static String[] getStringArray(String key, String split) {
	    return getString(key).split("\\s*" + split + "\\s*");
    }

    public static Resource getResourceByKey(String key) {
	    return ctx.getResource(env.getRequiredProperty(key));
    }

    public static String getLocationByKey(String key) {
        return getResourceLocation(getString(key));
    }

    public static String getResourceLocation(String resource) {
        try {
            return ctx.getResource(resource).getFile().getAbsolutePath();
        } catch (IOException e) {
            throw new EteException(e);
        }
    }

    public static ApplicationContext initConf(Class<?> confClass) {
        try {
            return new AnnotationConfigApplicationContext(confClass);
        } catch (Exception e) {
            log.error(confClass.getCanonicalName() + "配置文件加载出错: " + e.getMessage(), e);
            throw new EteException(e);
        }
    }

    @Configuration
    @PropertySource({
            "classpath:jdbc.properties"})
    public static class EnvConfig {
        @javax.annotation.Resource
        private Environment env;

	    @Bean
        public Object methodInvoking() {
            ConfigUtil.env = env;
            return null;
        }
    }

}
