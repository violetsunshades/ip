#!/usr/bin/env bash

if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

if ! javac -cp ../src/main/java -Xlint:none -d ../bin $(find ../src/main/java -name "*.java")
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

java -classpath ../bin navis.Navis < input.txt > ACTUAL.TXT

diff ACTUAL.TXT EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi