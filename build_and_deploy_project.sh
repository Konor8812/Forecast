#!/bin/bash

source clean_resources.sh

source prepareEnv.sh

mvn clean package

echo "$GITLAB_PASSWORD" | docker login -u "$GITLAB_USERNAME" --password-stdin registry.gitlab.com


docker build -t forecast-core:latest -f ./forecastCore/Dockerfile . & PID1=$!
docker build -t forecast-client:latest -f ./forecastClient/Dockerfile . & PID2=$!
docker build -t forecast-bot:latest -f ./forecastBot/Dockerfile . & PID3=$!

wait $PID1 $PID2 $PID3

docker tag forecast-core registry.gitlab.com/admirai/diploma_forecast/forecast-core:latest
docker tag forecast-client registry.gitlab.com/admirai/diploma_forecast/forecast-client:latest
docker tag forecast-bot registry.gitlab.com/admirai/diploma_forecast/forecast-bot:latest

docker push registry.gitlab.com/admirai/diploma_forecast/forecast-core:latest & PID1=$!
docker push registry.gitlab.com/admirai/diploma_forecast/forecast-client:latest & PID2=$!
docker push registry.gitlab.com/admirai/diploma_forecast/forecast-bot:latest & PID3=$!

wait $PID1 $PID2 $PID3

aws eks --region eu-north-1 update-kubeconfig --name diploma

kubectl apply -f ./kuber/config/weather-forecast-config.yaml

kubectl apply -f ./kuber/secret/weather-forecast-secret.yaml


kubectl apply -f ./kuber/forecast-core/deployment_forecast-core.yaml
kubectl apply -f ./kuber/forecast-core/service_forecast-core.yaml

kubectl apply -f ./kuber/forecast-client/deployment_forecast-client.yaml
kubectl apply -f ./kuber/forecast-client/service_forecast-client.yaml

kubectl apply -f ./kuber/forecast-bot/deployment_forecast-bot.yaml
kubectl apply -f ./kuber/forecast-bot/service_forecast-bot.yaml
