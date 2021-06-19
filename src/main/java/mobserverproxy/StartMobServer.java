package mobserverproxy;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import logger.MainLogger;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.mitm.manager.ImpersonatingMitmManager;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

public class StartMobServer {

	 private BrowserMobProxy proxyServer;
	 private String proxyPort;
	
	public void startServer() {
		proxyServer  = new BrowserMobProxyServer();
		proxyServer.setTrustAllServers(true); 
		proxyServer.setMitmManager(ImpersonatingMitmManager.builder().trustAllServers(true).build());
		mock1();
		proxyServer.start(0);
		String proxyServerUrl = "http://localhost:"+getProxyServerPort();
		MainLogger.logger().info("proxyadd", proxyServerUrl);
		System.setProperty("proxy_address", proxyServerUrl);
	}
	
	public String getProxyServerPort() {
		proxyPort =  String.valueOf(proxyServer.getPort());
		return proxyPort;
	}
	
	public void stopServer() {
		proxyServer.stop();
	}
	
	
	public void mock1() {
//		proxyServer.addRequestFilter(new RequestFilter() {
//
//			@Override
//			public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents,
//					HttpMessageInfo messageInfo) {
//				 // if (true) {
//					// System.out.println("********************");
//				//	 System.out.println("********************");
//	              //  System.out.println(request.getUri());
//	                //System.out.println("********************");
//	                //System.out.println("********************");
//	                //}
//				  //System.out.println("Request filter called");
//				return null;
//			};
//			
//			
//		});
		
		proxyServer.addResponseFilter(new ResponseFilter() {
            @Override
            public void filterResponse(HttpResponse response, HttpMessageContents contents, HttpMessageInfo messageInfo) {
            	MainLogger.logger().info(messageInfo.getUrl());
               // if (messageInfo.getUrl().contains("hjkjjkjh")) {
                	//System.out.println(response.setStatus(new HttpResponseStatus(400, "Tanuj not found")));
                //	MainLogger.logger().info("*********** "+response.getStatus());
                  //  contents.setTextContents("Tanuj is inside the browser");
                    //MainLogger.logger().info("Rersponse filter called");
                //}
            }
           
        });
		
	}
	 
//	 
//	public static void main(String...a) {
//		StartMobServer server = new StartMobServer();
//		server.startServer();
//		System.out.println("Started ...");
//		System.out.println("Port is "+server.getProxyServerPort());
//	}
}
