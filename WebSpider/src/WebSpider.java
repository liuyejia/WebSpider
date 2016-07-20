import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.regex.*;

public class WebSpider {

	static String SendandGet(String url)
	{
		String res="";
		 BufferedReader in=null;
		 try{
			 URL rUrl=new URL(url);
			 URLConnection conn=rUrl.openConnection();
			 conn.connect();
			 in=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			 String line;
			 while((line=in.readLine())!=null){
				 res+=line;
			 }
			 
		 }catch(Exception e)
		 {
			 System.out.println("Exception"+e);
			 e.printStackTrace();
		 }
		 finally{
			 try{
				 if(in!=null)
					 in.close();
			 }
			 catch(Exception e2){
				 e2.printStackTrace();
			 }
		 }
		 return res;
		 }
	// get recommendations from www.zhihu.com
	 static ArrayList<ZhiHu> GetRecommendations(String content) {
	  //store results
	  ArrayList<ZhiHu> results = new ArrayList<ZhiHu>();
	  // match URL of questions
	  Pattern pattern = Pattern
	    .compile("<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>");
	  Matcher matcher = pattern.matcher(content);
	  // match or not
	  Boolean isFind = matcher.find();
	  while (isFind) {
	   // *****store content in a ZhiHu object
	   ZhiHu zhihuTemp = new ZhiHu(matcher.group(1));
	   // add ZhiHu object
	   results.add(zhihuTemp);
	   // find next ZhiHu object
	   isFind = matcher.find();
	  }
	  return results;
	 }
	public static void main(String[] args)
	{
		String url = "http://www.zhihu.com/explore/recommendations";
		  // access URL
		  String content = WebSpider.SendandGet(url);
		  // get recommendation
		  ArrayList<ZhiHu> myZhihu = WebSpider.GetRecommendations(content);
		  // print Web Spider's results
		  System.out.println(myZhihu);	 
	}
}
