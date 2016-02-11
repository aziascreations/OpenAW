#!/bin/bash

#This script is used to avoid problems where the JVM doesn't have the correct "relative path".

cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
exec java -jar ./game.jar -s -p 27030