#!/bin/bash

# https://docs.docker.com/compose/install/

echo "========================="
echo "installing docker-compose"
echo "========================="

# set up Docker Compose constants 
DOCKER_COMPOSE_VERSION=1.24.0
# per le versioni: https://github.com/docker/compose/releases 

GET_DOCKER_COMPOSE_URL=https://github.com/docker/compose/releases/download/1.24.0/docker-compose-$(uname -s)-$(uname -m)
DOCKER_COMPOSE_PATH=/usr/local/bin/docker-compose

curl -L ${GET_DOCKER_COMPOSE_URL} -o ${DOCKER_COMPOSE_PATH}
chmod +x ${DOCKER_COMPOSE_PATH} 

