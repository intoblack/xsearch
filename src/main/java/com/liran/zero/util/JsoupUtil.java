package com.liran.zero.util;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {
	private JsoupUtil() {

	}

	public static String extractElementsString(Elements elements) {
		StringBuilder buffer = new StringBuilder();
		for (Element element : elements) {
			if (element.tag().getName().toLowerCase().equals("p")) {
				buffer.append("<p>" + element.text() + "</p>");
			} else {
				buffer.append(element.text());
			}
		}
		return buffer.toString();
	}

	public static String extractContent(String text) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '<') {
				int j = i + 1;
				while (j < text.length() && text.charAt(j) != '>') {
					j++;
				}
				if (text.charAt(j) == '>') {
					String tagname = text.substring(i, j + 1);
					if (tagname.startsWith("<br")) {
						buffer.append("<br>");

					} else if (tagname.startsWith("<p")) {
						buffer.append("<p>");
					}
					i = j;
				}
			} else {
				buffer.append(text.charAt(i));
			}
		}

		return buffer.toString().replace("&nbsp;", "");
	}
}
