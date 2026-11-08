package telemetria;

/**
 * Modelo geral dos veículos da frota EcoFleet. Reúne o que vans e bicicletas
 * têm em comum e não pode ser instanciado diretamente: só existe para ser
 * especializado pelas subclasses.
 *
 * @author Antonio Santos
 */
public abstract class Veiculo {

    protected int idVeiculo;
    protected String modelo;
    protected double capacidadeCarga;   // em kg
    private double autonomiaBateria;    // nível de bateria, de 0.0 a 100.0

    public Veiculo() {
        this.idVeiculo = 0;
        this.modelo = "";
        this.capacidadeCarga = 0.0;
        this.autonomiaBateria = 0.0;
    }

    public double getAutonomiaBateria() {
        return autonomiaBateria;
    }

    public void setAutonomiaBateria(double bateria) {
        // Valor fora da faixa 0.0 a 100.0 é tratado como inválido e a bateria fica em 0.0
        if (bateria < 0.0 || bateria > 100.0) {
            this.autonomiaBateria = 0.0;
        } else {
            this.autonomiaBateria = bateria;
        }
    }

    public abstract void registrarViagem(double distancia);
}
