package henu;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class MyFrame {
	 static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}  
	private JFrame jf = new JFrame("openCV测试");
	private JPanel p = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private String img1Url = "D:\\images\\img.jpg";
	private ImageIcon imagetoshow = new ImageIcon(img1Url);
	private ImageIcon imagetoshow2 = new ImageIcon("D:\\images\\img2.jpg");
	private void init() {
		int width = imagetoshow.getIconWidth();
		int height = imagetoshow.getIconHeight();
		
//		f.addWindowListener(new MyListenerr());
		
		imagetoshow.setImage(imagetoshow.getImage().getScaledInstance(
				width, height,Image.SCALE_DEFAULT ));
		
		imagetoshow2.setImage(imagetoshow2.getImage().getScaledInstance(
				width/4, height/4,Image.SCALE_DEFAULT ));
		
		
		JLabel showImageLabel = new JLabel(imagetoshow);
		JLabel showImageLabel2 = new JLabel(imagetoshow2);
		
		
		p.add(showImageLabel, new Integer(Integer.MIN_VALUE));
		p2.add(showImageLabel2, new Integer(Integer.MIN_VALUE));

		jf.add(p, BorderLayout.WEST);
		jf.add(p2,BorderLayout.EAST);
		
		JButton startBtn = new JButton("start");
		
		JButton openBtn = new JButton("open");
		startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("点击了start按钮");
				
				Mat frame = Imgcodecs.imread(img1Url);
//				Mat newFrame = ImgCV.doBackgroundRemoval(frame);
//				Mat newFrame = ImgCV.doCanny(frame);
//				Mat newFrame = ImgCV.huidu(frame);
//				Mat newFrame = ImgCV.erzhi(frame);
//				Mat newFrame = ImgCV.fazhihua(frame);
//				Mat newFrame = ImgCV.junzhi(frame);
//				Mat newFrame = ImgCV.gaosi(frame);
//				Mat newFrame = ImgCV.zhongzhi(frame);
				Mat newFrame = ImgCV.pengzhang(frame);
//				Mat newFrame = ImgCV.fushi(frame);
//				Mat source=Imgcodecs.imread("D://images//img2.jpg");//加载lena图像为Mat格式  
		        BufferedImage image= Mat2BufferedImage.matToBufferedImage(newFrame);  
		        
		        ImageIcon source = new ImageIcon(image);
		        int n=source.getIconWidth()/600+1;
		        source.setImage(source.getImage().getScaledInstance(
						source.getIconWidth()/n, source.getIconHeight()/n,Image.SCALE_DEFAULT ));
//		        if(source.getIconWidth()>600 && source.getIconWidth()<1000){
//					source.setImage(source.getImage().getScaledInstance(
//							source.getIconWidth()/2, source.getIconHeight()/2,Image.SCALE_DEFAULT ));
//				}
//				else if (source.getIconWidth()>1000) {
//					source.setImage(source.getImage().getScaledInstance(
//							source.getIconWidth()/4, source.getIconHeight()/4,Image.SCALE_DEFAULT ));
//				}
		       
				showImageLabel.setIcon(source);
				
			}
		});
		openBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc=new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfc.showDialog(new JLabel(), "选择");
				File file = jfc.getSelectedFile();
				if (file.isFile()) {
					img1Url = file.getPath();
					ImageIcon source =new ImageIcon(img1Url);
					System.out.println(source.getIconWidth());
					
					int n=source.getIconWidth()/600+1;
					
//					
					source.setImage(source.getImage().getScaledInstance(
							source.getIconWidth()/n, source.getIconHeight()/n,Image.SCALE_DEFAULT ));
//					if(source.getIconWidth()>600 && source.getIconWidth()<1000){
//						source.setImage(source.getImage().getScaledInstance(
//								source.getIconWidth()/2, source.getIconHeight()/2,Image.SCALE_DEFAULT ));
//					}
//					else if (source.getIconWidth()>=1000) {
//						source.setImage(source.getImage().getScaledInstance(
//								source.getIconWidth()/4, source.getIconHeight()/4,Image.SCALE_DEFAULT ));
//					}
					showImageLabel.setIcon(source);
				}
				
			}
		});
		p3.add(openBtn);
		p3.add(startBtn);
		jf.add(p3, BorderLayout.SOUTH);
		jf.pack();
		jf.setVisible(true);
//		f.add(showImageLabel, new Integer(Integer.MIN_VALUE));
//		showImageLabel.setBounds(0, 0, 500, 150);
////		f.setLayout(new BorderLayout(30, 5));
////		f.add(new Button("南"), BorderLayout.SOUTH);
////		f.add(new Button("北"), BorderLayout.NORTH);
//		f.pack();
//		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		try {

			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus风格，jdk6 update10版本以后的才会出现
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//当前系统风格
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//Motif风格，是蓝黑 ---------不好看
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());//跨平台的Java风格 --------默认风格
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
			//UIManager.setLookAndFeel("javax.swing.plaf.windows.WindowsLookAndFeel");//windows风格
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//java风格
			//UIManager.setLookAndFeel("com.apple.mrj.swing.MacLookAndFeel");//待考察，
			

			} catch (Exception e) {
			e.printStackTrace();
			}

		new MyFrame().init();
	}
	
}
