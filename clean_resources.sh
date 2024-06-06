#!/bin/bash

# Delete config and secret
kubectl delete -f ./kuber/config/weather-forecast-config.yaml
kubectl delete -f ./kuber/secret/weather-forecast-secret.yaml

# Delete forecast-core resources
kubectl delete -f ./kuber/forecast-core/deployment_forecast-core.yaml
kubectl delete -f ./kuber/forecast-core/service_forecast-core.yaml

# Delete forecast-client resources
kubectl delete -f ./kuber/forecast-client/deployment_forecast-client.yaml
kubectl delete -f ./kuber/forecast-client/service_forecast-client.yaml

# Delete forecast-bot resources
kubectl delete -f ./kuber/forecast-bot/deployment_forecast-bot.yaml
kubectl delete -f ./kuber/forecast-bot/service_forecast-bot.yaml
