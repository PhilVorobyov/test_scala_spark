#!/usr/bin/env bash

# Required csv file to run spark queries
FILE="./src/main/input_data/train.csv"
JAR="./target/scala-2.11/scala_spark_2.11-0.1.jar"

# Make sure we got the Input else die with an error on screen
for var in "$@"
do
    if [[ "$var" =~ ^(1|2|3)$ ]]; then
        echo "$var is in the list"
    else
        echo "Please enter a task number required to execute"; exit 1;
    fi
done

# Make sure csv file exists else die with an error on the screen
if test -f "$FILE"; then
    echo "$FILE exist"
    else
    echo "Please upload required file $FILE"; exit 1;
fi

# Check that jar is already built
if test -f "$JAR"; then
    echo "$JAR is already built"
    else
    echo "package jar file"; sbt package;
fi

echo "run application"
for var in "$@"
do
    sbt "run $var"
done
