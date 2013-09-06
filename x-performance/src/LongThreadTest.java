public class LongThreadTest extends ThreadTest<Long>
{
	@Override
	public Long getTestItem()
	{
		return Long.valueOf(Math.round(Math.random() * 4096));
	}
}
