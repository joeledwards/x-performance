public class Timing
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Java sleep and timestamp tests:");

        for (int i = 0; i < 10; i++)
        {
            long start = System.nanoTime();
            Thread.sleep(0, 1);
            long end = System.nanoTime();
            long duration = end - start;

            System.out.println("Sleep Duration: " + duration + " ns, expeced 1 ns");
        }

        for (int i = 0; i < 10; i++)
        {
            long start = System.nanoTime();
            long end = System.nanoTime();
            System.out.println("Timestamp took " + (end - start) + " ns");
        }
    }
}
