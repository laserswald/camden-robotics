package javarobot.chapter6;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

public class SimpleSwing extends JFrame
{
	Container content;

	public SimpleSwing()
	{
		//set the title bar
		super("Vision Project");
		//set the window listener
		addWindowListener(new ExitListener());
		//set initsize
		setSize(320,240);
		//sets contentpane
		content = getContentPane();
		//set initBgColor
		content.setBackground(Color.white);
		//show frame
		setVisible(true);		
	}
	
	public static void main(String[] agrs) throws Exception
	{
		new SimpleSwing();
	}
}

class ExitListener extends WindowAdapter
{
	public void windowClosing(WindowEvent evt)
	{
		System.exit(0);
	}
}
