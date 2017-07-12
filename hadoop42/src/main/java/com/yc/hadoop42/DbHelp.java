package com.yc.hadoop42;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public  class DbHelp{
		static Configuration conf=new Configuration();
		static URI uri=URI.create("hdfs://master:9000");
		static FileSystem fs=null;
	
	//上传
	public static void adds(Path [] srcs,Path dst) throws IOException{
		fs=FileSystem.get(uri, conf);
		fs.copyFromLocalFile(false, true, srcs, dst);
	}
	
	public static void add(Path src,Path dst) throws IOException{
		fs=FileSystem.get(uri, conf);
		fs.copyFromLocalFile(false, true, src, dst);
	}
	
	//下载
	public static void down(Path src,Path dst) throws IOException{
		fs=FileSystem.get(uri, conf);
		fs.copyToLocalFile(src, dst);
	}
	
	//创建目录
	public static boolean builtDir(Path path) throws IOException{
		fs=FileSystem.get(uri, conf);
		if (fs.exists(path)) {
			System.out.println("该目录已存在");
			return false;
		} else {
			return fs.mkdirs(path);
		}
	}
	
	//创建文件
	public static boolean builtfile(Path path) throws IOException{
		fs=FileSystem.get(uri, conf);
		if (fs.exists(path)) {
			System.out.println("该文件已存在");
			return false;
		} else {
			return fs.createNewFile(path);
		}
	}
	
}
