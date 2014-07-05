package com.liran.zero.spider;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.liran.zero.fetcher.WebFetcher;
import com.liran.zero.http.RequestParams;
import com.liran.zero.http.ZeroResponse;
import com.liran.zero.xsearch.exception.ZeroException;

public class JDComment {

	WebFetcher fetcher = new WebFetcher();

	private List<String> getJDCommentByPage(String itemid, int page)
			throws ZeroException {
		List<String> comments = new ArrayList<String>();
		RequestParams request = new RequestParams("http://club.jd.com/review/"
				+ itemid + "-0-" + page + "-0.html");
		ZeroResponse html = fetcher.getHtml(request);
		Document doc = Jsoup.parse(html.getContent());
		if (doc != null) {
			doc.getElementsByClass("comment-content").select("dl").get(1);
			for (Element comment : doc.getElementsByClass("comment-content").select("dl")) {
				if(comment.select("dt").text().trim().startsWith("心得"))
				{
					comments.add(comment.select("dt").get(1).select("dd").text());
				}
			
			}
		}
		return comments;
	}

	public List<String> getJDComment(String itemid) throws ZeroException {
		List<String> comments = new ArrayList<String>();
		for (int i = 1;; i++) {
			List<String> pageComment = getJDCommentByPage(itemid, i);
			System.out.println(i);
			if (pageComment.size() == 0) {
				break;
			}
			comments.addAll(pageComment);
		}
		return comments;
	}

	public static void main(String[] args) throws ZeroException {
		JDComment c = new JDComment();
		System.out.println(c.getJDComment("1006105"));
	}

}
