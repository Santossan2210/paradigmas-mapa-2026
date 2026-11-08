package processamento;

/**
 * Resultado do cálculo de uma viagem. Também é imutável: o cálculo gera um
 * objeto novo em vez de alterar a viagem original.
 *
 * @author Antonio Santos
 */
public record ResultadoViagem(Viagem viagem, double consumo, double bateriaFinal, boolean critica) {
}
