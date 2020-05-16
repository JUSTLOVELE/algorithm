package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Util {
	
	public static List<String> readMediumUF() {
		
		try {
			
			String path = System.getProperty("user.dir");
			path += "/src/main/resources/file/mediumUF.txt";
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			String s = null;
			List<String> list = new ArrayList<String>();
			
			while((s = br.readLine())!=null){//使用readLine方法，一次读一行
               
				if(s != null && s.length() > 0  && !"".equals(s)) {
					list.add(s);
				}
            }
			
            br.close();
            return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<String> readTinyUF() {
		
		try {
			
			String path = System.getProperty("user.dir");
			path += "/src/main/resources/file/tinyUF.txt";
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			String s = null;
			List<String> list = new ArrayList<String>();
			
			while((s = br.readLine())!=null){//使用readLine方法，一次读一行
               
				if(s != null && s.length() > 0  && !"".equals(s)) {
					list.add(s);
				}
            }
			
            br.close();
            return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<String> readData(String path) {
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			String s = null;
			List<String> list = new ArrayList<String>();
			
			while((s = br.readLine())!=null){//使用readLine方法，一次读一行
               
				if(s != null && s.length() > 0  && !"".equals(s)) {
					list.add(s);
				}
            }
			
            br.close();
            return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
