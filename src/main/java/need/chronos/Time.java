package need.chronos;

public class Time
{
	private boolean relative;
	private long time;
	
	public Time(long time, boolean relative)
	{
		this.time = time;
		this.relative = relative;
	}
	
	public long getTime()
	{
		return time;
	}
	
	public boolean isRelative()
	{
		return relative;
	}
}