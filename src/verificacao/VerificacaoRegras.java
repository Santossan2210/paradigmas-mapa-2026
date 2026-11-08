package verificacao;

import java.util.Locale;

import telemetria.BicicletaEletrica;
import telemetria.VanEletrica;

/**
 * Confere as regras de bateria do pacote telemetria usando apenas a API pública
 * das classes. Encerra com código 1 se algum caso falhar, para o CI acusar o erro.
 *
 * @author Antonio Santos
 */
public final class VerificacaoRegras {

    private static final int TOTAL_CASOS = 8;

    private VerificacaoRegras() {
    }

    public static void main(String[] args) {
        int casosOk = 0;

        // Validação da bateria na criação do veículo
        if (conferir("RN01 negativo", 0.0, novaVan(-5.0).getAutonomiaBateria())) casosOk++;
        if (conferir("RN01 acima de 100", 0.0, novaVan(120.0).getAutonomiaBateria())) casosOk++;
        if (conferir("RN01 limite inferior", 0.0, novaVan(0.0).getAutonomiaBateria())) casosOk++;
        if (conferir("RN01 meio", 50.0, novaVan(50.0).getAutonomiaBateria())) casosOk++;
        if (conferir("RN01 limite superior", 100.0, novaVan(100.0).getAutonomiaBateria())) casosOk++;

        // Consumo por quilômetro de cada tipo de veículo
        VanEletrica van = novaVan(100.0);
        van.registrarViagem(50.0);
        if (conferir("RN02 van 50 km", 75.0, van.getAutonomiaBateria())) casosOk++;

        BicicletaEletrica bicicleta = new BicicletaEletrica(2, "Bicicleta de teste", 30.0, 100.0, 25.0);
        bicicleta.registrarViagem(50.0);
        if (conferir("RN03 bicicleta 50 km", 90.0, bicicleta.getAutonomiaBateria())) casosOk++;

        // Viagem maior que a carga disponível não deixa a bateria negativa
        VanEletrica vanQuaseVazia = novaVan(10.0);
        vanQuaseVazia.registrarViagem(100.0);
        if (conferir("RN04 esgotamento", 0.0, vanQuaseVazia.getAutonomiaBateria())) casosOk++;

        System.out.printf(Locale.ROOT, "Verificação concluída: %d/%d casos OK%n", casosOk, TOTAL_CASOS);
        if (casosOk != TOTAL_CASOS) {
            System.exit(1);
        }
    }

    private static VanEletrica novaVan(double bateria) {
        return new VanEletrica(1, "Van de teste", 1500.0, bateria, "TST0A00");
    }

    private static boolean conferir(String caso, double esperado, double obtido) {
        if (Math.abs(obtido - esperado) < 1e-9) {
            System.out.println("[OK] " + caso);
            return true;
        }
        System.out.printf(Locale.ROOT, "[FALHA] %s: esperado %.1f, obtido %.1f%n", caso, esperado, obtido);
        return false;
    }
}
