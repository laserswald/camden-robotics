package javarobot.chapter6;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Threshold extends Object implements Filter
{
	private final String name = "GRAY_THRESHOLD";
	private BufferedImage dstImg = null;
	private int min, max; 
	private FilterType tType = null;

	public Threshold(int mn, int mx, FilterType t)
	{
		setThresholdValues(mn,mx,t);
	}

	//set threshold properties
	public void setThresholdValues(int mn, int mx, FilterType t)
	{
		this.min = mn;
		this.max = mx;
		this.tType = t;
	}

	//getters
	public int getMin(){return this.min;}
	public int getMax(){return this.max;}
	public FilterType getThresholdType(){return this.tType;}
	
	private void getThreshold(BufferedImage img)
	{
		int w = img.getWidth();
		int h = img.getHeight();

		for(int y = 0; y < h; ++y)
		{
			for(int x =0; x < w; ++x)
			{
				int pxl = img.getRGB(x,y);
				Color t = new Color(pxl);
				int dpxl = 0;
				
				if(tType == null){dpxl = compare(pxl,min ,max);}
				else{

				switch(tType)
				{
					case GREY:
						dpxl = compare(t.getRed(),min,max); 
						break;
					case RED:
						dpxl = compare(t.getRed(),min,max);
						break;
					case GREEN:
						dpxl = compare(t.getGreen(),min,max);
						break;
					case BLUE:
						dpxl = compare(t.getBlue(),min,max);
						break;
					case WHITE:
						dpxl = compare((t.getRed()+t.getGreen()+t.getBlue())/3 , 225, 225);
						break;
					case BLACK:
						dpxl = compare((t.getRed()+t.getGreen()+t.getBlue())/3 ,0 ,0);
						break;
					 default:
						dpxl = pxl;
				}}
				
				dstImg.setRGB(x,y,dpxl);
			}
		}
	}

	private int compare(int colorValue, int min, int max)
	{
		if(colorValue >= min && colorValue <= max)
		{
			return Color.WHITE.getRGB();
		}
		else
		{
			return Color.BLACK.getRGB();
		}
	}
	
	public BufferedImage invokeFilter(BufferedImage img)
	{
		dstImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		getThreshold(img);
		return dstImg;
	}

	public String getFilterName(){return name;}
}
