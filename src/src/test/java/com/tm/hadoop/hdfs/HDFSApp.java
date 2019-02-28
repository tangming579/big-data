package com.tm.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;


public class HDFSApp {

    public static final String HDFS_PATH = "hdfs://154.8.184.140:8020";
    FileSystem fileSystem = null;
    Configuration configuration = null;

    /*
    创建HDFS目录
    */
    @Test
    public void mkdir() throws Exception{
        fileSystem.mkdirs(new Path("/hdfsapi/test"));
    }
    /*
    创建文件
    */
    @Test
    public void create() throws Exception{
        FSDataOutputStream output = fileSystem.create(new Path("hdfsapi/test/a.txt"));
        output.write("hello hadoop".getBytes());
        output.flush();

        System.out.println("HDFS Create File");
    }
    @Before
    public void setUp() throws Exception{
        System.out.println("HDFS setUp");

        configuration = new Configuration();
        fileSystem = FileSystem.get(new URI(HDFS_PATH),configuration,"hadoop");
    }

    @After
    public  void setDown() throws Exception{
        configuration = null;
        fileSystem = null;

        System.out.println("HDFS setDown");
    }
}
