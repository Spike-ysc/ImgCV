package henu;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

public class CaiJian  {
	
	static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}  
	private static int[] yeshu = {91,90,90,90,90,90,90,90,90,91,90,91,90,90,91,90,90,90};
	
	
	
	public static void main(String[] args) {

		for(int n =21;n<=38;n++){
			for(int i=0; i<yeshu[n-21];i++){

				String path = "D:\\manhua\\"+n+"\\"+numToStr(n,i+1)+".png";
				Mat image = Imgcodecs.imread(path);
//				Mat image = Imgcodecs.imread("D:\\manhua\\1\\_RM01-_0000.jpg");
				int ceil_width = image.cols()/2;
				int ceil_height = image.rows();
				if(i == (yeshu[n-21]-1)){
					Rect rect3 = new Rect(0, 0, image.cols(),ceil_height);
					Mat tmp3 = new Mat(image, rect3);
					Imgcodecs.imwrite("D:\\manhua\\new\\"+n+"\\"+numToStr(n,i*2+1)+".png", tmp3);
				}else {
					Rect rect = new Rect(0, 0,ceil_width,ceil_height);
					Rect rect2 = new Rect(ceil_width, 0,ceil_width,ceil_height);
					Mat tmp1 = new Mat(image, rect);
					Mat tmp2 = new Mat(image, rect2);
//					Imgcodecs.imwrite("D:\\manhua\\new\\1\\_RM01-_0000.jpg", tmp2);
//					Imgcodecs.imwrite("D:\\manhua\\new\\1\\_RM01-_0001.jpg", tmp1);
//					
					Imgcodecs.imwrite("D:\\manhua\\new\\"+n+"\\"+numToStr(n,i*2+1)+".png", tmp2);
					Imgcodecs.imwrite("D:\\manhua\\new\\"+n+"\\"+numToStr(n,2*(i+1))+".png", tmp1);
				}
				
				System.out.println("第"+n+"章的第"+(i+1)+"张裁剪结束");
			}
		}
		
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
