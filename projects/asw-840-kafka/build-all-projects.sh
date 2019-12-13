#!/bin/bash

echo Building all projects

PATH_TO_SCRIPT=`dirname`

PROJECTS=$(ls ${PATH_TO_SCRIPT}/*/build.gradle)

for project in ${PROJECTS}; do
	echo ""
	echo "Now building ${project}"
	gradle --build-file ${project} build
done
