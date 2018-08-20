package henu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.activation.MailcapCommandMap;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MyJButton implements Icon
{
    private Color color;
    private int width;
    private int height;

    public MyJButton(Color color, int width, int height)
    {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    public int getIconWidth()
    {
        return width;
    }

    public int getIconHeight()
    {
        return height;
    }

    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    
    
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI()
    {
        JPanel panel = new JPanel( new GridLayout(2, 2) );

        for (int i = 0; i < 4; i++)
        {
            Icon icon = new MyJButton(Color.RED, 50, 50);
            JButton label = new JButton( icon );
            label.setText("" + i);
            label.setHorizontalTextPosition(JButton.CENTER);
            label.setVerticalTextPosition(JButton.CENTER);
            panel.add(label);
        }

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(panel);
        f.setSize(200, 200);
        f.setLocationRelativeTo( null );
        f.setVisible(true);
    }
}