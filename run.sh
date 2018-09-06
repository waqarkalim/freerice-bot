#!/bin/bash

javac -cp lib/client-combined-3.13.0.jar:lib/guava-25.0-jre.jar:lib/httpclient-4.5.5.jar:lib/httpcore-4.4.9.jar:lib/okhttp-3.10.0.jar:lib/okio-1.14.1.jar:lib/commons-logging-1.2.jar:lib/selenium-server-standalone-3.13.0.jar:lib/jsoup-1.11.3.jar:json-simple-1.1.jar:. freerice.java

echo "Compiled"

java -cp lib/client-combined-3.13.0.jar:lib/guava-25.0-jre.jar:lib/httpclient-4.5.5.jar:lib/httpcore-4.4.9.jar:lib/okhttp-3.10.0.jar:lib/okio-1.14.1.jar:lib/commons-logging-1.2.jar:lib/selenium-server-standalone-3.13.0.jar:lib/jsoup-1.11.3.jar:json-simple-1.1.jar:. freerice
