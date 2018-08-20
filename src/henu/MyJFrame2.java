package henu;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class MyJFrame2  extends JFrame implements ActionListener{
    JLabel jl=new JLabel("图片");
    JMenuBar jmb=new JMenuBar();
    JMenu jm=new JMenu("文件");
    JMenuItem jmi=new JMenuItem("选择图片");
    JPanel jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
    JFileChooser chooser=new JFileChooser();
    public MyJFrame2() {
        super("浏览图片");
        jmb.add(jm);
        jm.add(jmi);
        jp.add(jl);
        jmi.addActionListener(this);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(jmb,BorderLayout.NORTH);
        this.add(jp,BorderLayout.CENTER);
        this.setSize(800,600);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        int i=chooser.showOpenDialog(this);
        if(i==chooser.APPROVE_OPTION){
        	ImageIcon imageIcon = new ImageIcon(chooser.getSelectedFile().getPath());
            Image image=new ImageIcon(chooser.getSelectedFile().getPath()).getImage();
            
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(
            		imageIcon.getIconWidth()/4, imageIcon.getIconHeight()/4,Image.SCALE_DEFAULT ));
            image=image.getScaledInstance(400, 400, Image.SCALE_DEFAULT );//调整图像大小400,400
//            jl.setIcon(new ImageIcon(image));
            jl.setIcon(imageIcon);
            jl.setText("");
            }
        if(i==chooser.CANCEL_OPTION)return;
        }
    public static void main (String[] args) {
    new MyJFrame2();
    }
    }
    