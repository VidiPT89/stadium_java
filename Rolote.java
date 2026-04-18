import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rolote {
    private int id;
    private String nome;
    private List<Produto> produtos;
    private boolean aberto;
    private double faturamentoDiario;

    public Rolote(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.produtos = new ArrayList<>();
        this.aberto = true;
        this.faturamentoDiario = 0;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public List<Produto> getProdutos() { return produtos; }
    public boolean isAberto() { return aberto; }
    public double getFaturamentoDiario() { return faturamentoDiario; }

    public void abrirRolote() { this.aberto = true; }
    public void fecharRolote() { this.aberto = false; }

    public void adicionaProduto(Produto produto) {
        produtos.add(produto);
    }

    public boolean vendeProduto(Produto produto, int quantidade) {
        if (!aberto) {
            System.out.println("Rolote fechada!");
            return false;
        }
        if (produto.getQuantidadeStock() < quantidade) {
            System.out.println("Stock insuficiente!");
            return false;
        }
        produto.setQuantidadeStock(produto.getQuantidadeStock() - quantidade);
        double total = produto.getPreco() * quantidade;
        faturamentoDiario += total;
        return true;
    }

    public double calcularFaturacao() {
        return faturamentoDiario;
    }

    public void imprimirMenu() {
        System.out.println("Id:" + id);
        System.out.println("Nome: " + nome);
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }
}
