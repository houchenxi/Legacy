package com.haotwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
  
public class TestPost {   
  
    public static void testPost() throws IOException {   
  
    	/*1*/
//    	String url = "https://selfsolve.apple.com/wcResults.do";
    	String url = "http://passport.sogou-inc.com/SSO";
    	String url1 = "http://stats.sogou-inc.com";
    	 
    	HttpClient client = HttpClientBuilder.create().build();
    	
    	HttpPost post = new HttpPost(url);
    	
    	// add header
    	post.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.19 (KHTML, like Gecko)");
     
    	List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    	urlParameters.add(new BasicNameValuePair("username", "houchenxi"));
    	urlParameters.add(new BasicNameValuePair("password", "Octoberm1ma"));
    	urlParameters.add(new BasicNameValuePair("expireDay", "14"));
    	urlParameters.add(new BasicNameValuePair("target", "http://stats.sogou-inc.com:80/"));
    	urlParameters.add(new BasicNameValuePair("shire", "http://stats.sogou-inc.com:80/shire.jp"));
    	urlParameters.add(new BasicNameValuePair("Connection", "keep-alive"));
    	
    	post.setEntity(new UrlEncodedFormEntity(urlParameters));
     
    	HttpResponse response = client.execute(post);
    	System.out.println("Response Code : " 
                    + response.getStatusLine().getStatusCode());
     
    	BufferedReader rd = new BufferedReader(
    	        new InputStreamReader(response.getEntity().getContent()));
     
    	StringBuffer result = new StringBuffer();
    	String line = "";
    	while ((line = rd.readLine()) != null) {
    		result.append(line);
    	}
    	
    	System.out.println("page content:\n" + result.toString());
  
       	Header[] headers = response.getAllHeaders();//.getHeaders("Set-Cookie");
       	System.out.println("Headers:");
       	int i = 0;
       	String cookie = "";
       	for(Header header : headers)
       	{
       		System.out.println("(" +  (++i)  + ")" + header.getName() + ":" + header.getValue().toString());
       		if(header.getName().equals("SetCookie"))
       		{
       			cookie = header.getValue();
       		}
       	}
  
    	
    	
    	/*2*/
    	
    	HttpPost post1 = new HttpPost( "http://stats.sogou-inc.com:80/shire.jp" );
    	
    	urlParameters = new ArrayList<NameValuePair>();
     	urlParameters.add(new BasicNameValuePair("samlResponse", "PFJlc3BvbnNlIHhtbG5zPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoxLjA6cHJvdG9jb2wiIHhtbG5zOnNhbWw9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjEuMDphc3NlcnRpb24iIHhtbG5zOnNhbWxwPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoxLjA6cHJvdG9jb2wiIHhtbG5zOnhzZD0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiIHhtbG5zOnhzaT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiIElzc3VlSW5zdGFudD0iMjAxNC0xMS0wN1QxMjoxNjo0OC40NTBaIiBNYWpvclZlcnNpb249IjEiIE1pbm9yVmVyc2lvbj0iMSIgUmVzcG9uc2VJRD0iY2pwYWJsbW1hZ2RsbWpub2RmbmtoaGhjYWVwa2VtZmciPjxkczpTaWduYXR1cmUgeG1sbnM6ZHM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyMiPgo8ZHM6U2lnbmVkSW5mbz4KPGRzOkNhbm9uaWNhbGl6YXRpb25NZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzEwL3htbC1leGMtYzE0biMiPjwvZHM6Q2Fub25pY2FsaXphdGlvbk1ldGhvZD4KPGRzOlNpZ25hdHVyZU1ldGhvZCBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyNyc2Etc2hhMSI+PC9kczpTaWduYXR1cmVNZXRob2Q+CjxkczpSZWZlcmVuY2UgVVJJPSIjY2pwYWJsbW1hZ2RsbWpub2RmbmtoaGhjYWVwa2VtZmciPgo8ZHM6VHJhbnNmb3Jtcz4KPGRzOlRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyNlbnZlbG9wZWQtc2lnbmF0dXJlIj48L2RzOlRyYW5zZm9ybT4KPGRzOlRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMTAveG1sLWV4Yy1jMTRuIyI+PGVjOkluY2x1c2l2ZU5hbWVzcGFjZXMgeG1sbnM6ZWM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMTAveG1sLWV4Yy1jMTRuIyIgUHJlZml4TGlzdD0iY29kZSBkcyBraW5kIHJ3IHNhbWwgc2FtbHAgdHlwZW5zICNkZWZhdWx0IHhzZCB4c2kiPjwvZWM6SW5jbHVzaXZlTmFtZXNwYWNlcz48L2RzOlRyYW5zZm9ybT4KPC9kczpUcmFuc2Zvcm1zPgo8ZHM6RGlnZXN0TWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnI3NoYTEiPjwvZHM6RGlnZXN0TWV0aG9kPgo8ZHM6RGlnZXN0VmFsdWU+Y0JtYkJ6RXhXaFlFMEZtck9GYlR4bFpBR3hVPTwvZHM6RGlnZXN0VmFsdWU+CjwvZHM6UmVmZXJlbmNlPgo8L2RzOlNpZ25lZEluZm8+CjxkczpTaWduYXR1cmVWYWx1ZT4KQ1o3a1pJZEIzZ3VuT3lyZERHUVhOS25OampwUUFkc283QVRjNjNMOU5EZlE0ckE4OU9za2d4c2l3Ry92WXJac2IvVlNrVHZtSFJGdgpnWEd3OTF4YWRERm81UWF6OUlKSy91bUs2aW5GcCtTamxzVmtoODlIb014ajk0TFlBZGRLMVVJR2pwU29STDBlc0loNVF2UjhnUVRGCkxCMFdiQkhQeTJKME14bm1yd3c9CjwvZHM6U2lnbmF0dXJlVmFsdWU+CjwvZHM6U2lnbmF0dXJlPjxTdGF0dXM+PFN0YXR1c0NvZGUgVmFsdWU9InNhbWxwOlN1Y2Nlc3MiPjwvU3RhdHVzQ29kZT48L1N0YXR1cz48QXNzZXJ0aW9uIHhtbG5zPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoxLjA6YXNzZXJ0aW9uIiBBc3NlcnRpb25JRD0iam5tbmVibmRra2Zla25vZWRqYWprcG9nbWZoYnBhaGYiIElzc3VlSW5zdGFudD0iMjAxNC0xMS0wN1QxMjoxNjo0OC40NTBaIiBJc3N1ZXI9Imh0dHA6Ly9wYXNzcG9ydC5zb2dvdS1pbmMuY29tIiBNYWpvclZlcnNpb249IjEiIE1pbm9yVmVyc2lvbj0iMSI+PENvbmRpdGlvbnMgTm90QmVmb3JlPSIyMDE0LTExLTA3VDEyOjE1OjQ4LjQ1MFoiIE5vdE9uT3JBZnRlcj0iMjAxNC0xMS0yMVQxMjoxNjo0OC40NTBaIj48L0NvbmRpdGlvbnM+PEF1dGhlbnRpY2F0aW9uU3RhdGVtZW50IEF1dGhlbnRpY2F0aW9uSW5zdGFudD0iMjAxNC0xMS0wN1QxMjoxNjo0OC40NTBaIiBBdXRoZW50aWNhdGlvbk1ldGhvZD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6MS4wOmFtOnBhc3N3b3JkIj48U3ViamVjdD48TmFtZUlkZW50aWZpZXI+aG91Y2hlbnhpQHNvZ291LWluYy5jb208L05hbWVJZGVudGlmaWVyPjwvU3ViamVjdD48L0F1dGhlbnRpY2F0aW9uU3RhdGVtZW50PjwvQXNzZXJ0aW9uPjwvUmVzcG9uc2U+"));
     	urlParameters.add(new BasicNameValuePair("target", "http://stats.sogou-inc.com:80/"));
     	urlParameters.add(new BasicNameValuePair("userName", "houchenxi@sogou-inc.com"));
    	urlParameters.add(new BasicNameValuePair("Connection", "keep-alive"));
    	
    	post1.addHeader("Cookie",cookie);
     	
     	post1.setEntity(new UrlEncodedFormEntity(urlParameters));

    	response = client.execute(post1);
    	System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());
    	rd = new BufferedReader(
    	        new InputStreamReader(response.getEntity().getContent()));
     
    	result = new StringBuffer();
    	line = "";
    	while ((line = rd.readLine()) != null) {
    		result.append(line);
    	}
       	System.out.println("page content(" + result.length() + "):\n" + result.toString());
       	
       	headers = response.getAllHeaders();//.getHeaders("Set-Cookie");
       	System.out.println("Headers:");
       	i = 0;
       	for(Header header : headers)
       	{
       		System.out.println("(" +  (++i)  + ")" + header.getName() + ":" + header.getValue().toString());
       	}
       	
       	

       	/* 3 */
		String content = null;
		try {
			// 定义HttpClient
			// 实例化HTTP方法
			HttpGet request = new HttpGet();
			
			request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			request.setHeader("Accept-Encoding", "gzip, deflate");
			request.setHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
			request.setHeader("Connection", "keep-alive");
			request.setHeader("Host", "stats.sogou-inc.com");
			request.setHeader("Referer", "http://passport.sogou-inc.com/?shire=http://stats.sogou-inc.com:80/shire.jp&target=http://stats.sogou-inc.com:80/index.php");
//			request.setHeader("Cookie", "PHPSESSID=gifairkvm2jhe8kndb6cor7em4; jpassport-sp={username:houchenxi@sogou-inc.com,timebefore:2014-11-08T05:43:46.842Z,notonorafter:2014-11-22T05:44:46.842Z,sign:eJwdj7kNwDAMxFbS/5SyLO0/Uox0VxAHkm8GBRxjlkq65k5hvqswEhxjyBFX0yRbunlEh8r0nEi5HfxQqVZCDOtiPyBeg+tKKScadotJMrspIc702ZG8K3u4SxAhpODWlbXlvAFei1hvREICeo/KqWkODRU0qgx0ExSaXlR6MkUWww557THCkq7tN58F6LOdOvree0f");
//			request.setHeader("Cookie", "PHPSESSID=gifairkvm2jhe8kndb6cor7em4; jpassport-sp={username:houchenxi@sogou-inc.com,timebefore:2014-11-08T05:43:46.842Z,notonorafter:2014-11-22T05:44:46.842Z,sign:eJwdj7kNwDAMxFbS/5SyLO0/Uox0VxAHkm8GBRxjlkq65k5hvqswEhxjyBFX0yRbunlEh8r0nEi5HfxQqVZCDOtiPyBeg+tKKScadotJMrspIc702ZG8K3u4SxAhpODWlbXlvAFei1hvREICeo/KqWkODRU0qgx0ExSaXlR6MkUWww557THCkq7tN58F6LOdOvree0f");
			
			headers = request.getAllHeaders();
	       	System.out.println("Request Headers:");
	       	i = 0;
	       	for(Header header : headers)
	       	{
	       		System.out.println("(" +  (++i)  + ")" + header.getName() + ":" + header.getValue().toString());
	       	}
	       	
			try {
				request.setURI(new URI("http://stats.sogou-inc.com:80/"));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
			HttpResponse resp = client.execute(request);
			
	       	headers = resp.getAllHeaders();//.getHeaders("Set-Cookie");
	       	System.out.println("Headers:");
	       	i = 0;
	       	for(Header header : headers)
	       	{
	       		System.out.println("(" +  (++i)  + ")" + header.getName() + ":" + header.getValue().toString());
	       	}

	    	System.out.println("Response Code : " 
	                + resp.getStatusLine().getStatusCode());
	    	rd = new BufferedReader(
	    	        new InputStreamReader(resp.getEntity().getContent()));
	     
	    	result = new StringBuffer();
	    	line = "";
	    	while ((line = rd.readLine()) != null) {
	    		result.append(line);
	    	}
	       	System.out.println("page content(" + result.length() + "):\n" + result.toString());
	 } finally {
		}
	}

       		

  
    public static void main(String[] args) throws IOException {   
  
        testPost();   
  
    }   
  
}  


