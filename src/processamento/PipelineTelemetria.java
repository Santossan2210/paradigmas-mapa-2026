package processamento;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Pipeline funcional de telemetria: transformar (map), selecionar (filter) e
 * agregar (reduce).
 *
 * O parâmetro "fonte" é uma função que recebe a lista e devolve o fluxo de
 * viagens. Passando SEQUENCIAL ou PARALELO, o mesmo pipeline roda dos dois
 * jeitos sem mudar uma linha: é a função de ordem superior do exemplo.
 *
 * @author Antonio Santos
 */
public final class PipelineTelemetria {

    public static final Function<List<Viagem>, Stream<Viagem>> SEQUENCIAL = List::stream;
    public static final Function<List<Viagem>, Stream<Viagem>> PARALELO = List::parallelStream;

    private PipelineTelemetria() {
    }

    /** MAP: cada viagem vira um resultado com consumo e bateria final. */
    public static List<ResultadoViagem> transformar(List<Viagem> viagens,
                                                    Function<List<Viagem>, Stream<Viagem>> fonte) {
        return fonte.apply(viagens)
                .map(CalculoViagem::calcular)
                .toList();
    }

    /** FILTER: mantém só as viagens que terminaram com bateria crítica. */
    public static List<ResultadoViagem> selecionarCriticas(List<ResultadoViagem> resultados) {
        return resultados.stream()
                .filter(ResultadoViagem::critica)
                .toList();
    }

    /**
     * REDUCE: totais por categoria.
     *
     * Parte das viagens, e não do resultado de transformar ou de
     * selecionarCriticas, por dois motivos: o total vale para todas as viagens,
     * não só para as críticas, e cada execução precisa ser um pipeline completo
     * para a comparação entre sequencial e paralelo ser justa. Recalcular é
     * seguro porque calcular é uma função pura e sempre devolve o mesmo resultado.
     */
    public static Map<Categoria, ResumoCategoria> agregar(List<Viagem> viagens,
                                                          Function<List<Viagem>, Stream<Viagem>> fonte) {
        Map<Categoria, ResumoCategoria> totais = Arrays.stream(Categoria.values())
                .collect(Collectors.toMap(
                        categoria -> categoria,
                        categoria -> resumir(viagens, fonte, categoria),
                        (primeiro, segundo) -> primeiro,
                        () -> new EnumMap<>(Categoria.class)));
        return Collections.unmodifiableMap(totais);
    }

    private static ResumoCategoria resumir(List<Viagem> viagens,
                                           Function<List<Viagem>, Stream<Viagem>> fonte,
                                           Categoria categoria) {
        return fonte.apply(viagens)
                .filter(viagem -> viagem.categoria() == categoria)
                .map(CalculoViagem::calcular)
                .map(ResumoCategoria::de)
                .reduce(ResumoCategoria.vazio(categoria), ResumoCategoria::combinar);
    }
}
