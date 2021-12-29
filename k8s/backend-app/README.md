# バックエンド用 k8s 環境構築

## docker build

```
cd /path/to/k8s-handson
docker build -f k8s/backend-app/Dockerfile -t michiko/backend:1.0 --build-arg APP_FILE_BASE_DIR=backend-app .
```

## docker run

```
docker run -d -p 80:80 michiko:1.0
```

## k8s apply

```
cd /path/to/k8s-handson
kubectl apply -f k8s/backend-app/deployment.yaml
kubectl apply -f k8s/backend-app/ingress.yaml
```
