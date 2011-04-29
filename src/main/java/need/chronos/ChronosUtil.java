package need.chronos;

public class ChronosUtil
{

	public static boolean StringIsNumber(String string)
	{
		char[] chars=string.toCharArray();
		for(int i=0;i<chars.length;i++)
		{
			if(!CharIsNumber(chars[i]) || (chars[i] == 45 && i == 0))
			{
				return false;
			}
		}
		return true;
	}
	
	public static boolean CharIsNumber(char c)
	{
		return 47 < c && c < 58;
	}
}
