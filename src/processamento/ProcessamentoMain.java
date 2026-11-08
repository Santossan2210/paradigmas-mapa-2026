package processamento;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Executa o pipeline funcional sobre o lote simulado e mostra cada etapa no console.
 *
 * @author Antonio Santos
 */
public final class ProcessamentoMain {

    private static final int LOTE_PEQUENO = 10;

    private ProcessamentoMain() {
    }

    public static void main(String[] args) {
        List<Viagem> lote = GeradorLote.gerar(LOTE_PEQUENO);

        // Dados de entrada -> MAP
        titulo("Lote pequeno — " + LOTE_PEQUENO + " viagens");
        List<ResultadoViagem> resultados = PipelineTelemetria.transformar(lote, PipelineTelemetria.SEQUENCIAL);
        if (resultados.isEmpty()) {
            System.out.println("Nenhuma viagem no lote.");
        }
        resultados.forEach(ProcessamentoMain::imprimirViagem);

        // FILTER
        titulo("Seleção — viagens críticas (< 20%)");
        List<ResultadoViagem> criticas = PipelineTelemetria.selecionarCriticas(resultados);
        if (criticas.isEmpty()) {
            System.out.println("Nenhuma viagem crítica neste lote.");
        } else {
            criticas.forEach(ProcessamentoMain::imprimirViagem);
            System.out.println("Total de viagens críticas: " + criticas.size());
        }

        demonstrarAvaliacaoPreguicosa(lote);

        // REDUCE
        titulo("Agregação por categoria — lote pequeno");
        imprimirTotais(PipelineTelemetria.agregar(lote, PipelineTelemetria.SEQUENCIAL));
    }

    /**
     * Mostra que o stream só calcula quando o resultado é pedido. O peek existe
     * apenas para tornar isso visível: ele imprime e não altera nenhum dado.
     */
    private static void demonstrarAvaliacaoPreguicosa(List<Viagem> lote) {
        titulo("Avaliação preguiçosa");

        Stream<ResultadoViagem> criticasSobDemanda = lote.stream()
                .peek(viagem -> System.out.printf(Locale.ROOT, "  calculando viagem #%02d%n", viagem.id()))
                .map(CalculoViagem::calcular)
                .filter(ResultadoViagem::critica);

        System.out.println("Pipeline montado. Nenhuma viagem foi calculada até aqui.");

        // Só aqui, na operação terminal, as viagens começam a ser calculadas,
        // e o cálculo para assim que a primeira crítica aparece
        Optional<ResultadoViagem> primeira = criticasSobDemanda.findFirst();

        if (primeira.isPresent()) {
            System.out.printf(Locale.ROOT, "Primeira viagem crítica encontrada: #%02d%n",
                    primeira.get().viagem().id());
        } else {
            System.out.println("Nenhuma viagem crítica encontrada.");
        }

        // Os ids vão de 1 a 10 em ordem, então o id encontrado é quantas viagens foram calculadas
        int calculadas = primeira.map(resultado -> resultado.viagem().id()).orElse(lote.size());
        System.out.printf(Locale.ROOT, "Viagens calculadas: %d de %d%n", calculadas, lote.size());
    }

    private static void imprimirViagem(ResultadoViagem resultado) {
        Viagem viagem = resultado.viagem();
        System.out.printf(Locale.ROOT,
                "#%02d | %-18s | %3d km | bateria %d%% -> %.1f%% | consumo %.1f | CRÍTICA: %s%n",
                viagem.id(),
                viagem.categoria().rotulo(),
                viagem.distanciaKm(),
                viagem.bateriaInicial(),
                resultado.bateriaFinal(),
                resultado.consumo(),
                resultado.critica() ? "sim" : "não");
    }

    private static void imprimirTotais(Map<Categoria, ResumoCategoria> totais) {
        totais.values().forEach(resumo -> System.out.printf(Locale.ROOT,
                "%-18s | viagens: %6d | km: %8d | consumo: %10.1f | alertas: %6d%n",
                resumo.categoria().rotulo(),
                resumo.viagens(),
                resumo.kmTotal(),
                resumo.consumoTotal(),
                resumo.alertas()));
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("=== " + texto + " ===");
    }
}
