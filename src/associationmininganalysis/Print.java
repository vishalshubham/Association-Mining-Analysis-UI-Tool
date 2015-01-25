package  associationmininganalysis;

import associationmininganalysis.ITree.Node;
//import output.Output;

/**
 *
 * @author VISHAL
 */
public class Print
{
        static void print(Stack s,String type)
        {
    	int count=0;
    	Object obj;
    	String str;
    	Node   node;
    	Stack  a=new Stack();
    	Stack.Elem e;
    	e=s.top;
    	if (type.equalsIgnoreCase("node")){
    		node=(Node)e.obj;
    		count=node.count;
    	}
    	while(e!=null){
    		a.push(e.obj);
    		e=e.next;
    	}
    	e=a.top;
    	while(!a.IsEmpty()){
    		obj=a.pop();
    		
    		if (type.equalsIgnoreCase("item"))
                {
    		}
    		else if(type.equalsIgnoreCase("string"))
                {
    			str=(String)obj;
    			System.out.print(str+"-");
                        Test.ta.append(str+"-");
    		}
    		else if (type.equalsIgnoreCase("node"))
                {
    			node=(Node)obj;
    			System.out.print(node.name+"-");
                        Test.ta.append(node.name+"-");
    		}
    		    
    	}
    	if (type.equalsIgnoreCase("node"))
        {
                Test.ta.append(count+"");
    		System.out.println(count);
    	}
    	else System.out.println();
    	a=null;
    }
}