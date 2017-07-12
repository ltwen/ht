package com.yc.hadoop42;

import java.net.URI;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;

public class HdfsDemo3 {

	public static void main(String[] args) throws Exception {
		Configuration con=new Configuration(); //hadoop的配置文件对象
		URI uri=URI.create("hdfs://master:9000"); //hadoop分布式文件系统master所在地URI
		DistributedFileSystem fs=(DistributedFileSystem) FileSystem.get(uri,con ,"hadoop");//根据配置和远程地址创建文件系统
		Path file=new Path("/data/cite75_99.txt");//操作系统中文件时file，文件系统中文件path    这里是绝对路径
		
		FileStatus fileInfo=fs.getFileStatus(file);
		BlockLocation [] bs=fs.getFileBlockLocations(fileInfo, 0, fileInfo.getLen());
		System.out.println("文件被分为："+bs.length+"个快");
		for (BlockLocation b : bs) {
			String [] hosts=b.getHosts();
			long star=b.getOffset();
			long datasize=b.getLength();
			String [] names=b.getNames();
			for (int i=0;i<names.length;i++) {
				System.out.println("offset:"+ showSize(star) + "datasize:"+ showSize(datasize) + "host:"+hosts[i]+"name:"+names[i]);
			}
			
		} 
		
		
		
		
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
