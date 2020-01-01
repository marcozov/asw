#!/bin/bash

DOCKERHUB_USERNAME=marcozoveralli

docker build --rm -t ${DOCKERHUB_USERNAME}/hello . 
docker push ${DOCKERHUB_USERNAME}/hello
