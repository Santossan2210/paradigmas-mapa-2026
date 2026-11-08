package telemetria;

import java.util.Locale;

/**
 * Demonstração da telemetria: uma van e uma bicicleta saem com a bateria cheia
 * e fazem uma viagem de 50 km cada.
 *
 * @author Antonio Santos
 */
public class Main {

    public static void main(String[] args) {
        VanEletrica van = new VanEletrica(1, "EcoCargo E-Van 1500", 1500.0, 100.0, "ECO1A26");
        BicicletaEletrica bicicleta = new BicicletaEletrica(2, "EcoBike Urban 25", 30.0, 100.0, 25.0);

        System.out.println("=== EcoFleet Solutions S.A. - Telemetria da Frota ===");

        // Mesma chamada, comportamento diferente em cada tipo de veículo
        van.registrarViagem(50.0);
        bicicleta.registrarViagem(50.0);

        System.out.println("--- Nível final de bateria ---");
        System.out.printf(Locale.ROOT, "Van elétrica: %.1f%%%n", van.getAutonomiaBateria());
        System.out.printf(Locale.ROOT, "Bicicleta elétrica: %.1f%%%n", bicicleta.getAutonomiaBateria());
    }
}
