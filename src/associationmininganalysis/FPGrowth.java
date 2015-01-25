package associationmininganalysis;

import associationmininganalysis.ITree.Node;
import java.util.LinkedList;

/**
 *
 * @author VISHAL
 */
public class FPGrowth
{
    int minSupport=1;
    CreateFPTree ct;
    ITree iTree;                                      
    FPGrowth()
    {
    	ct=new CreateFPTree();
    }
    public void Start(FPTree tree,ITree itr,int supp)
    {
    	iTree=itr;
    	minSupport=supp;
    	FP_growth(tree,itr.head);
    }
    
    public void FP_growth(FPTree tree,LinkedList<Node> head)
    {
    	FPTree           branch;                     
    	String           itemName;
    	int              len,i=0,count;
    	LinkedList<Node> l,cl;
    	ITree            itr=new ITree();
    	ITree.Node       node;
 
    	l=head;
    	len=tree.itemTb.Length();
    	while(i<len){
    		count=tree.itemTb.ReadCount(i);
    		if (count<minSupport)
                {
    			i++;
    			continue;
    		}
    		itemName=tree.itemTb.ReadItem(i);
    		node=itr.new Node(itemName,count);
    		l.add(node);
    		branch=ct.ConditionFPT(tree,itemName);  
    	    if (branch==null)
            {
    	        i++;
    	    	continue;
    	    }	
    	    cl=new LinkedList<Node>();
    	    node.cNode=cl;
    		FP_growth(branch,cl);
    		branch=null;
    	    i++;
    	}
    }
}
