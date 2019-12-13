#!/bin/bash

echo Cleaning all projects

PATH_TO_SCRIPT=`dirname`

PROJECTS=$(ls ${PATH_TO_SCRIPT}/*/build.gradle)

for project in ${PROJECTS}; do
	echo ""
	echo "Now cleaning ${project}"
	gradle --build-file ${project} clean
done
