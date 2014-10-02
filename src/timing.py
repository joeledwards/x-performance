#!/usr/bin/env python
import time
import threading

print "Pyton sleep and timestamp tests:"

for i in xrange(0, 10):
    start = time.time()
    time.sleep(0.000000001)
    end = time.time()
    duration = long((end - start) * 1000000000.0)

    print "Sleep Duration: " + str(duration) + " ns, expeced 1 ns"

for i in xrange(0, 10):
    start = time.time()
    end = time.time()
    duration = long((end - start) * 1000000000.0)

    print "Timestamp took " + str(duration) + " ns, expeced 1 ns"
