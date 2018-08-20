package henu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFile {

    public ReadFile() {
    }
    /**
     * 读取某个文件夹下的所有文件
     */
    public static boolean readfile(String filepath) throws FileNotFoundException, IOException {

    	try {

                    File file = new File(filepath);
                    if (!file.isDirectory()) {
                            System.out.println("文件");
                            System.out.println("path=" + file.getPath());
                            System.out.println("absolutepath=" + file.getAbsolutePath());
                            System.out.println("name=" + file.getName());

                    } else if (file.isDirectory()) {
                            System.out.println("文件夹");
                            String[] filelist = file.list();
                            for (int i = 0; i < filelist.length; i++) {
                                    File readfile = new File(filepath + "\\" + filelist[i]);
                                    if (!readfile.isDirectory()) {
                                    		
                                            System.out.println("path=" + readfile.getPath());
                                            System.out.println("absolutepath="
                                                            + readfile.getAbsolutePath());
                                            System.out.println("name=" + readfile.getName());
                                            readfile.renameTo(new File("F:\\OpenCV\\cartest\\positive_samples\\"+i+".bmp"));
                                            //更改图片大小
//                                            CaiJian2.fangda(readfile.getPath());

                                    } else if (readfile.isDirectory()) {
                                            readfile(filepath + "\\" + filelist[i]);
                                    }
                            }

                    }

            } catch (FileNotFoundException e) {
                    System.out.println("readfile()   Exception:" + e.getMessage());
            }
            return true;
    }

    /**
     * 删除某个文件夹下的所有文件夹和文件
     */
    
    
    /*public static boolean deletefile(String delpath)
                    throws FileNotFoundException, IOException {
            try {

                    File file = new File(delpath);
                    if (!file.isDirectory()) {
                            System.out.println("1");
                            file.delete();
                    } else if (file.isDirectory()) {
                            System.out.println("2");
                            String[] filelist = file.list();
                            for (int i = 0; i < filelist.length; i++) {
                                    File delfile = new File(delpath + "\\" + filelist[i]);
                                    if (!delfile.isDirectory()) {
                                            System.out.println("path=" + delfile.getPath());
                                            System.out.println("absolutepath="
                                                            + delfile.getAbsolutePath());
                                            System.out.println("name=" + delfile.getName());
                                            delfile.delete();
                                            System.out.println("删除文件成功");
                                    } else if (delfile.isDirectory()) {
                                            deletefile(delpath + "\\" + filelist[i]);
                                    }
                            }
                            file.delete();

                    }

            } catch (FileNotFoundException e) {
                    System.out.println("deletefile()   Exception:" + e.getMessage());
            }
            return true;
    }*/
    
    public static void main(String[] args) {
            try {
                readfile("F:\\OpenCV\\cartest\\positive_samples"); 
//                readfile("F:\\OpenCV\\cartest\\negative_samples");
                    // deletefile("D:/file");
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }
            System.out.println("ok");

    }

}