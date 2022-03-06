package org.yandong;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;

public class TestUpload {

    public static void main(String[] args) {
        try {
            // 加载配置文件
            ClientGlobal.initByProperties("config/fastdfs-client.properties");

            // 创建tracker客户端
            TrackerClient trackerClient = new TrackerClient();

            // 通过tracker客户端获取tracker的连接服务并返回
            TrackerServer trackerServer = trackerClient.getConnection();

            // 声明storage服务
            StorageServer storageServer = null;

            // 定义storage客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);

            // 定义文件元信息
            NameValuePair[] list = new NameValuePair[1];
            list[0] = new NameValuePair("filename", "dog.jpg");

            String fileID = client.upload_file1("E:\\img\\dog.jpg", "jpg", list);
            System.out.println("fileID = " + fileID);

            // fileID = group1/M00/00/00/wKi4gGIeAXuAaZd0AAAj4H9n0Kc858.jpg
            /*
                group1：一台服务器，就是一个组
                M00： store_path0 ----> /home/fastdfs/fdfs_storage/data
                00/00：两级数据目录
            */
            trackerServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
