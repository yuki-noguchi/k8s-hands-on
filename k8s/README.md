# k8s 環境構築

## 事前準備

```
brew install minikube
brew install
```

**また、VPN 経由だと失敗します。**

## 開始

割と時間かかります。

```
sh /path/to/k8s-handson/k8s/start.sh
```

ブラウザで試すには、以下が必要です。

```
minikbe ip

sudo vi /etc/hosts

# 以下のように追記する
# 取得できたIPアドレス michiko.praise.com
# 取得できたIPアドレス api.michiko.praise.com
```

ブラウザから以下のアドレスにアクセスすることができます。

```
http://michiko.praise.com
```

ダッシュボードは以下のコマンドで。

```
minikube dashboard
```

## 終了

```
sh /path/to/k8s-handson/k8s/drop.sh
```

```
minikube ip

sudo vi /etc/hosts

# 以下を削除する
# 取得できた IP アドレス michiko.praise.com
# 取得できた IP アドレス api.michiko.praise.com
```
