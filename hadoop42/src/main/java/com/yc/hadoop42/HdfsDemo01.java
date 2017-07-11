package com.yc.hadoop42;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class HdfsDemo01 {

	public static void main(String[] args) throws Exception {
		Configuration con=new Configuration(); //hadoop的配置文件对象
		URI uri=URI.create("hdfs://master:9000/data/aaaa.txt"); //hadoop分布式文件系统master所在地URI
		FileSystem fileSystem=FileSystem.get(uri,con ,"hadoop");//根据配置和远程地址创建文件系统
		Path path=new Path(uri);//操作系统中文件时file，文件系统中文件path
		FSDataInputStream in=fileSystem.open(path); //文件系统中的文件对象
		IOUtils.copyBytes(in, System.out, con, true);
	}

}
