package javarobot.chapter6;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File;

public class ImagePanel extends JPanel
{
	public BufferedImage image = null;

	public ImagePanel()
	{
		init(320,240);
	}

	public ImagePanel(BufferedImage img)
	{
		image = img;
		
		init(image.getWidth(), image.getHeight());
		repaint();
	}

	public ImagePanel(String path) throws Exception
	{
		image = ImageIO.read(new File(path));
		init(image.getWidth(), image.getHeight());
		repaint();	
	}

	public void init(int w, int h)
	{
		setSize(w,h);
		setMinimumSize(new Dimension(w,h));
		setMaximumSize(new Dimension(w,h));
		setPreferredSize(new Dimension(w,h));
	}
	
	public ImagePanel getImagePanel()
	{
		return this;
	}

	public void setImage(BufferedImage img)
	{
		image = img;
		repaint();
	}
	
	public BufferedImage getImage()
	{
		return image;
	}

	public void paint(Graphics g)
	{
		if(image == null ) return;

		//incase of resize make bg color black
		g.setColor(Color.black);
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		g.drawImage(image, 0,0,Color.black, this);
	}
}
