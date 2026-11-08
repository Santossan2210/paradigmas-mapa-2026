package telemetria;

import java.util.Locale;

/**
 * Bicicleta elétrica de entrega, com assistência ao pedal limitada por velocidade.
 *
 * @author Antonio Santos
 */
public class BicicletaEletrica extends Veiculo {

    private double limiteVelocidadeAssistida;   // em km/h

    public BicicletaEletrica(int idVeiculo, String modelo, double capacidadeCarga,
                             double autonomiaBateria, double limiteVelocidadeAssistida) {
        this.idVeiculo = idVeiculo;
        this.modelo = modelo;
        this.capacidadeCarga = capacidadeCarga;
        setAutonomiaBateria(autonomiaBateria);
        this.limiteVelocidadeAssistida = limiteVelocidadeAssistida;
    }

    @Override
    public void registrarViagem(double distancia) {
        // Cada km percorrido pela bicicleta consome 0.2 ponto percentual de bateria
        double novaBateria = getAutonomiaBateria() - (distancia * 0.2);
        setAutonomiaBateria(novaBateria);

        System.out.printf(Locale.ROOT,
                "[Bicicleta elétrica] Distância percorrida: %.1f km | Bateria restante: %.1f%%%n",
                distancia, getAutonomiaBateria());
    }
}
