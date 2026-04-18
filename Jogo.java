import java.util.ArrayList;
import java.util.List;

public class Jogo {
    private String nome;
    private String dataHora;
    private Equipa equipaCasa;
    private Equipa equipaVisitante;
    private List<Bilhete> bilhetesVendidos;
    private int golosCasa;
    private int golosVisitante;
    private String arbitro;
    private List<Rolote> rolotes;
    private int lugaresOcupados;

    public Jogo(Equipa equipaCasa, Equipa equipaVisitante, String dataHora, String arbitro) {
        this.equipaCasa = equipaCasa;
        this.equipaVisitante = equipaVisitante;
        this.nome = equipaCasa.getNome() + " vs " + equipaVisitante.getNome();
        this.dataHora = dataHora;
        this.arbitro = arbitro;
        this.bilhetesVendidos = new ArrayList<>();
        this.golosCasa = 0;
        this.golosVisitante = 0;
    }

    public String getNome() { return nome; }
    public String getDataHora() { return dataHora; }
    public Equipa getEquipaCasa() { return equipaCasa; }
    public Equipa getEquipaVisitante() { return equipaVisitante; }
    public List<Bilhete> getBilhetesVendidos() { return bilhetesVendidos; }
    public String getArbitro() { return arbitro; }
    public void setArbitro(String arbitro) { this.arbitro = arbitro; }
    public void setLugaresOcupados(int lugaresOcupados) { this.lugaresOcupados = lugaresOcupados; }
    public void setRolotes(List<Rolote> rolotes) { this.rolotes = rolotes; }

    public void adicionarBilhete(Bilhete bilhete) {
        bilhetesVendidos.add(bilhete);
    }

    public String getResultado() {
        return equipaCasa.getNome() + " " + golosCasa + " - " + golosVisitante + " " + equipaVisitante.getNome();
    }

    public double calcularReceita(Setor[] setores) {
        double total = 0;
        for (Bilhete b : bilhetesVendidos) total += b.getPreco();
        if (setores != null) {
            for (Setor s : setores) {
                for (Bilhete b : bilhetesVendidos) {
                    // já somado acima
                }
            }
        }
        return total;
    }

    public void iniciarJogo() {
        System.out.println("\n=== Iniciando Jogo ===");
        System.out.println(nome);
        System.out.println("Data: " + dataHora.split(" ")[0]);
        System.out.println("Hora: " + dataHora.split(" ")[1]);
        System.out.println("Árbitro: " + arbitro);

        System.out.println("\nPrimeira parte:");
        System.out.println("1' Início do jogo");

        // Simulate 90 minutes: 1 second = 1 minute, check every 10 seconds
        for (int minuto = 10; minuto <= 90; minuto += 10) {
            try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            // 10% chance each team scores
            if (Math.random() < 0.10) {
                golosCasa++;
                String marcador = equipaCasa.getJogadorAleatorio();
                System.out.println(minuto + "' GOLO! " + equipaCasa.getNome() +
                        " (" + golosCasa + "-" + golosVisitante + ")");
                System.out.println("Marcador: " + marcador);
            }
            if (Math.random() < 0.10) {
                golosVisitante++;
                String marcador = equipaVisitante.getJogadorAleatorio();
                System.out.println(minuto + "' GOLO! " + equipaVisitante.getNome() +
                        " (" + golosCasa + "-" + golosVisitante + ")");
                System.out.println("Marcador: " + marcador);
            }

            if (minuto == 40) {
                System.out.println("45' Intervalo");
                System.out.println("\n=== Intervalo (5 segundos) ===");
                imprimirEstatisticasIntervalo();
                try { Thread.sleep(5000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                System.out.println("\nSegunda parte:");
                System.out.println("46' Recomeço");
            }
        }

        System.out.println("90' Fim do jogo");
        imprimirRelatorioFinal();
    }

    private void imprimirEstatisticasIntervalo() {
        int totalLugares = 100;
        int ocupados = lugaresOcupados > 0 ? lugaresOcupados : bilhetesVendidos.size();
        int pct = (int) Math.round((double) ocupados / totalLugares * 100);
        System.out.println("Estatísticas parciais:");
        System.out.println("- Ocupação: " + pct + "%");
        int rolotesAbertas = rolotes != null ? (int) rolotes.stream().filter(Rolote::isAberto).count() : 0;
        System.out.println("- Rolotes abertas: " + rolotesAbertas);
        double fat = rolotes != null ? rolotes.stream().mapToDouble(Rolote::getFaturamentoDiario).sum() : 0;
        System.out.println("- Faturamento rolotes: " + (int) fat + "€");
    }

    public void imprimirRelatorioFinal() {
        System.out.println("\n=== Relatório Final do Jogo ===");
        System.out.println("Resultado: " + getResultado());
    }
}
