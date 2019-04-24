#!/bin/sh
docker build . -t micronaut-graal-atp-hibernate-demo
echo
echo
echo "To run the docker container execute:"
echo "    $ docker run --network host micronaut-graal-atp-hibernate-demo"
