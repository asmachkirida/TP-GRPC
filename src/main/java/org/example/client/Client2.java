package org.example.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.example.stubs.Bank;
import org.example.stubs.BankServiceGrpc;

import java.io.IOException;

public class Client2 {
    public static void main(String[] args) throws IOException {
        // Creating a managed channel for gRPC communication
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 5555)
                .usePlaintext()  // Disables SSL for development
                .build();

        // Creating an asynchronous stub to interact with the gRPC server
        BankServiceGrpc.BankServiceStub asyncStub = BankServiceGrpc.newStub(managedChannel);

        // Creating a request object for currency conversion
        Bank.ConvertCurrencyRequest request = Bank.ConvertCurrencyRequest.newBuilder()
                .setCurrencyFrom("MAD")  // Source currency (Moroccan Dirham)
                .setCurrencyTo("USD")    // Target currency (US Dollars)
                .setAmount(7500)         // Amount to be converted
                .build();

        // Sending the request asynchronously and handling the response with a StreamObserver
        asyncStub.convert(request, new StreamObserver<Bank.ConvertCurrencyResponse>() {

            @Override
            public void onNext(Bank.ConvertCurrencyResponse convertCurrencyResponse) {
                // Handle the response when received
                System.out.println(convertCurrencyResponse);
            }

            @Override
            public void onError(Throwable throwable) {
                // Handle errors
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                // Handle completion
                System.out.println("Request Completed");
            }
        });

        // Waiting for the server response before closing
        System.out.println("Waiting for server response...");
        System.in.read();  // Block until input is received
    }
}
