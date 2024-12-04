package org.example.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.stubs.Bank;
import org.example.stubs.BankServiceGrpc;

public class Client1 {
    public static void main(String[] args) {
        // Creating a managed channel for gRPC communication
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 5555)
                .usePlaintext()  // Disables SSL for development
                .build();

        // Creating a blocking stub to interact with the gRPC server
        BankServiceGrpc.BankServiceBlockingStub blockingStub = BankServiceGrpc.newBlockingStub(managedChannel);

        // Creating a request object for currency conversion
        Bank.ConvertCurrencyRequest request = Bank.ConvertCurrencyRequest.newBuilder()
                .setCurrencyFrom("MAD")  // Source currency (Moroccan Dirham)
                .setCurrencyTo("USD")    // Target currency (US Dollars)
                .setAmount(7600)         // Amount to be converted
                .build();

        // Sending the request and receiving the response from the server
        Bank.ConvertCurrencyResponse currencyResponse = blockingStub.convert(request);

        // Printing the response
        System.out.println(currencyResponse);
    }
}
