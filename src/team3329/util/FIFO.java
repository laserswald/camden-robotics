package team3329.util;

/**
 * Noah Harvey
 * @author Developer
 */

import java.util.Vector;

public class FIFO {
    
    private Vector list;
    
    public FIFO(Vector l)
    {
        this.list = l;
    }
    
    public FIFO()
    {
        this.list = new Vector();
    }
    
    //puts a new object at the end of the FIFO list
    public void add(Object o)
    {
        list.addElement(o);
    }
    
    //pulls first object from the list and returns it
    public Object pull()
    {
        Object rt = list.firstElement();
        list.removeElementAt(0);
        
        return rt;
    }
    
    //returns the object at a given index
    public Object search(int index)
    {
        return list.elementAt(index);
    }
    
    //returns the current FIFO list
    public Vector getFIFOList()
    {
        return this.list;
    }
}
