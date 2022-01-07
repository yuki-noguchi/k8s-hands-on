# フロントエンド用 k8s 環境構築

## docker build

```
cd /path/to/k8s-hands-on
docker build -f k8s/front-app/Dockerfile -t michiko/front:1.0 --build-arg APP_FILE_BASE_DIR=front-app .
```

## docker run

```
docker run -d -p 80:80 michiko:1.0
```

## k8s apply

```
cd /path/to/k8s-hands-on
kubectl apply -f k8s/front-app/deployment.yaml
kubectl apply -f k8s/front-app/ingress.yaml
```
