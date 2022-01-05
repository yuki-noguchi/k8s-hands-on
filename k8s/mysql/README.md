# MySQL k8s 環境構築

## docker build

```
cd /path/to/k8s-handson/k8s/mysql
docker compose up
```

## docker run

```
docker run -d -p 80:80 michiko:1.0
```

## k8s apply

```
cd /path/to/k8s-handson/k8s/mysql
kubectl create configmap mysql-server-initdb-config --from-file=init/init.sql
kubectl create configmap mysql-server-conf-config --from-file=conf/my.cnf
kubectl apply -f deployment.yaml
```
