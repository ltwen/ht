package com.yc.hadoop42;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsDemo4 {

	public static void main(String[] args) throws Exception {
		Configuration con=new Configuration(); //hadoop的配置文件对象
		URI uri=URI.create("hdfs://master:9000"); //hadoop分布式文件系统master所在地URI
		FileSystem fs=FileSystem.get(uri, con);
		/*Path src=new Path("E:\\Documents\\MyHelp\\linux.txt");
		Path dst=new Path("/in");
		fs.copyFromLocalFile(src, dst); //从本地下载到文件系统
		Path src=new Path("/in/data01.txt");
		Path dst=new Path("D:");
		fs.copyToLocalFile(src, dst);*///从文件系统下载到本地
		fs.delete(new Path("/in/linux.txt"), true); //从文件系统删除文件
		
		
		
			
	} 
		
		
		
		
	
		

}
