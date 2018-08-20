package henu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


//https://blog.csdn.net/xietansheng/article/details/74366541


public class MyTabedPane {
	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//当前系统风格
		JFrame jFrame = new JFrame("测试窗口");
		jFrame.setSize(300, 300);
		jFrame.setBackground(new Color(40, 40, 40));
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		final JTabbedPane tabbedPane = new JTabbedPane();
//		tabbedPane.setOpaque(false);
		tabbedPane.setBackground(new Color(40, 40, 40));
		tabbedPane.setForeground(Color.white);
		tabbedPane.addTab("tab01", createTextPanel("Tab 01"));
		tabbedPane.addTab("tab01", createTextPanel("Tab 01"));
		tabbedPane.addTab("tab01", createTextPanel("Tab 01"));

		tabbedPane.setBackgroundAt(0, Color.red);
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				tabbedPane.setForeground(Color.white);
				tabbedPane.setForegroundAt(tabbedPane.getSelectedIndex(), Color.BLACK);
				System.out.println("当前选中的是:"+tabbedPane.getSelectedIndex());
			}
		});
		
		//设置默认选中的选项卡
		tabbedPane.setSelectedIndex(1);
		
		jFrame.setContentPane(tabbedPane);
		jFrame.setVisible(true);
	}
	
	private static JComponent createTextPanel(String text){
		
		JPanel panel = new JPanel(new GridLayout(1, 1));
		JLabel label = new JLabel("text");
		panel.setBackground(new Color(59, 63, 66));
		label.setForeground(Color.white);
		label.setFont(new Font(null, Font.PLAIN, 50));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);
		return panel;
	}
}
