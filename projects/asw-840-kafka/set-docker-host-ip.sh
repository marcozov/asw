#!/bin/bash

MY_IP_ADDR=$(ifconfig  | grep 'inet' | grep -v 'inet6' | grep -v '127.0.0.1' | cut -d: -f2 | awk '{ print $2}' | grep '10.11.1.')

if [ -z "$DOCKER_HOST_IP" ] ; then
    echo "please source this file" 
    export DOCKER_HOST_IP=${MY_IP_ADDR}
fi

echo DOCKER_HOST_IP is ${DOCKER_HOST_IP}
