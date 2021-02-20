#!/bin/bash

docker-compose -p dev-leetcode -f docker-compose.dev.yml down --remove-orphans
docker-compose -p dev-leetcode -f docker-compose.dev.yml up -d