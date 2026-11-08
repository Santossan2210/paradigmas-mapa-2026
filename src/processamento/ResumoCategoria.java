package processamento;

/**
 * Totais acumulados de uma categoria de veículo.
 *
 * Os totais ficam em long (km inteiros e consumo em centésimos) para que a soma
 * seja exata. Com double, somar em ordens diferentes, como acontece no modo
 * paralelo, poderia mudar a última casa decimal.
 *
 * @author Antonio Santos
 */
public record ResumoCategoria(Categoria categoria, long viagens, long kmTotal,
                              long consumoCentesimos, long alertas) {

    /** Resumo zerado: o ponto de partida (identidade) do reduce. */
    public static ResumoCategoria vazio(Categoria categoria) {
        return new ResumoCategoria(categoria, 0, 0, 0, 0);
    }

    /** Resumo de uma única viagem, pronto para ser combinado com os demais. */
    public static ResumoCategoria de(ResultadoViagem resultado) {
        Viagem viagem = resultado.viagem();
        return new ResumoCategoria(
                viagem.categoria(),
                1,
                viagem.distanciaKm(),
                Math.round(resultado.consumo() * 100),
                resultado.critica() ? 1 : 0);
    }

    /**
     * Soma dois resumos e devolve um terceiro, sem alterar nenhum dos dois.
     * Como é uma soma, a ordem das combinações não muda o resultado.
     */
    public ResumoCategoria combinar(ResumoCategoria outro) {
        if (outro.categoria != this.categoria) {
            throw new IllegalArgumentException(
                    "Não é possível combinar " + this.categoria + " com " + outro.categoria);
        }
        return new ResumoCategoria(
                categoria,
                viagens + outro.viagens,
                kmTotal + outro.kmTotal,
                consumoCentesimos + outro.consumoCentesimos,
                alertas + outro.alertas);
    }

    public double consumoTotal() {
        return consumoCentesimos / 100.0;
    }
}
