package henu;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImgCV { 
//	static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}  
//	public static void main(String[] args) {
//		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//		Mat img = Imgcodecs.imread("D:\\images\\img.jpg");
//		Mat new_img = huidu(img);
//		Imgcodecs.imwrite("D:\\images\\img002.jpg", new_img);
//	}
	
	//https://blog.csdn.net/m1109048058/article/details/76531738
	//背景去除 简单案列，只适合背景单一的图像
	static Mat doBackgroundRemoval(Mat frame){
		 Mat hsvImg = new Mat();  
	        List<Mat> hsvPlanes = new ArrayList<>();  
	        Mat thresholdImg = new Mat();  
	  
	        int thresh_type = Imgproc.THRESH_BINARY_INV;  
	  
	        // threshold the image with the average hue value  
	        hsvImg.create(frame.size(), CvType.CV_8U);  
	        Imgproc.cvtColor(frame, hsvImg, Imgproc.COLOR_BGR2HSV);  
	        Core.split(hsvImg, hsvPlanes);  
	  
	        // get the average hue value of the image  
	  
	        Scalar average = Core.mean(hsvPlanes.get(0));  
	        double threshValue = average.val[0];  
	        Imgproc.threshold(hsvPlanes.get(0), thresholdImg, threshValue, 179.0,  
	                thresh_type);  
	  
	        Imgproc.blur(thresholdImg, thresholdImg, new Size(5, 5));  
	  
	        // dilate to fill gaps, erode to smooth edges  
	        Imgproc.dilate(thresholdImg, thresholdImg, new Mat(),  
	                new Point(-1, -1), 1);  
	        Imgproc.erode(thresholdImg, thresholdImg, new Mat(), new Point(-1, -1),  
	                3);  
	  
	        Imgproc.threshold(thresholdImg, thresholdImg, threshValue, 179.0,  
	                Imgproc.THRESH_BINARY);  
	  
	        // create the new image  
	        Mat foreground = new Mat(frame.size(), CvType.CV_8UC3, new Scalar(255,  
	                255, 255));  
	        thresholdImg.convertTo(thresholdImg, CvType.CV_8U);  
	        frame.copyTo(foreground, thresholdImg);// 掩膜图像复制  
	        return foreground;  
	}
	
	//边缘检测
	static Mat doCanny(Mat frame)  
    {  
        // init  
        Mat grayImage = new Mat();  
        Mat detectedEdges = new Mat();  
        double threshold = 10;  
        // convert to grayscale  
        Imgproc.cvtColor(frame, grayImage, Imgproc.COLOR_BGR2GRAY);  
       // reduce noise with a 3x3 kernel  
        Imgproc.blur(grayImage, detectedEdges, new Size(3, 3));         
        // canny detector, with ratio of lower:upper threshold of 3:1  
        Imgproc.Canny(detectedEdges, detectedEdges, threshold, threshold * 3);           
        // using Canny's output as a mask, display the result  
        Mat dest = new Mat();  
        frame.copyTo(dest, detectedEdges);  
        return dest;  
    }  
	//灰度化
	static Mat huidu(Mat frame){
		Mat dest = new Mat();
		Imgproc.cvtColor(frame,	dest, Imgproc.COLOR_BGR2GRAY, 0);
		return dest;
	}
	
	//二值化
	static Mat erzhi(Mat frame){
		Mat hdimg = new Mat();//灰度过度
		Mat dest = new Mat();
		Imgproc.cvtColor(frame,	hdimg, Imgproc.COLOR_BGR2GRAY, 0);
		Imgproc.threshold(hdimg, dest, 107, 255 , Imgproc.THRESH_TOZERO);
		return dest;
	}
	//图像直接阈值化操作
	static Mat fazhihua(Mat frame){
		Mat hdimg = new Mat();//灰度过度
		Mat dest = new Mat();
		Imgproc.cvtColor(frame,	hdimg, Imgproc.COLOR_BGR2GRAY, 0);
		Imgproc.adaptiveThreshold(hdimg, dest, 200, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 7, 8);  
		return dest;
	}
	//均值滤波
	static Mat junzhi(Mat frame){
		Mat dest = new Mat();
		Imgproc.blur(frame, dest, new Size(9,9),new Point(-1,-1), Core.BORDER_DEFAULT);
		return dest;
	}
	
	//高斯滤波
	static Mat gaosi(Mat frame){
		Mat dest = new Mat();
		Imgproc.GaussianBlur(frame, dest, new Size(9,9), 0, 0, Core.BORDER_DEFAULT);
		return dest;
	}
	
	//中值滤波
	static Mat zhongzhi(Mat frame){
		Mat dest = new Mat();
		 Imgproc.medianBlur(frame, dest, 7);
		return dest;
	}
	
	//膨胀
	static Mat pengzhang(Mat frame){
		Mat dest = new Mat();
		Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3,3));
		Imgproc.dilate(frame, dest, element, new Point(-1, -1), 1);
		return dest;
	}
	
	//腐蚀
	static Mat fushi(Mat frame){
		Mat dest = new Mat();
		Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3,3));
		Imgproc.erode(frame, dest, element, new Point(-1, -1), 1);
		return dest;
	}
	
	//二值反色
	static Mat fanse(Mat frame){
		Mat dest = new Mat();
		Core.bitwise_not(frame, dest);
		return dest;
	}
	
	//亮度提升
	static Mat brightness(Mat frame){
		Mat dest = new Mat();
		Mat black = Mat.zeros(frame.size(), frame.type());
		Core.addWeighted(frame, 1.2, black, 0.5, 0, dest);
		return dest;
	}
	
	//亮度降低
	static Mat darkness(Mat frame){
		Mat dest = new Mat();
		Mat black = Mat.zeros(frame.size(), frame.type());
		Core.addWeighted(frame, 0.5, black, 0.5, 0, dest);
		return dest;
	}
}

