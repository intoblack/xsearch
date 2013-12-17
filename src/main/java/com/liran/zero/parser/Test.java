package com.liran.zero.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Test {
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://www.baidu.com").get();
		Elements newsHeadlines = doc.select("#nv > a");
		System.out.println(newsHeadlines.text());
		
	}
}
