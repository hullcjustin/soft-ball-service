# Soft Ball Service
#
FROM openjdk:8u151-jre-alpine3.7
LABEL maintainer="dev@justinchull.com"

COPY build/libs/soft-ball*.jar /opt/softball/

ENV TZ "America/Boise"
RUN apk update && \
    apk add tzdata && \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && \
    echo $TZ > /etc/timezone && \
    apk del tzdata

WORKDIR /opt/softball

CMD java -jar *.jar \
  -server \
  -XX:+UnlockExperimentalVMOptions \
  -XX:+UseCGroupMemoryLimitForHeap \
  -Djava.security.egd=file:/dev/./urandom \
  -Dsun.net.inetaddr.ttl=60
