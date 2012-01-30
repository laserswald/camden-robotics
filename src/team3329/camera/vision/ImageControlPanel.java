/*
 * Control Panel for the image viewe.java
 * Acts as the UI for the program
 */

package javarobot.chapter6;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;
import javax.swing.MenuElement;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.MenuSelectionManager;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ImageControlPanel extends JPopupMenu
{
	//gui vars
	private JMenuItem addPnlButton;
	private JMenuItem rmvPnlButton;
	private JMenuItem chgImgButton;
	private JMenuItem appFlrButton;
	private JMenu     thrshldPanel;
		private JRadioButtonMenuItem thrldOn;
		private JRadioButtonMenuItem thrldOff;
		private SliderMenuItem thrldSettings; 

	//Image vars 
	private Hashtable<ImagePanel, FilteredImage> panels = new Hashtable<ImagePanel, FilteredImage>();
	private ArrayList<ImageUpdateListener> listeners = new ArrayList<ImageUpdateListener>();
	
	private static ImagePanel currentImagePanel = null;

	public ImageControlPanel()
	{
		super();
		init();
	}

	public ImageControlPanel(FilteredImage imgName)
	{
		addImage(imgName);
		init();
	}

	private void init()
	{
		//create and add all gui items to menu
		addPnlButton = new JMenuItem("Add Panel");
		rmvPnlButton = new JMenuItem("Remove Panel");
		chgImgButton = new JMenuItem("Change Image");
		appFlrButton = new JMenuItem("Apply Filter");
		thrshldPanel = new JMenu("Threshold");
			thrldOn = new JRadioButtonMenuItem("On");
			thrldOff = new JRadioButtonMenuItem("Off");
			thrldSettings = new SliderMenuItem();
			
			thrshldPanel.add(thrldOn);
			thrshldPanel.add(thrldOff);
			thrshldPanel.add(thrldSettings);
		add(addPnlButton);
		add(rmvPnlButton);
		add(chgImgButton);
		add(appFlrButton);
		add(thrshldPanel);
	}

	public void addImage(FilteredImage Image)
	{
		panels.put(new ImagePanel(Image.getUnfilteredImage()), Image);
		;//later add gui interact
	}

	public static ImagePanel getCurrentImagePanel()
	{
		return currentImagePanel;
	}

	private static void updateCurrentImagePanel(ImagePanel pnl)
	{
		currentImagePanel = pnl;
	}

	public void removePanel(ImagePanel pnl)
	{
		panels.remove(pnl);
		;//later add gui interact
	}

	public void changeImage(ImagePanel pnl, FilteredImage img)
	{
		panels.remove(pnl);
		panels.put(pnl, img);
	}

	public FilteredImage getContainedImage(ImagePanel pnl)
	{
		return panels.get(pnl);
	}

	public void addImageUpdateListener(ImageUpdateListener lstnr)
	{
		listeners.add(lstnr);
	}

	public void removeImageUpateListener(ImageUpdateListener lstnr)
	{
		listeners.remove(lstnr);
	}

	public void fireImageUpdateEvent(FilteredImage img)
	{
		for(ImageUpdateListener l : listeners)
		{
			l.updateImage(img);
		}
	}
} 

class SliderMenuItem extends JPanel implements MenuElement, ImageUpdateListener
{
	private JSlider minField = new JSlider(0,225,0);
	private JSlider maxField = new JSlider(0,225,225);
	private String[] t = {"GREY","RED","GREEN","BLUE"};
	private JComboBox filterType = new JComboBox(t);
	private FilteredImage currentImage  = null;
	private int min =0;
	private int max =255;
	private FilterType filtertype = FilterType.GREY;

	public SliderMenuItem()
	{
		super();

		//set the slider sizes
		minField.setSize(new Dimension(150,minField.getSize().height));
		maxField.setSize(new Dimension(150,maxField.getSize().height));
	
		minField.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				JSlider src = (JSlider)e.getSource();
				min = (int)src.getValue();
				changeThreshold();
			}
		}); 	
	
		maxField.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				JSlider src = (JSlider)e.getSource();
				max = (int)src.getValue();
				changeThreshold();
			}
		});

		filterType.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JComboBox bx = (JComboBox)e.getSource();
				
				String type = (String)bx.getSelectedItem();
				
				if(type.equals("GREY"))
						filtertype = FilterType.GREY;
				else if(type.equals("RED"))
						filtertype = FilterType.RED;
				else if(type.equals("GREEN"))
						filtertype = FilterType.GREEN;
				else if(type.equals("BLUE"))
						filtertype = FilterType.BLUE;
	
				changeThreshold();
			}
		});

		//set the layout of the panel
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

		//add the components
		add(minField);
		add(maxField);
	}

	public void updateImage(FilteredImage img)
	{
		currentImage = img;
	}

	private void changeThreshold()
	{
		if(currentImage == null) return;
		
		Threshold thr = new Threshold(min,max,filtertype);
		
		ArrayList<Filter> fltrs = currentImage.getFilters();
		for(Filter f : fltrs)
		{
			fltrs.remove(thr);
		}

		currentImage.showFilter(thr);	
		ImageControlPanel.getCurrentImagePanel().setImage(currentImage.getUnfilteredImage());
	}
	
	//MenuElement methods
    	public void processMouseEvent(MouseEvent e, MenuElement path[], MenuSelectionManager manager) {;}

    	public void processKeyEvent(KeyEvent e, MenuElement path[], MenuSelectionManager manager) {;}

   	public void menuSelectionChanged(boolean isIncluded) {;}

    	public MenuElement[] getSubElements() 
	{
		return new MenuElement[0];
    	}

    	public Component getComponent() 
	{
      		return this;
    	}
}
