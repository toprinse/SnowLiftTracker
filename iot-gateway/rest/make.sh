#!/bin/bash

echo rest_post
g++ -Wall src/post.cpp -o bin/POST -O0 -g -lcurl
