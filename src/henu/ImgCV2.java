package henu;

import java.awt.Image;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class ImgCV2 {
	 public static void main(String[] args) {  
	        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	        
	        //模板匹配模块
//	        Mat g_src = Imgcodecs.imread("D:\\images\\moban_src.jpg");
//	        Mat g_tem = Imgcodecs.imread("D:\\images\\moban_tem.jpg");
//	        Mat new_img =moban(g_tem, g_src);
//	        Imgcodecs.imwrite("D:\\images\\moban_result.jpg",new_img );
	        
	        Mat rl_src = Imgcodecs.imread("D:\\images\\newImg.jpg");
	        Mat new_img = renlianshibie(rl_src);
	        Imgcodecs.imwrite("D:\\images\\rl_result.jpg", new_img);
	        System.out.println("人脸识别完成");
//	        Mat img = Imgcodecs.imread("D:\\images\\img4.jpg");//读图像  
//	        Mat new_img = doCanny(img);  
//	        Imgcodecs.imwrite("D:\\images\\img5.jpg",new_img);//写图像  
	    }  
	 
	 //模板匹配
	 //https://blog.csdn.net/m1109048058/article/details/78583309
	 //g_tem 模板图片，g_src源图片
	  public static Mat moban(Mat g_tem, Mat g_src) {
		int result_rows = g_src.rows() - g_tem.rows() +1;
		int result_cols = g_src.cols() - g_tem.cols() +1;
		Mat g_result = new Mat(result_rows,result_cols, CvType.CV_32FC1);
		Imgproc.matchTemplate(g_src, g_tem, g_result, Imgproc.TM_CCORR_NORMED);
		
		// Imgproc.matchTemplate(g_src, g_tem, g_result,
        // Imgproc.TM_CCOEFF_NORMED); // 归一化相关系数匹配法

        // Imgproc.matchTemplate(g_src, g_tem, g_result, Imgproc.TM_CCOEFF);
        // //
        // 相关系数匹配法：1表示完美的匹配；-1表示最差的匹配。

        // Imgproc.matchTemplate(g_src, g_tem, g_result, Imgproc.TM_CCORR); //
        // 相关匹配法

        // Imgproc.matchTemplate(g_src, g_tem, g_result,Imgproc.TM_SQDIFF); //
        // 平方差匹配法：该方法采用平方差来进行匹配；最好的匹配值为0；匹配越差，匹配值越大。

        // Imgproc.matchTemplate(g_src, g_tem,g_result,Imgproc.TM_CCORR_NORMED);
        // // 归一化相关匹配法
		
		
		Core.normalize(g_result, g_result,0, 1, Core.NORM_MINMAX, -1,new Mat());
		Point matchLocation = new Point();
		MinMaxLocResult mmlr = Core.minMaxLoc(g_result);
		matchLocation = mmlr.maxLoc;
		Imgproc.rectangle(g_src, matchLocation,
				new Point(matchLocation.x+g_tem.cols(),matchLocation.y+g_tem.rows()),
				new Scalar(0,0,0,0));
		return g_src;
	}
	  
	  
	  //人脸识别
	 public static Mat renlianshibie(Mat m_src) {
		 CascadeClassifier faceDetector = new CascadeClassifier();
//		 faceDetector.load("C:\\software\\OpenCV\\opencv\\sources"
//			 		+ "\\data\\haarcascades_cuda\\haarcascade_frontalface_alt.xml");
		 faceDetector.load("C:\\software\\OpenCV\\opencv\\sources"
			 		+ "\\data\\haarcascades_cuda\\cascade.xml");
			System.out.println("this first");
		 
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(m_src, faceDetections);
		System.out.println("this second");
		System.out.println(String.format("Detected "+ faceDetections.toArray()+"faces"));
		for(Rect rect : faceDetections.toArray()){

			System.out.println("this third");
			
			//将识别的图像打印出来
			Imgcodecs.imwrite("D:\\images\\"+rect.size()+".jpg",new Mat(m_src, rect));
			Imgproc.rectangle(m_src, new Point(rect.x, rect.y),
					new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(0,255,0));
			
		}
		return m_src;
	}
	  
	  

}
