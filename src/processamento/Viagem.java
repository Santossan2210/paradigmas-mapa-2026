package processamento;

/**
 * Uma viagem registrada pela telemetria. Por ser um record, não pode ser alterada
 * depois de criada.
 *
 * @author Antonio Santos
 */
public record Viagem(int id, Categoria categoria, int distanciaKm, int bateriaInicial) {

    public Viagem {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria da viagem não informada");
        }
        if (distanciaKm < 0) {
            throw new IllegalArgumentException("Distância negativa: " + distanciaKm);
        }
        if (bateriaInicial < 0 || bateriaInicial > 100) {
            throw new IllegalArgumentException("Bateria inicial fora de 0 a 100: " + bateriaInicial);
        }
    }
}
