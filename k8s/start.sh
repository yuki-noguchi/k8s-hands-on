#!/bin/sh

SCRIPT_DIR=$(cd $(dirname $0); pwd)

export KUBECONFIG=~/.kube/config

echo minikubeを起動します。
minikube start --driver=hyperkit
minikube addons enable ingress
eval $(minikube -p minikube docker-env)

cd ..

echo フロントエンドのビルドを実行します。
docker build -f ${SCRIPT_DIR}/front-app/Dockerfile -t michiko/front:1.0 --build-arg APP_FILE_BASE_DIR=front-app .

echo バックエンドのビルドを実行します。
docker build -f ${SCRIPT_DIR}/backend-app/Dockerfile -t michiko/backend:1.0 --build-arg APP_FILE_BASE_DIR=backend-app .

echo フロントエンドのk8s環境を適用します。
kubectl apply -f ${SCRIPT_DIR}/front-app/deployment.yaml 
kubectl apply -f ${SCRIPT_DIR}/front-app/ingress.yaml

echo MySQLのconfigMapの設定を行います。
kubectl create configmap mysql-server-initdb-config --from-file=${SCRIPT_DIR}/mysql/init/init.sql
kubectl create configmap mysql-server-conf-config --from-file=${SCRIPT_DIR}/mysql/conf/my.cnf

echo MySQLのk8s環境を適用します。
kubectl apply -f ${SCRIPT_DIR}/mysql/deployment.yaml 

echo バックエンドのk8s環境を適用します。
kubectl apply -f ${SCRIPT_DIR}/backend-app/deployment.yaml 
kubectl apply -f ${SCRIPT_DIR}/backend-app/ingress.yaml