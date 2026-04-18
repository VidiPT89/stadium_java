public class Produto {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private double preco;
    private int quantidadeStock;

    public Produto(String nome, double preco, int quantidadeStock) {
        this.id = contadorId++;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeStock = quantidadeStock;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getQuantidadeStock() { return quantidadeStock; }
    public void setQuantidadeStock(int quantidadeStock) { this.quantidadeStock = quantidadeStock; }

    @Override
    public String toString() {
        return id + ". " + nome + " - " + (int) preco + "€ (stock: " + quantidadeStock + ")";
    }
}
