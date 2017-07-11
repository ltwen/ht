package com.yc.hadoop42;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

public class Conn {

	public static void main(String[] args) {
		String urlStr="hdfs://master:9000/data/aaaa.txt"; //hdfs文件系统资源url
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());//设置可以访问的hdfs网络支持协议
		URL url = null;
		try {
			url=new URL(urlStr);
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		}
		URLConnection conn=null;
		try {
			conn=url.openConnection();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
//		Scanner sc = null;
//		try {
//			sc=new Scanner(conn.getInputStream());
//			while (sc.hasNextLine()) {
//				System.out.println(sc.nextLine());
//				
//			}
//			sc.close();
//		} catch (IOException e) {
//			System.err.println(e.getMessage());
//		}
		
			try {
				IOUtils.copyBytes(conn.getInputStream(), System.out, 2048, true);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		
		
	}

}
