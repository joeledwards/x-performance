package performance;
public class QueueItem<T>
{
	T item;
	
	QueueItem(T item)
	{
		this.item = item;
	}
	
	public T getItem()
	{
		return item;
	}
}
