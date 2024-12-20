/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.accesspark.monitor.app.security;

import com.spiwer.rosilla.util.RequestUtil;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * @author AccessparkSoft
 */
@Component
@Order(0)
public class SecurityFilter implements Filter
{

 @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException
  {
    if (RequestUtil.CONNECTIONS_LIST == null || RequestUtil.CONNECTIONS_LIST.isEmpty()) {

    }
    chain.doFilter(request, response);
    RequestUtil.remove();
  }

}
