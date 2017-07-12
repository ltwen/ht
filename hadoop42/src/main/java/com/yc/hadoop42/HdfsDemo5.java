package com.yc.hadoop42;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class HdfsDemo5 {

	public static void main(String[] args) throws Exception {
		Configuration con=new Configuration(); //hadoop的配置文件对象
		URI uri=URI.create("hdfs://master:9000"); //hadoop分布式文件系统master所在地URI
		FileSystem fs=FileSystem.get(uri, con);
		Path path=new Path("/in");
		if(fs.exists(path)){
			System.out.println("该目录存在");
		}else{
			fs.mkdirs(path);
			System.out.println("创建目录");
		}
		Path file=new Path(path, "data04.txt");
		FSDataOutputStream out=null;
		DbHelp.builtfile(path);
		/*if (fs.createNewFile(file)) {
			System.out.println("创建文件成功！！！");
			
		}else {
			System.out.println("创建文件不成功");
		}*/
		if(fs.exists(file)){
			//文件存在追加内容
			out=fs.append(file, 4096, new Progressable() {
				@Override //执行提示
				public void progress() {
					System.out.println("正在更新");
				}
			});
		}else {
			//文件不存在
			out=fs.create(file, true, 4096, new Progressable() {
				@Override //执行提示
				public void progress() {
					System.out.println("!!!");
				}
			});
		}
		InputStream in=new FileInputStream("E:\\Documents\\MyHelp\\linux.txt");
		IOUtils.copyBytes(in, out,4096, true);
		
		
		
			
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
