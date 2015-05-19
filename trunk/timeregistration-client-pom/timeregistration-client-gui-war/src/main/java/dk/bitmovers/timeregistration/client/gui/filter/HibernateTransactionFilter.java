package dk.bitmovers.timeregistration.client.gui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class HibernateTransactionFilter implements Filter {

	Logger logger = LoggerFactory.getLogger(getClass());
	SessionFactory sessionFactory;

	public HibernateTransactionFilter() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		boolean doCommit = false;
		try {
			//			Session session = sessionFactory.getCurrentSession();
			//
			//			if (!session.getTransaction().isActive())
			//				session.getTransaction().begin();

			chain.doFilter(req, resp);
			doCommit = true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			//			doCommit = false;
		} finally {
			Session session = sessionFactory.getCurrentSession();
			if (session != null) {
				if (doCommit) {
					if (session.getTransaction().isActive())
						session.getTransaction().commit();
						session.flush();
				} else {
					session.getTransaction().rollback();
				}

			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException { // TODO Auto-generated method stub
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(arg0.getServletContext());
		sessionFactory = ((SessionFactory) wac.getBean("sessionFactory", SessionFactory.class));
	}

}
