package org.example.services;

import io.grpc.stub.StreamObserver;
import org.example.stubs.Bank;
import org.example.stubs.BankServiceGrpc;

public class BankGrpcService extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public void convert(Bank.ConvertCurrencyRequest request,
                        StreamObserver<Bank.ConvertCurrencyResponse> responseObserver) {

        // Extraction des paramètres de la requête
        String currencyFrom = request.getCurrencyFrom();
        String currencyTo = request.getCurrencyTo();
        double amount = request.getAmount();

        // Logique simple de conversion de devises (ici avec un taux fixe fictif)
        Bank.ConvertCurrencyResponse response = Bank.ConvertCurrencyResponse.newBuilder()
                .setCurrencyFrom(currencyFrom)
                .setCurrencyTo(currencyTo)
                .setAmount(amount)
                .setResult(amount * 11.4) // Le résultat de la conversion
                .build();

        // Envoi de la réponse au client
        responseObserver.onNext(response);

        // Marquer la fin de l'appel gRPC
        responseObserver.onCompleted();
    }
}
