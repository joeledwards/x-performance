#include <stdio.h>
#include <time.h>
#include <sys/time.h>
//#include <linux/time.h>

int main(int argc, char **argv)
{
    struct timespec remainder;
    struct timespec spec;
    long start;
    long end;
    long duration;
    int i;

    printf("C sleep and timestamp tests:\n");

    for(i = 0; i < 10; i++)
    {
        clock_gettime(CLOCK_REALTIME, &spec);
        start = spec.tv_sec * 1000000000 + spec.tv_nsec;

        spec.tv_sec = 0;
        spec.tv_nsec = 1;
        nanosleep(&spec, &remainder);
        
        clock_gettime(CLOCK_REALTIME, &spec);
        end = spec.tv_sec * 1000000000 + spec.tv_nsec;

        duration = end - start;

        printf("Sleep Duration: %ld ns, expected 1 ns\n", duration);
    }

    for(i = 0; i < 10; i++)
    {
        clock_gettime(CLOCK_REALTIME, &spec);
        start = spec.tv_sec * 1000000000 + spec.tv_nsec;

        clock_gettime(CLOCK_REALTIME, &spec);
        end = spec.tv_sec * 1000000000 + spec.tv_nsec;

        duration = end - start;
        printf("Timestamp took %ld ns, expected 1 ns\n", duration);
    }

    return 0;
}
