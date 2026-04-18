import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estadio {
    private long id;
    private String nome;
    private String localizacao;
    private int capacidadeMaxima;
    private Setor[] setores;
    private List<Rolote> rolotes;
    private List<Adepto> adeptos;
    private List<Bilhete> bilhetes;
    private Jogo jogo;

    public Estadio(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.capacidadeMaxima = 100;
        this.id = gerarIdAleatorio();
        this.rolotes = new ArrayList<>();
        this.adeptos = new ArrayList<>();
        this.bilhetes = new ArrayList<>();

        // Criar os 4 setores
        this.setores = new Setor[]{
            new Setor("A", 10),
            new Setor("B", 20),
            new Setor("C", 30),
            new Setor("D", 40)
        };
    }

    private long gerarIdAleatorio() {
        long min = 1_000_000_000L;
        long max = 9_999_999_999L;
        return min + (long)(Math.random() * (max - min));
    }

    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getLocalizacao() { return localizacao; }
    public int getCapacidadeMaxima() { return capacidadeMaxima; }
    public Setor[] getSetores() { return setores; }
    public List<Rolote> getRolotes() { return rolotes; }
    public List<Adepto> getAdeptos() { return adeptos; }
    public List<Bilhete> getBilhetes() { return bilhetes; }
    public Jogo getJogo() { return jogo; }
    public void setJogo(Jogo jogo) { this.jogo = jogo; }

    public Setor getSetor(String id) {
        for (Setor s : setores)
            if (s.getId().equalsIgnoreCase(id)) return s;
        return null;
    }

    public Adepto getAdeptoPorId(String id) {
        for (Adepto a : adeptos)
            if (a.getId().equalsIgnoreCase(id)) return a;
        return null;
    }

    public boolean adicionarRolote(Rolote rolote) {
        if (rolotes.size() >= 5) {
            System.out.println("Limite máximo de rolotes atingido (5).");
            return false;
        }
        rolotes.add(rolote);
        return true;
    }

    public void adicionarAdepto(Adepto adepto) {
        adeptos.add(adepto);
    }

    public String gerarProximoIdAdepto() {
        return String.format("AD%03d", adeptos.size() + 1);
    }

    public int calcularOcupacao() {
        int total = 0;
        for (Setor s : setores) total += s.getLugaresOcupados();
        return total;
    }

    public void imprimirVisualizacaoSetores() {
        System.out.println("Setor A:             Setor B:             Setor C:             Setor D:");
        for (int fila = 0; fila < 5; fila++) {
            for (Setor s : setores) {
                boolean[] row = s.getLugares()[fila];
                for (boolean b : row) System.out.print(b ? "[X]" : "[ ]");
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void iniciaJogo() {
        if (jogo == null) {
            System.out.println("Nenhum jogo criado!");
            return;
        }
        // Passa dados do estádio para o jogo
        jogo.setLugaresOcupados(calcularOcupacao());
        jogo.setRolotes(rolotes);
        jogo.iniciarJogo();

        // Relatório final detalhado
        System.out.println("\nEstatísticas do jogo:");
        System.out.println("1. Bilheteira");
        int ocupados = calcularOcupacao();
        System.out.println("- Lugares ocupados: " + ocupados + "/" + capacidadeMaxima);
        System.out.println("- Ocupação por setor:");
        double receitaBilheteira = 0;
        for (Setor s : setores) {
            System.out.println("  * Setor " + s.getId() + ": " + s.getLugaresOcupados() + "/25");
            receitaBilheteira += s.getLugaresOcupados() * s.getPrecoBase();
        }
        System.out.println("- Receita bilheteira: " + (int) receitaBilheteira + "€");

        System.out.println("2. Rolotes");
        double totalRolotes = 0;
        for (Rolote r : rolotes) {
            System.out.println("- Rolote #" + r.getId() + ": " + (int) r.getFaturamentoDiario() + "€");
            totalRolotes += r.getFaturamentoDiario();
        }
        System.out.println("- Faturação total rolotes: " + (int) totalRolotes + "€");

        double receitaTotal = receitaBilheteira + totalRolotes;
        System.out.printf("3. Receita total: %.0f€%n", receitaTotal);
        System.out.println("\nTodos os dados foram salvos.");
        System.out.println("Obrigado por usar o Sistema de Gestão do Estádio!");
    }
}
