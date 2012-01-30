package javarobot.chapter6;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;

public class FilteredImage
{
	private BufferedImage image = null;
	private BufferedImage filteredImage = null;
	private ArrayList<Filter> filters = new ArrayList<Filter>();

	public FilteredImage(String n) throws Exception
	{
		image = ImageIO.read(new File(n));
		resetImage();
	}

	public BufferedImage getFilteredImage()
	{
		return filteredImage;
	}

	public BufferedImage getUnfilteredImage()
	{
		return image;
	}

	public ArrayList getFilters()
	{
		return filters;
	}

	public void setImage(BufferedImage img)
	{
		image = img;
		resetImage();
	}

	public void addFilter(Filter p)
	{
		filters.add(p);
	}

	public void removeFilter(Filter p)
	{
		filters.remove(p);
	}
	
	public void resetImage()
	{
		filteredImage = image;
	}

	public void applyAllFilters()
	{
		resetImage();
		for(Filter f : filters)
		{
			filteredImage = f.invokeFilter(filteredImage);
		}
	}

	//shows a specific filter if it exists in the filters 
	//if it doesn't then add the filter to the list and invoke it
	//NOTE: DOES NOT RESET IMAGE BEFORE IT APPLIES FILTER DO THIS EXTERNALLY
	public void showFilter(Filter f)
	{
		if(filters.contains(f))
		{ 
			filteredImage = filters.get(filters.indexOf(f)).invokeFilter(filteredImage); 
		}
		else
		{ 
			addFilter(f); 
			filteredImage = f.invokeFilter(filteredImage); 
		}
	}
	
	//hide a specific filter by re filtering the image and ignoring the desired filter
	public void hideFilter(Filter f)
	{
		resetImage();
		for(Filter fl : filters)
		{
			if(fl != f)
			{
				filteredImage = fl.invokeFilter(filteredImage);
			}
		}
	}
}
