import java.util.ArrayList;
import java.util.List;

public class Equipa {
    private String nome;
    private String cidade;
    private int dataFundacao;
    private List<String> plantel;
    private String treinador;

    public Equipa(String nome, String cidade, int dataFundacao, String treinador) {
        this.nome = nome;
        this.cidade = cidade;
        this.dataFundacao = dataFundacao;
        this.treinador = treinador;
        this.plantel = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public int getDataFundacao() { return dataFundacao; }
    public void setDataFundacao(int dataFundacao) { this.dataFundacao = dataFundacao; }
    public List<String> getPlantel() { return plantel; }
    public String getTreinador() { return treinador; }
    public void setTreinador(String treinador) { this.treinador = treinador; }

    public void adicionarJogador(String jogador) {
        plantel.add(jogador);
    }

    public String getJogadorAleatorio() {
        if (plantel.isEmpty()) return "Jogador Desconhecido";
        int idx = (int)(Math.random() * plantel.size());
        return plantel.get(idx);
    }

    @Override
    public String toString() {
        return nome + " (Cidade: " + cidade + ", Fundação: " + dataFundacao +
               ", Treinador: " + treinador + ", Plantel: " + plantel.size() + " jogadores)";
    }
}
