#!/bin/bash

DOCKERHUB_USERNAME=marcozoveralli
# DOCKER_SWARM=tcp://localhost:2375
DOCKER_SWARM=tcp://workstation:2375

docker -H ${DOCKER_SWARM} service create --name hello --publish 8080:8080 --mode replicated --replicas 2 ${DOCKERHUB_USERNAME}/hello 

