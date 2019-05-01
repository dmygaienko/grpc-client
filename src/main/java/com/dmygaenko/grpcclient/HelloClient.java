package com.dmygaenko.grpcclient;

import com.dmygaenko.api.*;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

@Service
public class HelloClient {

    public String hello(String firstName, String lastName) {
        final HelloServiceGrpc.HelloServiceBlockingStub stub = getHelloServiceBlockingStub();
        HelloResponse hello = stub.hello(
                HelloRequest.newBuilder()
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .build());
        return hello.getGreeting();
    }

    private HelloServiceGrpc.HelloServiceBlockingStub getHelloServiceBlockingStub() {
        final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();
        return HelloServiceGrpc.newBlockingStub(channel);
    }

    public String transfer(String firstName, String lastName) {
        final HelloServiceGrpc.HelloServiceBlockingStub stub = getHelloServiceBlockingStub();
        FileResponse hello = stub.transferFile(
                FileRequest.newBuilder()
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setContent(ByteString.copyFrom("Test String", Charset.defaultCharset()))
                        .build());
        return hello.getGreeting();
    }

}
