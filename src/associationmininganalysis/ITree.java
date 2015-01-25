package  associationmininganalysis;

//import output.Output;
import java.io.Serializable;
import java.util.*;
/**
 * ITree��
 * 05.12.10���ڶ���
 * ITree����ͬһ��ε��ֵܽ��֮�䰴����Ŀ�����������
 * ��LinkedList�洢ͬ����ֵܽ��
 * 
 *              _______________________
 *   head  --->|__|____|___|___|___|___|
 *                |        |       |
 *                V        |       |
 *          ����LinkedList  |       |
 *                         V       |
 *                                 |
 *                                 |     _______________________
 *                 ����LinkedList   ---->|__|____|___|___|___|___|           
 *                                         |        |       |
 *                                         V        V       V
 * */

/**
 *
 * @author VISHAL
 */


public class ITree implements Serializable
{
	private int total;                         
	private double sCount;                     
	public LinkedList<Node> head;
	int n=0;
	public ITree()
        {
		this.total=0;
		this.sCount=0;
		head=new LinkedList<ITree.Node>();
	}
	public ITree(int total,int sCount)
        {
		this.total=total;
		this.sCount=sCount;
		head=new LinkedList<ITree.Node>();
	}
	void addTotal(int inc)
        {
		total+=inc;
	}
	double readSCount()
        {
		return sCount;
	}
	void setSCount(double n)
        {
		sCount=n;
	}
	void Insert2(Stack s)
        {
		Stack ss;
		double count;
		Node top;
		
		top=(Node)s.top.obj;
		count=top.count;
		ss=s.clone();
		Sort.sort(ss);                        	
		InsertToITree(ss,count);
		ss=null;
	}
	private void InsertToITree(Stack s,double count)
        {
		int n;
		Node node=null,cn=null;
		LinkedList<Node> l;
		String itemName;
		int comp;
		
        if (s==null||s.IsEmpty()) return;  
		if(head==null) head=new LinkedList<Node>();
		l=head;
        while(!s.IsEmpty())
        {
            node=(Node)s.pop();
            node.count=0;
			itemName=node.name;
			if(l.size()==0){ 
				l.add(node);
				l=new LinkedList<Node>();
				node.cNode=l;
				continue;
			}	
			for(int i=0;i<l.size();i++){
				cn=(Node)l.get(i);
				comp=compare(itemName,cn.toString());
				if (comp==0){                  
					l=cn.cNode;
					node=cn;
					if(l==null){
						l=new LinkedList<Node>();
						cn.cNode=l;
					}	
					break;
				}else if(comp<0){              
				    l.add(i,node);	
				    l=new LinkedList<Node>();
				    node.cNode=l;
				    break;
				}else if(i==l.size()-1){       
					l.add(node);
					l=new LinkedList<Node>();
					node.cNode=l;
					break;
				}
			}
			
		}
		node.count+=count;                   
	}
	private int compare(String it1,String it2)
        {
    	int i1,i2;
    	it1=it1.trim();
    	it2=it2.trim();
    	i1=Integer.parseInt(it1.substring(1));
    	i2=Integer.parseInt(it2.substring(1));
    	if (i1>i2)return 1;
    	else if(i1==i2)return 0;
    	return -1;
        }
	void empty()
        {
		head=null;
	}
	boolean IsEmpty()
        {
		if (head==null||head.size()==0)
			return true;
		else return false;
	}
	void print(LinkedList<Node> h,Stack s)
        {
		Node node=null;
		if(h==null)
                {
			System.out.print(n+":");
                        Test.ta.append(n+++":");

                        
	    	Print.print(s,"node");
                                        Test.ta.append("\n");

			return;
		}
	    for(int i=0;i<h.size();i++)
            {
	    	node=h.get(i);	  
	    	s.push(node);	    	
	    	print(node.cNode,s);
	    	s.pop();   	
	    }
	}
	public class Node implements Serializable
        {
	public int count;
    	public String name;
    	public LinkedList<Node> cNode; 
    	Node(String name)
        {
    		count=0;
    		this.name=name;
    	}
    	public Node(String name,int count)
        {
    		this.count=count;
    		this.name=name;
    	}
    	public String toString()
        {
    		return name;
    	}
	}
}
