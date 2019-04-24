FROM oracle/graalvm-ce:1.0.0-rc15 as graalvm
COPY . /home/app/micronaut-graal-atp-hibernate-demo
WORKDIR /home/app/micronaut-graal-atp-hibernate-demo
RUN native-image --no-server -cp build/libs/micronaut-graal-atp-hibernate-demo-*.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/micronaut-graal-atp-hibernate-demo .
ENTRYPOINT ["./micronaut-graal-atp-hibernate-demo"]
