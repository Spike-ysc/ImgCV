package henu;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CaiJian2 {
	static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}  
	
	
	public static void main(String[] args) {

			for(int i=0; i<10;i++){
				String path = "D:\\images\\timg.jpg";
				Mat image = Imgcodecs.imread(path);
				int ceil_width = 500;
				int ceil_height =125;

				System.out.println("----------cols="+image.cols()+"-------rows="+image.rows());
				int x = image.cols()/500;
				int y = image.rows()/125;
				System.out.println("----------x="+x+"-------y="+y);
				for(int j=0; j<y;j++){
					for (int j2 = 0; j2 < x; j2++) {
						Rect rect = new Rect(j2*ceil_width, j*ceil_height,ceil_width,ceil_height);
						Mat tmp1 = new Mat(image, rect);
						Imgcodecs.imwrite("D:\\images\\new\\"+(j+23)+"_"+j2+".bmp", tmp1);
						System.out.println(j+"---------"+j2+"over");
					}
				}

					
								
			
			}
		}
	
	public static void fangda(String path){
		 Mat src = Imgcodecs.imread(path);

	        Mat dst = new Mat();
	        Imgproc.resize(src, dst, new Size(100,25), 0, 0, Imgproc.INTER_AREA);
	        Imgcodecs.imwrite(path, dst);
	        System.out.println(path+"完成");
	}
			
	private static String numToStr(int n, int num){
		String result = "";
		if (num>0 && num<10) {
			result = "_RM"+n+"-_000"+num;
		}else if (num<100) {
			result = "_RM"+n+"-_00"+num;
		}else if(num<1000) {
			result = "_RM"+n+"-_0"+num;
		}else {
			System.out.println("数字错误！");
		}
		return result;
	}
}
