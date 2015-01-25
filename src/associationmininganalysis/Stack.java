package associationmininganalysis;

/**
 *
 * @author VISHAL
 */
class Stack
{
	Elem top;

	void push(Object obj)
        {
		Elem temp;
		temp=top;
		top=new Elem();
		top.obj=obj;
		top.next=temp;
	}
	Object pop()
        {
		Elem temp;
		if (top!=null){
			temp=top;
			top=top.next;
			return temp.obj;
		}
		else return null;
	}
	Stack Invert()
        {
		Stack s=new Stack();
		Elem e;
		e=top;
		while(e!=null){
		    s.push(e.obj);
		    e=e.next;
		}
		return s;
	}

        public Stack clone()
        {
		Stack s=new Stack();
		Elem e,ee;
		e=top;
		ee=new Stack.Elem();
		s.top=ee;
		while(e!=null){
		    ee.obj=e.obj;
		    if (e.next!=null){
		        ee.next=new Stack.Elem();
		        ee=ee.next;
		    }    
		    e=e.next;
		}
		return s;
	}
	void Empty()
        {
		top=null;
	}
	boolean IsEmpty()
        {
		if (top==null)
                {
			return true;
		}
		else return false;
	}
	int Len()
        {
		int i;
		Elem t;
		for(i=0,t=top;t!=null;i++,t=t.next);
		return i;	
		
	}
	
	Stack()
        {
		top=null;
		
	}
	class Elem
        {
		Object obj;
		Elem next; 
	    Elem()
            {
		    obj=null;
		    next=null;
	    }
	}
}
