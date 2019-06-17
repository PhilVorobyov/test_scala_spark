#!/usr/bin/env bash

# Make sure we got the Input else die with an error on screen
for var in "$@"
do
    if [[ "$var" =~ ^(batch|stream)$ ]]; then
        echo "start $var processing";
        sbt "run $var"
    elif [[ "$var" =~ ^(1|2|3|4|5|6|7|8|9)$ ]]; then
    echo "start producer with $var threads"
    else
        echo "Please enter processing type or number of threads"; exit 1;
    fi
done
echo "sdfdsf"
# Make sure csv file exists else die with an error on the screen
#if test -f "$FILE"; then
#    echo "$FILE exist"
#    else
#    echo "Please upload required file $FILE"; exit 1;
#fi
#
## Check that jar is already built
#if test -f "$JAR"; then
#    echo "$JAR is already built"
#    else
#    echo "package jar file"; sbt package;
#fi
#
echo "run application"
for var in "$@"
do
    sbt "run $var"
done
