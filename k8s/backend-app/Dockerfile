# ビルドのためのコンテナイメージ
FROM gradle:jdk11-alpine as builder

ARG APP_FILE_BASE_DIR

WORKDIR /build

# ファイルをコピーしてビルド
COPY "${APP_FILE_BASE_DIR}/" ./

RUN ./gradlew build

# ---
# アプリケーション実行用コンテナイメージ
FROM openjdk:15.0.2-jdk

ARG APP_FILE_BASE_DIR

WORKDIR /app

RUN ln -sf /usr/share/zoneinfo/Asia/Tokyo /etc/localtime;

# ビルドされた成果物をコピーしてくる
COPY --from=builder /build/build/libs/backend-app-0.0.1-SNAPSHOT.jar ./

EXPOSE 8080

ENTRYPOINT java -jar backend-app-0.0.1-SNAPSHOT.jar
