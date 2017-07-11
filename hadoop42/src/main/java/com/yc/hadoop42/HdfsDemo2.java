package com.yc.hadoop42;

import java.net.URI;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsDemo2 {

	public static void main(String[] args) throws Exception {
		Configuration con=new Configuration(); //hadoop的配置文件对象
		URI uri=URI.create("hdfs://master:9000"); //hadoop分布式文件系统master所在地URI
		FileSystem fs=FileSystem.get(uri,con ,"hadoop");//根据配置和远程地址创建文件系统
		Path file=new Path("/data/cite75_99.txt");//操作系统中文件时file，文件系统中文件path    这里是绝对路径
		//FSDataInputStream in=fs.open(file); //文件系统中的文件对象
		//IOUtils.copyBytes(in, System.out, con, true);
		FileStatus fileInfo=fs.getFileStatus(file);
		
		
		System.out.println("文件大小：" + showSize(fileInfo.getLen()));
		System.out.println("是否是文件：" + fileInfo.isDirectory());
		System.out.println("文件的相同份数：" + fileInfo.getReplication());
		System.out.println("文件块大小：" + showSize(fileInfo.getBlockSize()));
		System.out.println("文件最后修改时间：" + showData(fileInfo.getModificationTime()));
		System.out.println("文件大小：" + showSize(fileInfo.getAccessTime()));
		System.out.println("文件权限：" + fileInfo.getPermission());
		System.out.println("文件所有者：" + fileInfo.getOwner());
		System.out.println("文件所在组：" + fileInfo.getGroup());
		System.out.println("文件的关联：" + fileInfo.isSymlink());
		/*System.out.println((double)Math.pow(1024, 10));
		System.out.println((long)Math.pow(1024,10));*/
		
	}
		public static String showSize(long num){
			if(1024>num){
				return num +".0Byte";
			}else if(1024<num && num<Math.pow(1024, 2)){
				return num / 1024.0 + "KB";
			}else if((long)Math.pow(1024, 2)<num && num<(long)Math.pow(1024, 3)){
				return num / Math.pow(1024, 2) + "MB";
			}else if((long)Math.pow(1024, 3)<num && num<(long)Math.pow(1024, 4)){
				return num / Math.pow(1024, 3) + "GB";
			}else if((long)Math.pow(1024, 4)<num && num<(long)Math.pow(1024, 5)){
				return num / Math.pow(1024, 4) + "TB";
			}else if((long)Math.pow(1024, 5)<num && num<(long)Math.pow(1024, 6)){
				return num / Math.pow(1024, 5) + "PB";
			}else if((long)Math.pow(1024, 6)<num && num<(long)Math.pow(1024, 7)){
				return num / Math.pow(1024, 6) + "EB";
			}else{
				return num / Math.pow(1024, 7) + "ZB";
			}
		}
		
		public static String showData(long num) {
			Date date=new Date(num);
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return dateFormat.format(date);
			
		}

}
