FROM maven:latest

RUN apt-get update -y && \
    apt-get install -y x11-apps wget unzip \
    libgtk-3-0 \
    libx11-xcb1 \
    libgdk-pixbuf2.0-0 \
    libpango-1.0-0 \
    libatk1.0-0 \
    libpangocairo-1.0-0 \
    libcups2 \
    x11-apps \
    libcanberra-gtk-module && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Expor a variável DISPLAY para o container
ENV DISPLAY=:0

ENTRYPOINT ["java","-jar","target/java-swing-docker.jar"]