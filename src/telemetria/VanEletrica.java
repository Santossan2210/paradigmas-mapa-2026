package telemetria;

import java.util.Locale;

/**
 * Van elétrica de carga. Consome mais bateria por quilômetro que a bicicleta.
 *
 * @author Antonio Santos
 */
public class VanEletrica extends Veiculo {

    private String placaIdentificacao;

    public VanEletrica(int idVeiculo, String modelo, double capacidadeCarga,
                       double autonomiaBateria, String placaIdentificacao) {
        this.idVeiculo = idVeiculo;
        this.modelo = modelo;
        this.capacidadeCarga = capacidadeCarga;
        setAutonomiaBateria(autonomiaBateria);
        this.placaIdentificacao = placaIdentificacao;
    }

    @Override
    public void registrarViagem(double distancia) {
        // Cada km percorrido pela van consome 0.5 ponto percentual de bateria
        double novaBateria = getAutonomiaBateria() - (distancia * 0.5);
        setAutonomiaBateria(novaBateria);

        System.out.printf(Locale.ROOT,
                "[Van elétrica] Distância percorrida: %.1f km | Bateria restante: %.1f%%%n",
                distancia, getAutonomiaBateria());
    }
}
