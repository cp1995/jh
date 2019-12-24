package com.cp.dd.common.util;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

import java.io.IOException;
import java.net.URI;

public class HDFSUtil {

	private static Configuration conf = null;
	private static FileSystem fs = null;

	private static String DFSMasterName = "fs.defaultFS";
	private static String nameNodeURI = "hdfs://10.18.97.150:9000/act/";
	private static String username = "eshore";


	static {
		conf = new Configuration();
		conf.set(DFSMasterName, nameNodeURI);
		try {
			//fs = FileSystem.get(conf);
			fs = FileSystem.get(new URI(nameNodeURI), conf, username);
		} catch (Exception e) {
			System.out.println("获取文件系统失败！");
			e.printStackTrace();
		}
	}
	
	// 文件列表获取
	public static void listFiles(String url) {
		Path path = new Path(url);
		try {
			RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(path, true);
			while (listFiles.hasNext()) {
				System.out.println(listFiles.next().getPath());
			}
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	// 上传文件
	/**
	 * 
	 * @param localfile 需要上传的本地文件路径
	 */
	public static void upload(String localfile, String targetfile) {
		Path src = new Path(localfile);
		Path dst = new Path(targetfile);
		try {
			fs.copyFromLocalFile(src, dst);
		} catch (IOException e) {
			System.out.println("文件上传失败");
			e.printStackTrace();
		}
	}
	
	// 下载文件
	public static void download(String localfile, String targetfile) {
		Path src = new Path(localfile);
		Path dst = new Path(targetfile);
		try {
			fs.copyToLocalFile(src, dst);
		} catch (IOException e) {
			System.out.println("文件下载失败");
			e.printStackTrace();
		}
	}
	public static Boolean mkdir(String path) {
	    try {
	        System.out.println("FilePath=" + path);
	        if (!fs.exists(new Path(path))) {
	            // 创建目录
	            fs.mkdirs(new Path(path));
	            System.out.println("[mkdir] 创建文件目录成功,path=[{}]"+ path);
	        }
	        fs.close();//释放资源
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public static void main(String[] args) throws Exception {


		//要上传的源文件所在路径
		String localFile ="D:/11.png";
		//hadoop文件系统的跟目录
		String destFile = "/mulu11.png";
		//将源文件copy到hadoop文件系统

		//HDFSUtil.upload(localFile,destFile);

		/*	HDFSUtil.download(destFile,"/Users/liangrh/Downloads/1234.jpg");

		HDFSUtil.listFiles("/");*/
		//创建目录
		mkdir("/tour/");
	}

}
