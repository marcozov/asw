#!/bin/bash

echo Running with lucky word passed as an environment variable
export LUCKY_WORD="Environment variable"
java -jar build/libs/lucky-word.jar

unset LUCKY_WORD
