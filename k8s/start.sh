#!/bin/sh

SCRIPT_DIR=$(cd $(dirname $0); pwd)

echo minikubeを起動します。
minikube start --driver=hyperkit
minikube addons enable ingress
eval $(minikube -p minikube docker-env)

cd ../${SCRIPT_DIR}

echo フロントエンドのビルドを実行します。
docker build -f ${SCRIPT_DIR}/front-app/Dockerfile -t michiko/front:1.0 --build-arg APP_FILE_BASE_DIR=front-app .

echo バックエンドのビルドを実行します。
docker build -f ${SCRIPT_DIR}/backend-app/Dockerfile -t michiko/backend:1.0 --build-arg APP_FILE_BASE_DIR=backend-app .

echo フロントエンドのk8s環境をapplyします。
kubectl apply -f ${SCRIPT_DIR}/front-app/deployment.yaml 
kubectl apply -f ${SCRIPT_DIR}/front-app/ingress.yaml

echo バックエンドのk8s環境をapplyします。
kubectl apply -f ${SCRIPT_DIR}/backend-app/deployment.yaml 
kubectl apply -f ${SCRIPT_DIR}/backend-app/ingress.yaml