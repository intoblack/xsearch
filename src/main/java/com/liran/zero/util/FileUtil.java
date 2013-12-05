package com.liran.zero.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.liran.zero.exception.ZeroFetcherException;

public class FileUtil {

	/**
	 * 是否存在文件
	 * 
	 * @param file
	 *            文件对象
	 * @return 存在 true 不存在false
	 */
	public static boolean isExistFile(File file) {
		return file != null && file.isFile() && file.exists();
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 *            文件对象
	 * @return 存在 返回删除成功与否 否则 返回false
	 */
	public static boolean rmFile(File file) {
		return isExistFile(file) ? file.delete() : false;
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 *            文件路径字符串
	 * @return 存在 返回删除成功与否 否则 返回false
	 */
	public static boolean rmFile(String file) {
		return rmFile(new File(file));
	}

	/**
	 * 
	 * 是否存在文件
	 * 
	 * @param filenae
	 *            文件路径字符串
	 * @return 返回文件是否存在
	 * @throws ZeroFetcherException
	 */
	public static boolean isExistFile(String filename) {
		if (StringUtil.isEmpty(filename)) {
			throw new IllegalArgumentException("文件路径为空!");
		}
		return isExistFile(new File(filename));
	}

	public static boolean isExistFolder(File pathFile) {
		return pathFile != null && pathFile.isDirectory() && pathFile.exists();
	}

	public static boolean isExistFolder(String path) {
		return isExistFolder(new File(path));
	}

	/**
	 * 功能:获得一个路径下的所有文件 思想:递归 附加:无
	 * 
	 * @param path
	 *            字符串 要获得路径的文件
	 * @return
	 */
	public static Set<File> folderWalk(String path) {
		File folder = new File(path);
		Set<File> fileList = new HashSet<File>();
		for (File file : folder.listFiles()) {
			if (file.isDirectory()) {
				fileList.addAll(folderWalk(file.getAbsolutePath()));
			} else {
				fileList.add(file);
			}
		}
		return fileList;
	}

	/**
	 * 递归文件路径
	 * 
	 * @param rootFile
	 * @return
	 */
	public static Set<File> folderWalk(File rootFile) {
		Set<File> fileList = new HashSet<File>();
		for (File file : rootFile.listFiles()) {
			if (file.isDirectory()) {
				fileList.addAll(folderWalk(file));
			} else {
				fileList.add(file);
			}
		}
		return fileList;
	}

	/**
	 * @functionname public static List<String> readFileData(File file) throws
	 *               ZooPoolException;
	 * 
	 * @function:：读取文件 函数思想： 文件读取
	 * 
	 * 
	 * @param file
	 * @return
	 * @throws ZooPoolException
	 */
	public static List<String> readFileData(File file)
			throws ZeroFetcherException {
		List<String> fileDataList = new ArrayList<String>();
		BufferedReader fileReader = null;
		try {
			fileReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String data = null;
			while ((data = fileReader.readLine()) != null) {
				fileDataList.add(data);
			}
		} catch (Exception e) {
			throw new ZeroFetcherException("FILE_READ_FAILED", e);
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					throw new ZeroFetcherException("FILE_CLOSE_FAILED", e);
				}
			}

		}
		return fileDataList;
	}

	/**
	 * @functionname public static List<String> readFileData(String filePath)
	 *               throws ZooPoolException；
	 * 
	 * @function: 文件读取
	 * 
	 * @param filePath
	 * @return
	 * @throws ZooPoolException
	 */
	public static List<String> readFileData(String filePath)
			throws ZeroFetcherException {
		File file = new File(filePath);
		return readFileData(file);
	}

	public static List<String> readFileData(InputStream input)
			throws IOException {
		List<String> contents = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		String line = null;
		while (br.ready()) {
			line = br.readLine().trim();
			if (line != null && line.isEmpty() != true) { // && line.length()>1
				contents.add(line);
			}
		}
		br.close();
		return contents;
	}

	/**
	 * @functionname public static long getElementString(String word, String
	 *               splitString)；
	 * @function:： 字符串 word ，在分割符下切分之后数组长度
	 * 
	 * @param word
	 * @param splitString
	 * @return
	 */
	public static int getElementString(String word, String splitString) {
		int reCount = 0;
		if (!StringUtil.isEmpty(word)) {
			reCount = word.split(splitString).length;
		}
		return reCount;
	}

	public static void writeFile(File file, List<String> contents)
			throws IOException {
		FileWriter fw = null;
		fw = new FileWriter(file);
		for (String sentence : contents) {
			fw.write(sentence + "\n");
		}
		if (fw != null) {
			fw.close();
		}
	}

	public static void appendFile(String file, String conent) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true)));
			out.write(conent);
		} catch (Exception e) {

		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void appendFileLine(String file, String content) {
		appendFile(file, content + "\n");
	}

	public static void writeFile(String fileName, List<String> contents)
			throws IOException {
		writeFile(new File(fileName), contents);
	}

	public static void main(String[] args) throws ZeroFetcherException {
		System.out.println(StringUtil.wordListToString(
				readFileData("/home/lixuze/search.html"), "\r\n"));
	}
}
