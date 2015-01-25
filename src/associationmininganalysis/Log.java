package associationmininganalysis;

/**
 *
 * @author VISHAL
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.sql.*;
import com.CryptoTools.CryptoBase64;
import com.CryptoTools.CryptoTripleDES;
import com.CryptoTools.CryptoMD5;


public class Log extends JFrame implements ActionListener
{
    Connection con=null;
    JPanel p=new JPanel();
    JLabel l1=new JLabel("USERNAME");
    JLabel l2=new JLabel("PASSWORD");
    JTextField t1=new JTextField(20);
    JPasswordField ps=new JPasswordField(20);
    JButton b=new JButton("SIGNIN");
    
    public Log()
    {
        setSize(300,300);
        setLocation(250,250);
        setTitle("LOGIN:");
        setVisible(true);
        add(p);
        addComponent(p,l1,50,50,100,25);
        addComponent(p,t1,100,50,100,25);
        addComponent(p,l2,50,100,100,25);
        addComponent(p,ps,100,100,100,25);
        addComponent(p,b,75,150,100,25);

        addWindowListener(new WindowAdapter()
            {
		public void windowClosing(WindowEvent evt)
                {
                    
                           System.out.println("Exiting");
                           System.exit(0);
                }
            });

//        MsCon m=new MsCon();
//        m.connect();
//        con=m.con();
        b.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent ae)
           {
                try
                {
                    Statement stmtt=con.createStatement();
                    ResultSet rss=stmtt.executeQuery("select max(tid) from TransactionDettemp");
                    while(rss.next())
                    {
                        String hell = rss.getString(1);
                        //String name = rss.getString(2);

                        System.out.println("this is my hell : "+hell);
                      //  System.out.println("this is my name : "+name);



                    }
                    // CryptoTripleDES des=new CryptoTripleDES();
                    CryptoMD5 md=new CryptoMD5();
                    //des.DeriveKeyFromPassword(t1.getText(),null, 1234);
                    //des.encryptText(t2.getText());
                    md.encryptText(ps.getText());

                    CryptoBase64 b64=new CryptoBase64();
                 //   String ret=b64.encrypt(des.getResult());
                    String ret=b64.encrypt(md.getResult());
                    System.out.println("Message is encrypted!!!="+ret);


                    int flag=0;
                    Statement stmt=con.createStatement();
                    ResultSet rs=stmt.executeQuery("select * from log where user1='"+t1.getText()+"'");
                   while(rs.next())
                   {
                    String user=rs.getString(1);
                    String pass=rs.getString(2);
                    if(pass.equals(ret))
                    {
                            flag=1;
                            new Test();
                       //     setVisible(false);
                            System.out.println("Emp Called");
                            CryptoBase64 bb64=new CryptoBase64();
                            bb64.decrypt(ret);
                            CryptoTripleDES ddes=new CryptoTripleDES();
                            ddes.DeriveKeyFromPassword(t1.getText(), null, 1234);
                            String rret=ddes.decryptText(bb64.getResult());
                            System.out.println("Message is Decrypted + "+rret);

                    }
                    
                    if(flag==0)
                    {
                        System.out.println("WRONG USERNAME OR PASSWORD!!!");
                    }
                    System.out.println(user);
                    System.out.println(pass);
                   }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
           }
        });



    }
    public void addComponent(Container container,Component c,int x,int y,int width,int height)
    {
        c.setBounds(x,y,width,height);
        container.add(c);
    }
    public static void main(String args[])
    {
        Log l=new Log();
    }

    public void actionPerformed(ActionEvent e)
    {

    }
}
