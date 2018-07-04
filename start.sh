#!/bin/bash

APPLICATION_FILE=./application.properties
JAR_NAME=explorer-inteface-0.0.1-SNAPSHOT.jar

pkill -9 $JAR_NAME

nohup java -jar $JAR_NAME --spring.config.location=$APPLICATION_FILE > log 2>&1 & 