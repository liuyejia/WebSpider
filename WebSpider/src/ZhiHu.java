import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class ZhiHu {
	public String question;// question
	 public String questionDescription;// question description
	 public String zhihuUrl;// URL of question in Zhihu
	 public ArrayList<String> answers;// store all answers of question
	 // initialize data
	 public ZhiHu(String url) {
	  
	  question = "";
	  questionDescription = "";
	  zhihuUrl = "";
	  answers = new ArrayList<String>();
	  //get URL for each questions
	  if (getRealUrl(url)) {
	   System.out.println("正在抓取" + zhihuUrl);
	   // get content of question page
	   String content = WebSpider.SendandGet(zhihuUrl);
	   Pattern pattern;
	   Matcher matcher;
	   // macth titile
	   pattern = Pattern.compile("zm-item-title.+?><span.+?>(.+?)</span>");
	   matcher = pattern.matcher(content);
	   if (matcher.find()) {
	    question = matcher.group(1);
	   }
	   // match question description
	   pattern = Pattern
	     .compile("zh-question-detail.+?<div.+?>(.*?)</div>");
	   matcher = pattern.matcher(content);
	   if (matcher.find()) {
	    questionDescription = matcher.group(1);
	   }
	   // match answers
	   pattern = Pattern.compile("/answer/content.+?<div.+?>(.*?)</div>");
	   matcher = pattern.matcher(content);
	   boolean isFind = matcher.find();
	   while (isFind) {
	    answers.add(matcher.group(1));
	    isFind = matcher.find();
	   }
	  }
	 }
	
	 public boolean getAll() {
	  return true;
	 }
	 // get URL of question page from original URL
	 boolean getRealUrl(String url) {
	  // e.g.transfer 'http://www.zhihu.com/question/22355264/answer/21102139'
	  // to 'http://www.zhihu.com/question/22355264'
	 
	  Pattern pattern = Pattern.compile("question/(.*?)/");
	  Matcher matcher = pattern.matcher(url);
	  if (matcher.find()) {
	   zhihuUrl = "http://www.zhihu.com/question/" + matcher.group(1);
	  } else {
	   return false;
	  }
	  return true;
	 }
	 @Override
	 public String toString() {
	  return "question：" + question + "\n" + "question description：" + questionDescription + "\n"
	    + "URL:：" + zhihuUrl + "\nanswers：" + answers.size() + "\n";
	 }
}
