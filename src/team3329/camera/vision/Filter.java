package javarobot.chapter6;

import java.awt.image.BufferedImage;

public interface Filter
{
	public BufferedImage invokeFilter(BufferedImage img);
	public String getFilterName();
}
