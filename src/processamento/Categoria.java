package processamento;

/**
 * Categorias de veículo da frota e a taxa de consumo de cada uma.
 *
 * As taxas repetem as do pacote telemetria de propósito: este módulo não usa as
 * classes OO, porque elas mudam de estado a cada viagem e aqui o objetivo é
 * trabalhar só com dados imutáveis.
 *
 * @author Antonio Santos
 */
public enum Categoria {

    VAN(0.5, "Van elétrica"),
    BICICLETA(0.2, "Bicicleta elétrica");

    private final double taxaPorKm;   // pontos percentuais de bateria por km
    private final String rotulo;

    Categoria(double taxaPorKm, String rotulo) {
        this.taxaPorKm = taxaPorKm;
        this.rotulo = rotulo;
    }

    public double taxaPorKm() {
        return taxaPorKm;
    }

    public String rotulo() {
        return rotulo;
    }
}
