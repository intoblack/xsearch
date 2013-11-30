package com.liran.zero.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;



import com.liran.zero.fetcher.ZeroFetcherException;
import com.liran.zero.util.FileUtil;
import com.liran.zero.util.StringUtil;

public class SearchStatusParser implements IParser<String> {

	private static final Pattern pattern = Pattern
			.compile("STK.pageletM.view\\(\\{\"pid\":\"pl_weibo_feedlist[\\s\\S]*?</script>");

	public String parser(String html) {
		Matcher matcher = pattern.matcher(html);

		String searchStatusString = null;
		if (matcher.find()) {
			searchStatusString = matcher.group();
			if(!StringUtil.isEmpty(searchStatusString))
			{
				JSONObject searchStatusJson = JSONObject.fromObject(searchStatusString.substring(searchStatusString.indexOf("{"), searchStatusString.indexOf("}")+1));
				String searchStatusHtml = searchStatusJson.getString("html");
				System.out.println(searchStatusHtml);
			
			}
		}

		return null ;
	}

	public static void main(String[] args) throws ZeroFetcherException {
		SearchStatusParser status = new SearchStatusParser();
		System.out.println(status.parser(StringUtil.wordListToString(FileUtil.readFileData("/home/lixuze/search.html"),"\r\n")));
	}

}
