#!/bin/bash

for i in {1..100}; do 
	curl localhost:8080/sentence
	echo "" ; 
done
