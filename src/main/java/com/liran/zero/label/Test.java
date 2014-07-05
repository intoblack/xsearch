package com.liran.zero.label;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

public class Test {

	public interface IFileDo {
		public void readline(String line);
	}

	private IFileDo fileDo = null;

	public Test(IFileDo fileDO) {
		this.fileDo = fileDO;
	}

	public boolean readFile(File file) {
		boolean status = true;
		FileReader reader = null;
		BufferedReader br = null;
		try {
			reader = new FileReader(file);
			br = new BufferedReader(reader);

			String line = "";

			while ((line = br.readLine()) != null) {
				fileDo.readline(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return status;
	}

	public static class FileDo implements IFileDo {

		private Map<String, Integer> wordCout = new HashMap<String, Integer>();
		private int window = 8;

		public void readline(String line) {
			if (line != null && line.length() != 0) {
				List<Term> terms = NlpAnalysis.parse(line);
				for (int i = 0; i < (terms.size() - 1); i++) {
					for (int j = i + 1; j < terms.size() && j < (i + window); j++) {
						if (isGoodLabel(terms.get(i), terms.get(j))
								|| isGoodLabel(terms.get(j), terms.get(i))) {
							String word = terms.get(i) + " " + terms.get(j);
							if (!wordCout.containsKey(word)) {
								wordCout.put(word, 0);
							}
							wordCout.put(word, wordCout.get(word) + 1);
						}
					}
				}
			}

		}

		private boolean isGoodLabel(Term term1, Term term2) {
			return term1.getNatureStr().startsWith("n")
					&& !term1.getNatureStr().equals("null")
					&& term2.getNatureStr().startsWith("a");
		}

		public List<Map.Entry<String, Integer>> order() {
			List<Map.Entry<String, Integer>> wordEntry = new ArrayList<Map.Entry<String, Integer>>(
					wordCout.entrySet());
			Collections.sort(wordEntry,
					new Comparator<Map.Entry<String, Integer>>() {

						public int compare(Entry<String, Integer> o1,
								Entry<String, Integer> o2) {
							return o2.getValue().compareTo(o1.getValue());
						}
					});
			return wordEntry;

		}

	}

	public static void main(String[] args) {
		FileDo fileDO = new FileDo();
		Test t = new Test(fileDO);
		t.readFile(new File("/home/lixuze/phone.txt"));
		System.out.println(fileDO.order());
		NlpAnalysis.parse("a");
	}
}
