package javarobot.chapter6;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class ToColor implements Filter
{
	private final String name = "TO_GRAY";	
	private BufferedImage dstImg = null;
	private FilterType filtertype = null;
	
	public ToColor(FilterType color)
	{
		this.filtertype = color;
	}
	
	private void toGray(BufferedImage img)
	{
		int h = img.getHeight();
		int w = img.getWidth();

		for(int y = 0; y < h; ++y)
		{
			for(int x = 0; x < w; ++x)
			{
				int srcPixel = img.getRGB(x,y);  //get pixel
				Color g = getColor(new Color(srcPixel)); //get the gray color
				dstImg.setRGB(x,y,g.getRGB()); //set the new pixel value
			}
		}
		
	}
	
	//average the rgb values of the pixel and set r,g,b = avg value
	private Color getColor(Color color)
	{
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		int gr = (int)((r+g+b)/(3.0));
		
		switch(filtertype)
		{
			case GREY:
				return new Color(gr,gr,gr);
			case GREEN:
				return new Color(0,gr,0);
			case RED:
				return new Color(gr,0,0);
			case BLUE:
				return new Color(0,0,gr);
			case ALPHA: 
				return new Color((float)r,(float)g,(float)b,(float)(gr)/(225));
			default: 
				return new Color(r,g,b);
		}
	}
	
	//store the parameter image to send back restored image
	public BufferedImage invokeFilter(BufferedImage img)
	{
		dstImg = new BufferedImage(img.getWidth(),img.getHeight(), BufferedImage.TYPE_INT_RGB);
		toGray(img);
		return dstImg;
	}

	public String getFilterName()
	{
		return name;
	}
}
