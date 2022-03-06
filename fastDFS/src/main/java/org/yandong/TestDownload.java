package org.yandong;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestDownload {

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

            // 将图片转换为字节数组
            byte[] bytes = client.download_file1("group1/M00/00/00/wKi4gGIeAXuAaZd0AAAj4H9n0Kc858.jpg");

            // 通过io将字节数组转化为一个文件
            FileOutputStream fileOutputStream = new FileOutputStream(new File("F:/xxx.jpg"));
            fileOutputStream.write(bytes);
            fileOutputStream.close();
            trackerServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
