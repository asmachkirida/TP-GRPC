package org.example.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.services.BankGrpcService;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Création et configuration du serveur gRPC
        Server server = ServerBuilder.forPort(5555)
                .addService(new BankGrpcService())  // Utilisation de BankGrpcService
                .build();

        // Démarrage du serveur
        server.start();
        System.out.println("Serveur gRPC démarré sur le port 5555");

        // Attendre que le serveur soit arrêté
        server.awaitTermination();
    }
}
