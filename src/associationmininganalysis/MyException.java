package associationmininganalysis;
/**
 *
 * @author VISHAL
 */
public class MyException extends Exception
{
    private int exceptNumber;
    MyException (int a)
    {
    	exceptNumber=a;
    }
    @Override
    public String toString()
    {
    	return "MyException["+exceptNumber+"]";
    }
}
