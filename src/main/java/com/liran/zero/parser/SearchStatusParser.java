package com.liran.zero.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.sf.json.JSONObject;

import com.liran.zero.http.ZeroResponse;
import com.liran.zero.util.FileUtil;
import com.liran.zero.util.StringUtil;
import com.liran.zero.xsearch.exception.ZeroException;

public class SearchStatusParser implements IParser<String> {

	private static final Pattern pattern = Pattern
			.compile("STK.pageletM.view\\(\\{\"pid\":\"pl_weibo_feedlist[\\s\\S]*?</script>");

	public String parser(String html) {
		Matcher matcher = pattern.matcher(html);

		String searchStatusString = null;
		if (matcher.find()) {
			searchStatusString = matcher.group();
			if (!StringUtil.isEmpty(searchStatusString)) {
				JSONObject searchStatusJson = JSONObject
						.fromObject(searchStatusString.substring(
								searchStatusString.indexOf("{"),
								searchStatusString.indexOf("}") + 1));
				String searchStatusHtml = searchStatusJson.getString("html");
				Document doc = Jsoup.parse(searchStatusHtml);
				Elements statusElments = doc.select("[class=feed_list]");
				for (Element statuElment : statusElments) {
					System.out.println(parserStatus(statuElment));
				}
			}
		}

		return null;
	}

	public JSONObject parserStatus(Element statuEelement) {
		JSONObject json = new JSONObject();
		json.put("mid", statuEelement.attr("mid"));
		json.put("text", statuEelement.select("[class=content] > p > em")
				.text());
		Elements statusInfoElement = statuEelement
				.select("[class=content] > [class^=info]");

		List<String> pics = new ArrayList<String>();
		for (Element picElement : statuEelement
				.select("[class=content] > [class=piclist]")) {
			pics.add(picElement.attr("src"));
		}
		json.put("pics", pics);
		String statusInfoArry[] = statusInfoElement.select("span").text()
				.split("\\|");
		json.put("commends_count", parserStatusInfo(statusInfoArry[0]));
		json.put("reposts_count", parserStatusInfo(statusInfoArry[1]));
		json.put("comments_count", parserStatusInfo(statusInfoArry[3]));
		json.put("date", statusInfoElement.select("[class=date]").attr("date"));
		json.put("source", statusInfoElement.select("[rel=nofollow]").text());
		return json;
	}

	public int parserStatusInfo(String info) {
		int count = 0;
		String infoArry[] = info.split("\\(");
		if (infoArry.length == 2) {
			count = Integer.parseInt(infoArry[1].split("\\)")[0]);
		}
		return count;

	}

	public static void main(String[] args) throws ZeroException {
		SearchStatusParser status = new SearchStatusParser();
		System.out.println(status.parser(StringUtil.wordListToString(
				FileUtil.readFileData("/home/lixuze/search.html"), "\r\n")));
	}

	public boolean assertResponse(ZeroResponse response) {
		return false;
	}

}
