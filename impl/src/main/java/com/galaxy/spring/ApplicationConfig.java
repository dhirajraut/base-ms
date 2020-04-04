package com.galaxy.spring;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.galaxy.spring")
@Configuration
public class ApplicationConfig {

	/**
	 * Create ModelMapper bean.
	 * 
	 * @return
	 */
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

	private Connector createHttpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);

		return connector;
	}
	

	/**
	 * Redirect HTTP requests to HTTPS.
	 * @return
	 */
	@Bean
	public TomcatServletWebServerFactory servletContainer() {
	    TomcatServletWebServerFactory tomcat =
	            new TomcatServletWebServerFactory() {

	                @Override
	                protected void postProcessContext(Context context) {
	                    SecurityConstraint securityConstraint = new SecurityConstraint();
	                    securityConstraint.setUserConstraint("CONFIDENTIAL");
	                    SecurityCollection collection = new SecurityCollection();
	                    collection.addPattern("/*");
	                    securityConstraint.addCollection(collection);
	                    context.addConstraint(securityConstraint);
	                }
	            };
	    tomcat.addAdditionalTomcatConnectors(createHttpConnector());
	    return tomcat;
	}
}
