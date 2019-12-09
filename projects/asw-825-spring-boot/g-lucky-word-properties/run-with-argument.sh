#!/bin/bash

export BAD_LUCK="bad luck setup!"
echo Running with lucky word passed as an argument
java -jar build/libs/lucky-word.jar --lucky.word=Argument

unset BAD_LUCK
