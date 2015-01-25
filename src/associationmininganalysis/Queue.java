package associationmininganalysis;

/**
 *
 * @author VISHAL
 */
class Queue<T>
{
	Elem head,tail,elem;
	void in(T node)
        {
		elem=new Elem(node);
		if ((head==null)&&(tail==null)){
		    tail=elem;
		    head=elem;
		}    
		else{
			tail.next=elem;
			tail=elem;
		}	
	}
	T out()
        {
		Elem e;
		if ((head==null)&&(tail==null)){
			return null;
		}
		else if(head==tail){
			e=head;
			head=tail=null;
		    return e.node;
		}
		else{
			e=head;
			head=head.next;
		    return e.node;
		}
	}
	boolean IsEmpty()
        {
            if ((head==null)&&(tail==null))
            {
		return true;
            }
            return false;
	}
	void Empty()
        {
		head=null;
		tail=null;
	}
	Queue()
        {
		head=null;
		tail=null;
		
		
	}
	class Elem
        {
		T node;
		Elem next;
		Elem(T node)
                {
			this.node=node;
			next=null;
		}
	}	
}   
