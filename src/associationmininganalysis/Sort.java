package associationmininganalysis;
import java.util.Comparator;

/**
 *
 * @author VISHAL
 */
class Sort
{
    static void sort(Stack s)
    {
        sort(s,null);
    }
    static void sort(Stack s,String order[])
    {
	    Stack.Elem it,temp,prep,end=null,top;
	    Stack stack=new Stack();
	    int result;
	    top=s.top;	
	    it=top;
	    prep=stack.new Elem();
	    prep.next=top;
	    top=prep;
	  
	    while(it.next!=end){
	  	    while(it.next!=end){
	  		    if ((result=Comp.compare(it.obj,it.next.obj,order))==-1){
	  			    temp=it.next;
	  			    it.next=it.next.next;
	  			    prep.next=temp;
	  			    temp.next=it;
	  		    }
	  		    prep=prep.next;
	  		    it=prep.next;
	  	    }
	  	    end=it;
	  	    it=top.next;
	  	    prep=top;
	   }
	   s.top=top.next;
	   top=null;
	   stack=null;
  }
  static void sort(Item h,FPTree tree)
  {
	  String order[]=new String[tree.itemTb.Length()];
	  tree.itemTb.CopyItemArray(order);
	  sort(h,order);
  }
  static void sort(Item h,String order[])
  {
  	Item it,temp,prep,end=null;
  	int result;
  	
  	it=h.next;
  	prep=h;
  	while(it.next!=end){
  	    while(it.next!=end){
  		    if ((result=Comp.compare(it.name,it.next.name,order))==-1){
  			    temp=it.next;
  			    it.next=it.next.next;
  			    prep.next=temp;
  			    temp.next=it;
  		    }
  		    prep=prep.next;
  		    it=prep.next;
  	    }
  	    end=it;
  	    it=h.next;
  	    prep=h;
  	}
  }
  static int sort(Item h)
  {
	 Item it,maxIt=null,temp,prep,r;//it��¼��ǰ����λ��
	 int max,len=0;
	 
	 it=h;
	 prep=h;
	 if(it==null||it.next==null) return 0;
	 for(;it.next!=null;len++){
	     max=0;
	     r=it;
		 while(r.next!=null){
		     if (r.next.count>max){
	    	     maxIt=r.next;
	    	     max=r.next.count;
	    	     prep=r;
	         }
		     r=r.next;
	     }
		 
		 prep.next=maxIt.next;
		 temp=it.next;
		 it.next=maxIt;
		 maxIt.next=temp;
		 it=maxIt;
	 }
	 return len;
  }
}
class Comp implements Comparator
{
	public int compare(Object a,Object b)
        {
		int r=0;
		r=(a.toString()).compareTo(b.toString()); 
		return r;
	}
	public int compare(int a,int b)
        {
		int r;
		if (a==b)return 0;
		return r=a>b?1:-1;
	}
	static int compare(Object a,Object b,String order[])
        {
		return compare(a.toString(),b.toString(),order);
	}
	static int compare(String a,String b,String order[])
        {
    	int num,i=0,result=0;
    	String name;
    	num=order.length;
    	while(i<num){
    		name=order[i];
    		if (name.equals(a))
    			{result=1;return result;}
    		else if (name.equals(b))
    			{result=-1;return result;}
    		else
    			i++;
    	}
    	return result;  	
	}
}
