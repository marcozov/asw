#!/bin/bash

docker run -d -p 8080:8080 --name=lucky-word lucky-word-img -jar -Dspring.profiles.active=english lucky-word.jar
