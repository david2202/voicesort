package au.com.auspost.voicesort.web.filter;

import au.com.auspost.voicesort.domain.Device;
import au.com.auspost.voicesort.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeviceSecurityFilter implements Filter {
    private DeviceService deviceService;

    public DeviceSecurityFilter(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
            String url = httpRequest.getRequestURL().toString();
            if (url.endsWith("/register")
                    || url.endsWith("/healthCheck")
                    || url.contains("/css")
                    || url.contains("/scripts")
                    || url.contains("/images")
                    || url.contains("/fonts")) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                Cookie cookie = WebUtils.getCookie(httpRequest, "device");
                if (cookie != null) {
                    Device device = deviceService.load(cookie.getValue());
                    if (device != null) {
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    }
                }
            }
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().append("<html>" +
                    "<head><h1>Unauthorised</h1></head>" +
                    "<body><p>Your device is not registered to access this application</p></body>" +
                    "</html>");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
