package com.dmygaenko.grpcclient;

import com.dmygaenko.grpcapi.HelloRequest;
import com.dmygaenko.grpcapi.HelloResponse;
import com.dmygaenko.grpcapi.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class HelloClient {

    public String hello(String firstName, String lastName) {
        final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();
        final HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
        HelloResponse hello = stub.hello(
                HelloRequest.newBuilder()
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .build());
        return hello.getGreeting();
    }

}
