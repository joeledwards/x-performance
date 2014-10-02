import java.util.LinkedList;

public class OutOfMem
{
    public static void main(String[] args)
    {
        LinkedList<long[]> list = new LinkedList<>();

        long last_long = 0;

        while(true)
        {
            long[] block = new long[4096];

            for(int i = 0; i < 4096; i++)
                block[i] = last_long++;

            list.add(block);
            
            if(list.size() % 250 == 0) 
                System.out.format("There are %,d blocks in the list.\n", list.size());
        }
    }
}
