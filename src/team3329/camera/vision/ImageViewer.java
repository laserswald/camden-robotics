package javarobot.chapter6;

import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class ImageViewer extends SimpleSwing
{
	public ArrayList<ImagePanel> panels = new ArrayList<ImagePanel>();

	public ImageViewer(BufferedImage bimg)
	{
		setTitle("Image Viewer");
		addPanel(bimg);
	}

	public ImageViewer(String path) throws Exception
	{
		setTitle("Image Viewer - "+path);
		addPanel(path);	
	}

	public void init()
	{
		//add the panels
		JPanel pnl = new JPanel();
		FlowLayout layout = new FlowLayout(FlowLayout.LEADING,0,0);
		pnl.setLayout(layout);
		
		int w =0; int h=0;
		for(ImagePanel j : panels)
		{
			pnl.add(j);
			w = j.getWidth() + w + 2;
			h = j.getHeight() > h ? j.getHeight() : h;
		}
		
		setSize(w,h);	
		getContentPane().add(pnl);

		setVisible(true);

		//set the images to the panels
		for(ImagePanel k : panels)
		{
			k.setImage(k.getImage());
		}
	}

	

	public void addPanel(ImagePanel pnl)
	{
		panels.add(pnl);
	}

	public void addPanel(String path) throws Exception
	{
		panels.add(new ImagePanel(path));
	}
	
	public void addPanel(BufferedImage img)
	{
		panels.add(new ImagePanel(img));
	}

	public void removePanel(ImagePanel pnl)
	{
		panels.remove(pnl);
	}

	public static void main(String[] args)
	{
		ImageViewer viewer = null;
		try
		{
			//create a new image that is able to be filtered
			FilteredImage image1 = new FilteredImage("/home/boys/Pictures/GlobeAnchorandEagle.jpg");
			viewer = new ImageViewer(image1.getFilteredImage());
			
			image1.showFilter(new ToColor(FilterType.GREEN)); //add and apply filters
			viewer.addPanel(image1.getFilteredImage());
			
			image1.resetImage();
			image1.showFilter(new Threshold(100,150,FilterType.GREY));
			viewer.addPanel(image1.getFilteredImage());

			viewer.init();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
}
