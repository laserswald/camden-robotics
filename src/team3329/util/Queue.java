package team3329.util;

/**
 * Noah Harvey
 * @author Developer
 */

import java.util.Vector;

public class Queue {
    
    private Vector list;
    
    public Queue(Vector l)
    {
        this.list = l;
    }
    
    public Queue()
    {
        this.list = new Vector();
    }
    
    //puts a new object at the end of the Queue list
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
    
    //returns the current Queue list
    public Vector getFIFOList()
    {
        return this.list;
    }

    //return current vector length
    public int length()
    {
        return list.size();
    }
}
