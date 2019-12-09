#!/bin/bash

echo Building all projects

PATH_TO_SCRIPT=`dirname $0`

PROJECTS=$(ls ${PATH_TO_SCRIPT}/*/build.gradle)

# costruisce tutti i progetti 
for project in ${PROJECTS}; do 
	echo ""
	echo "Now building ${project}"
	gradle --build-file ${project} build
done 
