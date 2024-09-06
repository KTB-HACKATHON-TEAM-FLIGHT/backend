FROM eclipse-temurin:17-jdk

# 필수 패키지 설치 (wkhtmltopdf 및 관련 의존성)
RUN apt-get update && apt-get install -y \
    wkhtmltopdf \
    fonts-freefont-ttf \
    && rm -rf /var/lib/apt/lists/*

# JAR 파일을 컨테이너에 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 8080 포트 노출
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
