# バックエンド用 k8s 環境構築

## docker build

```
cd /path/to/k8s-handson
docker build -f k8s/back-app/Dockerfile -t michiko-back:1.0 --build-arg APP_FILE_BASE_DIR=back-app .
```

## docker run

```
docker run -d -p 80:80 michiko:1.0
```