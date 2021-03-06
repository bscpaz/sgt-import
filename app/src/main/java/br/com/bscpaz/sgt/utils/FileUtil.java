package br.com.bscpaz.sgt.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class FileUtil {
	
	public static boolean createFile (String fileName, Charset charset, String content) {
		boolean result = true;
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
	    BufferedWriter writer = null;
	    
		try {
			File file = new File(fileName);
			
			if (file.exists() && !file.isDirectory()) { 
			    file.delete();
			}

			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos, charset);
			
			writer = new BufferedWriter(osw);
			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (osw != null) {
					osw.close();
				}
				if (writer != null) {
					writer.close();
				}				
			} catch (IOException e) {
				System.out.println("WARN: Error in attempt to close file's classes. See FileUtil.createFile() method.");
			}
		}
		return result;
	}

	public static boolean writeIntoFile (String fileName, Charset charset, String content) {
		boolean result = true;
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
	    BufferedWriter writer = null;
		boolean toAppend = true;
	    
		try {
			File file = new File(fileName);
			fos = new FileOutputStream(file, toAppend);
			osw = new OutputStreamWriter(fos, charset);
			
			writer = new BufferedWriter(osw);
			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (osw != null) {
					osw.close();
				}
				if (writer != null) {
					writer.close();
				}				
			} catch (IOException e) {
				System.out.println("WARN: Error in attempt to close file's classes. See FileUtil.createFile() method.");
			}
		}
		return result;
	}

	public static String getFileContentFromResource(String fileFromResource, Charset charset) {
		InputStream inputStream = null;
		
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			inputStream = classloader.getResourceAsStream(fileFromResource);
			return getFileContent(inputStream, charset);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getFileContentFromPath(String fileFromPath, Charset charset) {
		String fileContent = null;
		InputStream inputStream = null;
		
		try {
			inputStream = new FileInputStream(fileFromPath);
			fileContent = getFileContent(inputStream, charset);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileContent;
	}	
	
	private static String getFileContent(InputStream inputStream, Charset charset) {
		StringBuilder fileContent = new StringBuilder();
		InputStreamReader streamReader = null;
		BufferedReader reader = null;
		
		try {
			streamReader = new InputStreamReader(inputStream, charset);
			reader = new BufferedReader(streamReader);
		
			for (String line; (line = reader.readLine()) != null;) {
			    fileContent.append(line).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			fileContent = new StringBuilder();
		} finally {
			try {
				if (streamReader != null) {
					streamReader.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileContent.toString();
	}
}