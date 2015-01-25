package associationmininganalysis;
import java.io.*;

/**
 *
 * @author VISHAL
 */
public class CreateFPTree
{
    FPTree t;
	Item itTotal;                           
	File f;
    public void SetFPTree(FPTree t)
    {
    	this.t=t;
    }
    public void start(File f)
    {
    	int num;                               
    	this.f=f;
    	final boolean isTb=false;
    	final boolean isTree=true;
    	itTotal=new Item("head");              
    	
    	ReadFromTxt(isTb);                     
    	num=NumOfItem(itTotal);                
    	t.ConsItemTb(num);                     
    	SortItem(itTotal);                     
    	Insert(itTotal,t.itemTb);              
    	ReadFromTxt(isTree);
    	LinkItem(t); 
    }
    public void LinkItem(FPTree t)
    {
    	FPTree.Node d;
    	int num;
    	num=t.itemTb.Length();
    	
    	String[] item=new String[num];
    	FPTree.Node[] node=new FPTree.Node[num];;
    	t.itemTb.CopyItemArray(item);
    	
    	Queue<FPTree.Node> q=new Queue();
    	d=t.tree.root;
    	inQue(q,d);
    	while(!q.IsEmpty())
        {
    		d=q.out();
    		inQue(q,d);
    		num=FindNum(item,d.item);
    		if (num==-1)
    			System.out.println("Exception:No found item");
    		else{
    			if (node[num]==null)
                        {
    				node[num]=d;
    				t.itemTb.SetNode(d,num);
    			}
    			else
                        {
    			    node[num].bnode=d;
    			    node[num]=d;
    			}
                    }
    	}
    	
    }
    private Item  ReadOneTrans(Item head,String s)
    {
    	int    start=0,end=0,n=1;
    	String name;
    	Item   it;
    	
    	it  =head;
    	s   =s.trim();
		start=0;
		end  =s.indexOf(" ");
    	while(start>=0){		    
		    if (end>=0)
			    name=s.substring(start+1,end);
		    else 
		    	name=s.substring(start+1);
		    start=end;
		    end=s.indexOf(" ",start+1);
		    if (n++==1) continue;
		    it.next=new Item(name);
		    it=it.next;
		}
    	return head;
    }
    private void InsertItTotal(Item head)
    {
    	String name;
    	Item   item=head.next; 
    	Item   it=new Item("");
    	
    	while(item!=null){
    	    name=item.name;
    	    it=find(itTotal,name);
	        if (it!=null){
			    it.count=it.count+item.count;
		    }	
		    else{
			    it=goTail(itTotal);
		    	it.next=new Item(name);
		    	it.next.count=item.count;
		    }
	        item=item.next; 
    	}
    }
   
    private int NumOfItem(Item head)
    {
    	Item it;
    	int i;
    	
    	it=head.next;
    	for(i=0;it!=null;i++,it=it.next);
    	return i;
    }
    private Item goTail(Item head)
    {
    	Item it;
    	it=head;
    	while(it.next!=null){
    		it=it.next;
    	}
    	return it;
    }
    private void ReadFromTxt(boolean isTree)
    {
    	Item itInTran;
    	String s;
    	BufferedReader fis;
    	itInTran=new Item("head");            
    	
    	try{
    		fis=new BufferedReader(new FileReader(f));
    		while((s=fis.readLine())!=null){
    		    itInTran=ReadOneTrans(itInTran,s);
    		    if (isTree)
                    {
    			    Sort.sort(itInTran,t);
    		    	InsertTree(itInTran,t.tree);
    		    }
    		    	else
    			    InsertItTotal(itInTran);
    		    
    	    }
    	}
    	catch(IOException e){
    		System.out.println(e.getMessage());
    	}
    }
    private Item find(Item item,String name)
    {
		while((item!=null)&&(!item.name.equals(name)))
                {
			item=item.next;
		}
		if (item==null)
    	    return null;
		else return item; 
    }
    private void SortItem(Item h)
    {
    	Item it,temp,prep,end=null;
    	it=h.next;
    	prep=h;
    	while(it.next!=end)
        {
    	    while(it.next!=end)
            {
    		    if (it.count<it.next.count)
                    {
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
    private void Insert(Item head,FPTree.ItemTb t)
    {
    	int n=0;
    	Item item;
    	item=head.next;
    	while(item!=null)
        {
    		t.Insert(item.name,n++,item.count,null);
    		item=item.next;
    	}
    }
    private void InsertTree(Item head,FPTree.Tree t)
    {
    	FPTree.Node node,cNode;	
    	Item it;
    	String name;
    	   	
    	it=head.next;
    	node=t.root;
    	while(it!=null)
        {
    	    name=it.name;
    	    cNode=node.FindChild(node,name);
	        if (cNode!=null)
                {
	    	    cNode.IncCount(it.count);
	    	    node=cNode;
	        }else node=node.InsertNode(name,it.count);
	        it=it.next;
    	}
    }
    private void buildLink(FPTree t)
    {
    	FPTree.Node d;
    	int num;
    	num=t.itemTb.Length();
    	
    	String[] item=new String[num];
    	FPTree.Node[] node=new FPTree.Node[num];;
    	t.itemTb.CopyItemArray(item);
    	
    	Queue q=new Queue();
    	d=t.tree.root;
    	inQue(q,d);
    	while(!q.IsEmpty())
        {
    		d=(FPTree.Node)q.out();
    		inQue(q,d);
    		num=FindNum(item,d.item);
    		if (num==-1)
    			System.out.println("Exception:No found item");
    		else{
    			if (node[num]==null)
                        {
    				node[num]=d;
    				t.itemTb.SetNode(d,num);
    			}
    			else
                        {
    			    node[num].bnode=d;
    			    node[num]=d;
    			}
    		}
//    		System.out.println("name:"+d.item+"/parent:"+d.parent.item+"/count:"+d.count);
    	}
    	
    }
    public int FindNum(String[] item,String name)
    {
    	int num=0,len;
    	len=item.length;
    	while(num<len)
        {
    		if (name.equals(item[num]))
    		    return num;
    		num++;
    	}
    	return -1;
    }
    public FPTree ConditionFPT(FPTree tree,String item)
    {
    	FPTree tjFPT=new FPTree();
    	FPTree.Node node,nd;
    	Item its,cpb;                         
    	int num,supp;                         
    	
    	itTotal=new Item("head");
    	its=new Item("head");
    	node=tree.itemTb.FindNode(item);
    	nd=node;
    	cpb=its;
    	while(nd!=null)
        {
    		supp=nd.count;
    		node=nd.parent;
    		its=cpb;
    	    while(!node.item.equals("root"))
            {
    		    its.next=new Item(node.item);	    
    		    its.next.count=supp;
    		    its=its.next;
    		    node=node.parent;
    	    }
    	    if (cpb.next!=null)
            {
    	        InsertItTotal(cpb);
    	    }
    	    nd=nd.bnode;
    	    
    	}
    	num=NumOfItem(itTotal);              
    	if (num==0) return null;             
    	tjFPT.ConsItemTb(num);               
    	SortItem(itTotal);                   
    	Insert(itTotal,tjFPT.itemTb);        
    	
    	its=new Item("");
    	node=tree.itemTb.FindNode(item);
    	nd=node;
    	cpb=its;
    	while(nd!=null)
        {
    		supp=nd.count;
    		node=nd.parent;
    		its=cpb;
    		
    	    while(!node.item.equals("root"))
            {
    		    its.next=new Item(node.item); 	    	
    	    	its.next.count=supp;
    	    	its=its.next;
    	    	node=node.parent;
    	    }
    	    if(cpb.next!=null)
            {
    	        Sort.sort(cpb,tjFPT);
    	        InsertTree(cpb,tjFPT.tree);    
    	    }
    	    nd=nd.bnode;
    	}
    	buildLink(tjFPT);                      
    	return tjFPT;  	
    }
    private void inQue(Queue q,FPTree.Node n)
    {
    	FPTree.Node.Cnode c;
    	c=n.child;
    	while (c!=null)
        {
    		q.in(c.link);
    		c=c.next;
    	}	
    }
}

