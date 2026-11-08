package processamento;

/**
 * Regras de consumo escritas como funções puras: o resultado depende só da
 * viagem recebida, e nada fora do método é lido ou alterado.
 *
 * @author Antonio Santos
 */
public final class CalculoViagem {

    /** Abaixo deste nível, a viagem é considerada crítica. */
    public static final double LIMITE_CRITICO = 20.0;

    private CalculoViagem() {
    }

    public static ResultadoViagem calcular(Viagem viagem) {
        double consumo = viagem.distanciaKm() * viagem.categoria().taxaPorKm();
        double bruto = viagem.bateriaInicial() - consumo;

        // Mesma regra de validação do setAutonomiaBateria, só que sem alterar estado
        double bateriaFinal = (bruto < 0.0 || bruto > 100.0) ? 0.0 : bruto;

        return new ResultadoViagem(viagem, consumo, bateriaFinal, ehCritica(bateriaFinal));
    }

    public static boolean ehCritica(double bateriaFinal) {
        return bateriaFinal < LIMITE_CRITICO;
    }
}
