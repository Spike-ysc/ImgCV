package henu;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MyJFrame {
	JFrame jf = new JFrame("openCV测试");
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT,
			JTabbedPane.WRAP_TAB_LAYOUT);
	ImageIcon icon = new ImageIcon("D:\\images\\img.jpg");
	
	String[] layouts = {"换行布局", "滚动条布局"};
	String[] positions = {"左边","顶部","右边","底部"};
	Map<String, String> books = new LinkedHashMap<>();
	
	public void init() {
		icon.setImage(icon.getImage().getScaledInstance(150, 50,Image.SCALE_DEFAULT ));
		books.put("疯狂java讲义1", "img.jpg");
		books.put("疯狂java讲义2", "img.jpg");
		books.put("疯狂java讲义3", "img.jpg");
		books.put("疯狂java讲义4", "img.jpg");
		books.put("疯狂java讲义5", "img.jpg");
		String tip = "可以看到本书照片";
		for(String bookName:books.keySet()){
			tabbedPane.addTab(bookName, icon,null,tip);
		}
		jf.add(tabbedPane, BorderLayout.CENTER);
		jf.pack();
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		new MyJFrame().init();
	}
}
