package henu;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class CharTest {

	private void predictann(Mat testroi){
		CascadeClassifier bp = new CascadeClassifier();
		bp.load("C:\\software\\OpenCV\\opencv\\sources"
		 		+ "\\data\\haarcascades_cuda\\bpcharModel.xml");
		int image_cols= 8;
		int image_rows = 16;
		Mat test_temp = new Mat();
		Imgproc.resize(testroi, test_temp,new Size(image_cols,image_rows), 0, 0, Imgproc.INTER_AREA);
		Imgproc.threshold(test_temp, test_temp, 0, 255, Imgproc.THRESH_BINARY|Imgproc.THRESH_OTSU);
		MatOfFloat mFloat = new MatOfFloat();
		
	}
	
	
	
	public static void main(String[] args) {
		
	}
}
