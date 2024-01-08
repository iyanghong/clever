# 第一阶段：用于构建并打包项目
FROM maven:3.8.4-openjdk-8 AS build-stage

# 将项目文件复制到容器中
WORKDIR /usr/src/app
COPY . .

# 运行 Maven 构建命令
RUN mvn clean install

# 第二阶段：部署项目
FROM openjdk:8-jre

# 从第一阶段拷贝构建好的项目文件到部署阶段
COPY --from=build-stage /usr/src/app/target/your-application.jar /usr/src/app/

# 设置工作目录
WORKDIR /usr/src/app

# 容器启动时执行的命令，启动您的应用程序
CMD ["java", "-jar", "your-application.jar"]
