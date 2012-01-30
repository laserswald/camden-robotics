Vision Processing
Version: 0.0.1
Author Noah Harvey

Note: All code that is currently written in package: VisionProcessing_v001 is rough, 
so then please exuse any compile time errors.

Installing Package: 

Unzip the package VisionProcessing_v001.zip and dump all files 
in a directory of: ...\javarobot\chapter6 (Windows) or .../javarobot/chapter6 (Linux);

The source files are located in the src directory in the package.

Java Package of source files: javarobot/chapter6

Changing  the Image: 
Edit the file ImageViewer.java in the main(String[] args)... method to change run time objects. 

How the code works: 

SimpleSwing: extends JFrame: a basic gui system that displays a window and holds a windowExit event 

ImageViewer: extends SimpleSwing: the GUI manager of the system. It loads the JFrame window as  well as handles
the containment of all FilteredImages and Panels.

ImagePanel: extends JPanel: holds and maintains the display of a BufferedImage through java.awt.Graphics methods

FilteredImage: class that is used to hold, apply and remove image filters. It contains two sets of methods
for hanlding the application of and removal of filters. One set is to apply and remove all filters on an image. 
The second is to allow the system to apply and remove 1 filter at a time. 

Filter: interface that must be implemented for custom filters to be applied. 

ToColor: filter class that filters an image to gray scale, green scale, or blue scale

Threshold: filter class that returns an binary image based on threshold values and a given type of threshold.

FilterType: enum: color relationship to filter. Used in ToColor and Threshold.
