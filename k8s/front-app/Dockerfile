# ビルドのためのコンテナイメージ
FROM node:14.15.4-alpine as builder

ARG APP_FILE_BASE_DIR

WORKDIR /build
# 設定ファイルをコピーして、インストール
COPY "${APP_FILE_BASE_DIR}/rollup.config.js" ./
COPY "${APP_FILE_BASE_DIR}/package*.json" ./
COPY "${APP_FILE_BASE_DIR}/tsconfig.json" ./

RUN npm install

# ソース・ファイルをコピーして、ビルド
COPY "${APP_FILE_BASE_DIR}/src" ./src
COPY "${APP_FILE_BASE_DIR}/public" ./public

RUN npm run build

# ---
# アプリケーション実行用コンテナイメージ
FROM nginx:1.19.2

ARG APP_FILE_BASE_DIR

ENV NGINX_PID_FILE "/var/run/nginx.pid"
ENV DOCUMENT_ROOT "/usr/share/nginx/html/"

RUN ln -sf /usr/share/zoneinfo/Asia/Tokyo /etc/localtime; \
  apt update && apt-get update --fix-missing; \
  apt-get install -y jq curl procps sudo apache2-utils --no-install-recommends; \
  apt-get autoclean; rm -rf /var/lib/apt/lists/*; \
  {   echo "nginx ALL=NOPASSWD: ALL"; \
      echo "Defaults visiblepw"; \
  } >> /etc/sudoers.d/sudouser; \
  mkdir -p /opt/logs "${DOCUMENT_ROOT}"/live; 

# ビルドされた成果物をコピーしてくる
COPY --from=builder /build/public ${DOCUMENT_ROOT}
WORKDIR ${DOCUMENT_ROOT}
EXPOSE 80
USER nginx
ENTRYPOINT sudo -n /usr/sbin/nginx -g 'daemon off;'
