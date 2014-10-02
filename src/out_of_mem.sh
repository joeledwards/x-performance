#!/bin/bash
echo "Compiling OutOfMem.java ..."
javac OutOfMem.java
if [ $? -eq 0 ]; then
    echo ""
    echo ""
    echo "Running OutOfMem ..."
    time java -XX:HeapDumpPath=. -XX:+HeapDumpOnOutOfMemoryError -Xmx2048M OutOfMem
else
    echo "Compile failed!"
fi

