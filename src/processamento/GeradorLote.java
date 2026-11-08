package processamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Gera um lote simulado de viagens, sempre igual a cada execução.
 *
 * A geração usa um laço comum, de propósito: o Random guarda estado interno e
 * muda a cada número sorteado, então não seria uma função pura. Por isso ela é
 * sempre sequencial e fica fora do pipeline funcional. Só o processamento das
 * viagens já prontas pode rodar em paralelo.
 *
 * @author Antonio Santos
 */
public final class GeradorLote {

    /** Semente fixa: garante o mesmo lote em toda execução. */
    public static final long SEMENTE = 376L;

    private GeradorLote() {
    }

    public static List<Viagem> gerar(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade negativa: " + quantidade);
        }

        Random sorteio = new Random(SEMENTE);
        List<Viagem> viagens = new ArrayList<>(quantidade);

        for (int id = 1; id <= quantidade; id++) {
            Categoria categoria = sorteio.nextInt(100) < 30 ? Categoria.VAN : Categoria.BICICLETA;
            int distanciaKm = 5 + sorteio.nextInt(116);       // 5 a 120 km
            int bateriaInicial = 40 + sorteio.nextInt(61);    // 40% a 100%
            viagens.add(new Viagem(id, categoria, distanciaKm, bateriaInicial));
        }

        // Entrega uma cópia imutável: ninguém consegue alterar o lote depois de gerado
        return List.copyOf(viagens);
    }
}
