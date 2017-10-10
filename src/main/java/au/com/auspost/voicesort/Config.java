package au.com.auspost.voicesort;

import au.com.auspost.voicesort.dao.DeviceDao;
import au.com.auspost.voicesort.service.DeviceService;
import au.com.auspost.voicesort.web.filter.DeviceSecurityFilter;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableCaching(proxyTargetClass = true)
public class Config {
    @Value("${voicesort.deviceSecurityEnabled}")
    private Boolean deviceSecurityEnabled;

    @Autowired
    private DeviceService deviceService;

    @Bean
    public CacheManager cacheManager() {
        // configure and return an implementation of Spring's CacheManager SPI
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("devices")));
        return cacheManager;
    }
    @Bean
    public FilterRegistrationBean deviceSecurityFilterBean() {
        final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new DeviceSecurityFilter(deviceService));
        filterRegBean.addUrlPatterns("/*");
        filterRegBean.setEnabled(deviceSecurityEnabled);
        filterRegBean.setName("deviceSecurityFilter");
        filterRegBean.setAsyncSupported(Boolean.TRUE);
        return filterRegBean;
    }
}
