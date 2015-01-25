package associationmininganalysis;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author VISHAL
 */
public class Test extends JFrame 
{
    private JLabel topicText;
    public static JTextArea text;
    private JLabel label;
    private JFrame f; //Main frame
    public static JTextArea ta; // Text area
    private JTextField t;
    private JScrollPane sbrText; // Scroll pane for text area
    private JButton btnQuit; // Quit Program
    private JButton btnCalculate;

    public Test(int threshold)
    {
        ITree        itr;
    	File         f;
    	FPGrowth fpg = new FPGrowth();
    	FPTree pt = new FPTree();
    	CreateFPTree c  =new CreateFPTree();
    	try
        {
    		if (threshold<0)
                {
    			System.out.println("Support is :"+threshold);
    			System.exit(1);
    		}
    	}
        catch(Exception e)
        {
    		System.out.println("Sorry support cant be less than 0");
    		System.exit(1);
    	}
    	c.SetFPTree(pt);
    	f=new File("D://temp.txt");
    	c.start(f);
    	itr=new ITree();
    	fpg.Start(pt,itr,threshold);
    	System.out.println("Done with tree parsing");
    	itr.print(itr.head,new Stack());
//        itr.print(itr.head,new Stack(),text);
    }

    public Test()
    {
        topicText = new JLabel("ASSOCIATION RULE : FP GROWTH ALGORITHM");
        label = new JLabel("Enter the value:");
        f = new JFrame("FP Growth Algorithm Mining");
	f.getContentPane().setLayout(null);
        f.setSize(300,620);
        f.setLocation(400,100);
        f.setTitle("FP Growth Algorithm Mining");
        f.setVisible(true);

        t = new JTextField(5);

        // Create Scrolling Text Area in Swing
        ta = new JTextArea("", 15, 20);
	ta.setLineWrap(true);
	sbrText = new JScrollPane(ta);
	sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        sbrText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// Create Quit Button
        btnQuit = new JButton("Quit");
        btnQuit.addActionListener(
	new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        btnCalculate = new JButton("Calculate");
        btnCalculate.addActionListener(
        new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!(t.getText().equals("")))
                {
                    if(checkIfNum(t.getText()))
                    {
                        System.out.println("sorry");
                        ta.setText("");
                        Test test = new Test(Integer.parseInt(t.getText()));
                    }
                    else
                    {
                        JFrame frame = new JFrame("Number not found!");
                        JOptionPane.showMessageDialog(frame,"Only Numbers are allowed!!!","Numbers not found",JOptionPane.ERROR_MESSAGE);
                        t.setText("");
                    }
                }
                else
                {
                    JFrame frame = new JFrame("Enter threshold value");
                    JOptionPane.showMessageDialog(frame,"Enter threshold value!!!","Empty Space found",JOptionPane.ERROR_MESSAGE);
                }
            }

            private boolean checkIfNum(String text)
            {
                char[] arrText = text.toCharArray();
                for(int i = 0 ; i < text.length() ; i++)
                {
                    if(arrText[i]!='0' && arrText[i]!='1' && arrText[i]!='2' && arrText[i]!='3' && arrText[i]!='4' && arrText[i]!='5' && arrText[i]!='6' && arrText[i]!='7' && arrText[i]!='8' && arrText[i]!='9')
                    {
                        System.out.println("sorry1");
                        return false;
                    }
                    System.out.println("sorry2");
                }
                return true;
            }
        });

    }

    public void addComponent(Container container,Component c,int x,int y,int width,int height)
    {
        c.setBounds(x,y,width,height);
        container.add(c);
    }

    public void launchFrame()
    { // Create Layout
        // Add text area and button to frame

        addComponent(f,topicText,10,10,300,25);
        addComponent(f,label,50,50,100,25);
        addComponent(f,t,150,50,100,25);
        addComponent(f,btnCalculate,85,100,100,25);
        addComponent(f,sbrText,25,150,250,350);
        addComponent(f,btnQuit,85,530,100,25);

//        f.getContentPane().add(label);
//        f.getContentPane().add(t);
//        f.getContentPane().add(btnCalculate);
//	f.getContentPane().add(sbrText);
//        f.getContentPane().add(btnQuit);
		 // Close when the close button is clicked
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.pack(); // Adjusts frame to size of components
        f.setVisible(true);
    }

    public static void main(String args[])
    {
        Test t = new Test();
        t.launchFrame();
    }
}

