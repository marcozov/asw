###########################################
# Dockerfile to share the www folder (in the container) 
###########################################

# BusyBox ha un footprint minimale 
FROM busybox:latest

COPY www /var/www/html

VOLUME /var/www/html 

ENTRYPOINT ["/bin/true"]
