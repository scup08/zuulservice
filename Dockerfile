FROM centos

ADD zuulservice.jar /app.jar

#配置java环境变量
ENV JRE_HOME=/usr/java/jre1.8.0_171
ENV PATH $JRE_HOME/bin:$PATH
ENV CLASSPATH=$JRE_HOME/lib/rt.jar:$JRE_HOME/lib/ext

# 开放1111端口
EXPOSE 9030

# 配置容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Xms256m","-Xmx256m","/app.jar"]
