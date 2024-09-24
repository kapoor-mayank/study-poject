/*     */ package org.traccar.web;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.util.EnumSet;
/*     */ import javax.servlet.DispatcherType;
/*     */ import javax.servlet.Servlet;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.eclipse.jetty.http.HttpMethod;
/*     */ import org.eclipse.jetty.http.HttpStatus;
/*     */ import org.eclipse.jetty.proxy.AsyncProxyServlet;
/*     */ import org.eclipse.jetty.server.Handler;
/*     */ import org.eclipse.jetty.server.Request;
/*     */ import org.eclipse.jetty.server.Server;
/*     */ import org.eclipse.jetty.server.handler.ErrorHandler;
/*     */ import org.eclipse.jetty.server.handler.HandlerList;
/*     */ import org.eclipse.jetty.servlet.DefaultServlet;
/*     */ import org.eclipse.jetty.servlet.ServletContextHandler;
/*     */ import org.eclipse.jetty.servlet.ServletHolder;
/*     */ import org.glassfish.jersey.jackson.JacksonFeature;
/*     */ import org.glassfish.jersey.server.ResourceConfig;
/*     */ import org.glassfish.jersey.servlet.ServletContainer;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.api.AsyncSocketServlet;
/*     */ import org.traccar.api.CorsResponseFilter;
/*     */ import org.traccar.api.MediaFilter;
/*     */ import org.traccar.api.ObjectMapperProvider;
/*     */ import org.traccar.api.ResourceErrorHandler;
/*     */ import org.traccar.api.SecurityRequestFilter;
/*     */ import org.traccar.api.resource.ServerResource;
/*     */ import org.traccar.config.Config;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WebServer
/*     */ {
/*  54 */   private static final Logger LOGGER = LoggerFactory.getLogger(WebServer.class);
/*     */   
/*     */   private Server server;
/*     */ 
/*     */   
/*     */   private void initServer(Config config) {
/*  60 */     String address = config.getString("web.address");
/*  61 */     int port = config.getInteger("web.port", 8082);
/*  62 */     if (address == null) {
/*  63 */       this.server = new Server(port);
/*     */     } else {
/*  65 */       this.server = new Server(new InetSocketAddress(address, port));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public WebServer(Config config) {
/*  71 */     initServer(config);
/*     */     
/*  73 */     ServletContextHandler servletHandler = new ServletContextHandler(1);
/*     */     
/*  75 */     int sessionTimeout = config.getInteger("web.sessionTimeout");
/*  76 */     if (sessionTimeout > 0) {
/*  77 */       servletHandler.getSessionHandler().setMaxInactiveInterval(sessionTimeout);
/*     */     }
/*     */     
/*  80 */     initApi(config, servletHandler);
/*     */     
/*  82 */     if (config.getBoolean("web.console")) {
/*  83 */       servletHandler.addServlet(new ServletHolder((Servlet)new ConsoleServlet()), "/console/*");
/*     */     }
/*     */     
/*  86 */     initWebApp(config, servletHandler);
/*     */     
/*  88 */     servletHandler.setErrorHandler(new ErrorHandler()
/*     */         {
/*     */           protected void handleErrorPage(HttpServletRequest request, Writer writer, int code, String message) throws IOException
/*     */           {
/*  92 */             writer.write("<!DOCTYPE<html><head><title>Error</title></head><html><body>" + code + " - " + 
/*  93 */                 HttpStatus.getMessage(code) + "</body></html>");
/*     */           }
/*     */         });
/*     */     
/*  97 */     HandlerList handlers = new HandlerList();
/*  98 */     initClientProxy(config, handlers);
/*  99 */     handlers.addHandler((Handler)servletHandler);
/* 100 */     this.server.setHandler((Handler)handlers);
/*     */   }
/*     */   
/*     */   private void initClientProxy(Config config, HandlerList handlers) {
/* 104 */     int port = config.getInteger("osmand.port");
/* 105 */     if (port != 0) {
/* 106 */       ServletContextHandler servletHandler = new ServletContextHandler()
/*     */         {
/*     */           
/*     */           public void doScope(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
/*     */           {
/* 111 */             if (target.equals("/") && request.getMethod().equals(HttpMethod.POST.asString())) {
/* 112 */               super.doScope(target, baseRequest, request, response);
/*     */             }
/*     */           }
/*     */         };
/* 116 */       ServletHolder servletHolder = new ServletHolder((Servlet)new AsyncProxyServlet.Transparent());
/* 117 */       servletHolder.setInitParameter("proxyTo", "http://localhost:" + port);
/* 118 */       servletHandler.addServlet(servletHolder, "/");
/* 119 */       handlers.addHandler((Handler)servletHandler);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void initWebApp(Config config, ServletContextHandler servletHandler) {
/* 124 */     ServletHolder servletHolder = new ServletHolder(DefaultServlet.class);
/* 125 */     servletHolder.setInitParameter("resourceBase", (new File(config.getString("web.path"))).getAbsolutePath());
/* 126 */     if (config.getBoolean("web.debug")) {
/* 127 */       servletHandler.setWelcomeFiles(new String[] { "debug.html", "index.html" });
/*     */     } else {
/* 129 */       String cache = config.getString("web.cacheControl");
/* 130 */       if (cache != null && !cache.isEmpty()) {
/* 131 */         servletHolder.setInitParameter("cacheControl", cache);
/*     */       }
/* 133 */       servletHandler.setWelcomeFiles(new String[] { "release.html", "index.html" });
/*     */     } 
/* 135 */     servletHandler.addServlet(servletHolder, "/*");
/*     */   }
/*     */   
/*     */   private void initApi(Config config, ServletContextHandler servletHandler) {
/* 139 */     servletHandler.addServlet(new ServletHolder((Servlet)new AsyncSocketServlet()), "/api/socket");
/*     */     
/* 141 */     if (config.hasKey("media.path")) {
/* 142 */       ServletHolder servletHolder = new ServletHolder(DefaultServlet.class);
/* 143 */       servletHolder.setInitParameter("resourceBase", (new File(config.getString("media.path"))).getAbsolutePath());
/* 144 */       servletHolder.setInitParameter("dirAllowed", config.getString("media.dirAllowed", "false"));
/* 145 */       servletHolder.setInitParameter("pathInfoOnly", "true");
/* 146 */       servletHandler.addServlet(servletHolder, "/api/media/*");
/* 147 */       servletHandler.addFilter(MediaFilter.class, "/api/media/*", EnumSet.allOf(DispatcherType.class));
/*     */     } 
/*     */     
/* 150 */     ResourceConfig resourceConfig = new ResourceConfig();
/* 151 */     resourceConfig.registerClasses(new Class[] { JacksonFeature.class, ObjectMapperProvider.class, ResourceErrorHandler.class });
/* 152 */     resourceConfig.registerClasses(new Class[] { SecurityRequestFilter.class, CorsResponseFilter.class });
/* 153 */     resourceConfig.packages(new String[] { ServerResource.class.getPackage().getName() });
/* 154 */     servletHandler.addServlet(new ServletHolder((Servlet)new ServletContainer(resourceConfig)), "/api/*");
/*     */   }
/*     */   
/*     */   public void start() {
/*     */     try {
/* 159 */       this.server.start();
/* 160 */     } catch (Exception error) {
/* 161 */       LOGGER.warn("Web server start failed", error);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void stop() {
/*     */     try {
/* 167 */       this.server.stop();
/* 168 */     } catch (Exception error) {
/* 169 */       LOGGER.warn("Web server stop failed", error);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\web\WebServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */