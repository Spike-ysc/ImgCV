package henu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class MainFrame {
	
	static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}  
	
	private static String imgUrl = "D:\\images\\img.jpg";	//图片地址
	private ImageIcon m_image = new ImageIcon(imgUrl);	//原始图片初始化
	private ImageIcon n_Image = new ImageIcon(imgUrl);	//备用图片
	
	private static final int img_Width = 600;	//图片宽度
	private static BufferedImage saveImg;

	private JFrame frame = new JFrame("openCV测试");
//	private JScrollPane leftJP = new JScrollPane();
//	new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);  
	private JPanel leftJp = new JPanel();
	private JScrollPane leftJSP = new JScrollPane(leftJp,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	//滚动条
	private JPanel rightJp = new JPanel();
	
	//若要加功能，只需要在这里加上就可以，然后在mListener的switch中处理。
	private String[] function = {"打开","灰度化","二值化",
			"直接阈值化","均值滤波","中值滤波","高斯滤波","膨胀",
			"腐蚀","边缘检测","反色","亮度提高","亮度降低","恢复","保存图片"};
	
	private JLabel imgLabel; 	//存放图片的控件
	
	private Mat nMat;	//更改图片时用到的
	
	
	private void init() {
		frame.setSize(900, 700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		leftStyle();
		rightStyle();
		
		frame.add(leftJSP,BorderLayout.WEST);
		frame.add(rightJp);
//		frame.pack();
		frame.setVisible(true);
	}
	//左边界面
	private void leftStyle() {
		leftJp.setSize(200, 700);
		leftJp.setBorder(new EmptyBorder(10, 15, 10, 30));	//设置空白边界，作为margin
		leftJp.setBackground(new Color(83, 83, 83));
		leftJp.setLayout(new BoxLayout(leftJp, BoxLayout.Y_AXIS));
//		leftJp.setLayout(new GridLayout(15, 1));
		Container cp=frame.getContentPane();
		cp.setBackground(new Color(78, 83, 86));
		for(int i=0; i<function.length;i++){

//			Icon mIcon = new MyJButton(new Color(78, 83, 86), 150, 25);
//			JButton button = new JButton(mIcon);
//			button.setText(function[i]);
//			button.setHorizontalTextPosition(JButton.CENTER);
//            button.setVerticalTextPosition(JButton.CENTER);
			JButton button = new JButton(function[i]);
			button.setMaximumSize(new Dimension(150, 25));
			//不起作用是因为风格是window风格
			//可能需要重绘JButton
			button.setForeground(Color.white);
			button.setBackground(Color.red);
			button.setOpaque(false);
//			//0 是第一种风格，四种风格分别是无样式、粗体、斜体、粗体加斜体 ，15是字体大小
			button .setFont(new java.awt.Font("等线", 0, 15));
			button.addActionListener(new mListener());
			leftJp.add(button);
			leftJp.add(Box.createVerticalStrut(15));
		}
		

	}
	
	//右边界面
	private void rightStyle(){
		rightJp.setSize(700, 700);

		rightJp.setBackground(new Color(40, 40, 40));
		imgLabel = new JLabel(reSize(m_image));
		rightJp.add(imgLabel);
	}
	
	//保持宽高比更改图片为宽度为img_width
	//返回更改后的ImageIcon
	private ImageIcon reSize(ImageIcon image){
		int width = image.getIconWidth();
		
		//获取宽度和600的比例，算出等比例的高度
		double bili = (double)image.getIconWidth()/img_Width;
		int img_Height = (int)(image.getIconHeight()/bili); 

		
		image.setImage(image.getImage().getScaledInstance(
				img_Width, img_Height,
				Image.SCALE_DEFAULT));
		return image;
	}
	
	
	private void toSaveImg(){
		try {
			ImageIO.write(saveImg, "jpg", new File("D:/images/gray.jpg"));
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//当前系统风格
		new MainFrame().init();
	}
	
	//将更改后的图片替换掉原先的图片
	private void setSource(Mat nMat) {
		BufferedImage image= Mat2BufferedImage.matToBufferedImage(nMat);  
		saveImg = image;
        ImageIcon source = new ImageIcon(image);
        imgLabel.setIcon(reSize(source));
	}
	
	//按钮点击事件
	class mListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
//			System.out.println(arg0.getActionCommand());
			switch (arg0.getActionCommand()) {
			case "打开":
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfc.showDialog(new JLabel(), "选择");
				try {
					File file = jfc.getSelectedFile();
					if (file.exists()&& file.length()!=0) {
						imgUrl = file.getPath();
						n_Image = new ImageIcon(imgUrl);
						ImageIcon source = new ImageIcon(imgUrl);
						imgLabel.setIcon(reSize(source));
					}else {
						System.out.println("文件为空");
					}
				} catch (Exception e) {
					System.out.println("读取文件错误！");
					// TODO: handle exception
				}
				break;
			case "灰度化":
				nMat = ImgCV.huidu(Imgcodecs.imread(imgUrl));
				setSource(nMat);
				break;
			case "二值化":
				nMat = ImgCV.erzhi(Imgcodecs.imread(imgUrl));
				setSource(nMat);
				break;
			case "直接阈值化":
				nMat = ImgCV.fazhihua(Imgcodecs.imread(imgUrl));
				setSource(nMat);
				break;
			case "均值滤波":
				nMat = ImgCV.junzhi(Imgcodecs.imread(imgUrl));
				setSource(nMat);
				break;
			case "中值滤波":
				nMat = ImgCV.zhongzhi(Imgcodecs.imread(imgUrl));
				setSource(nMat);
				break;
			case "高斯滤波":
				nMat = ImgCV.gaosi(Imgcodecs.imread(imgUrl));
				setSource(nMat);
				break;
			case "膨胀":
				nMat = ImgCV.pengzhang(Imgcodecs.imread(imgUrl));
				setSource(nMat);
				break;
			case "腐蚀":
				nMat = ImgCV.fushi(Imgcodecs.imread(imgUrl));
				setSource(nMat);
				break;
			case "边缘检测":
				nMat = ImgCV.doCanny(Imgcodecs.imread(imgUrl));
				setSource(nMat);
				break;
			case "反色":
				nMat = ImgCV.fanse(Imgcodecs.imread(imgUrl));
				setSource(nMat);
				break;
			case "亮度提高":
				nMat = ImgCV.brightness(Imgcodecs.imread(imgUrl));
				setSource(nMat);
				break;
			case "亮度降低":
				nMat = ImgCV.darkness(Imgcodecs.imread(imgUrl));
				setSource(nMat);
				break;
			case "恢复":
				imgLabel.setIcon(reSize(n_Image));
				break;
			case "保存图片":
				toSaveImg();
				break;
			default:
				break;
			}
		}
	}
	

}
