import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Adepto {
    private String id;
    private String nome;
    private int idade;
    private String documento;
    private String endereco;
    private List<Bilhete> bilhetes;
    private double carteira;

    public Adepto(String id, String nome, int idade, String documento, String endereco, double carteira) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.documento = documento;
        this.endereco = endereco;
        this.bilhetes = new ArrayList<>();
        this.carteira = carteira;
        criarFicheiroAdepto();
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getDocumento() { return documento; }
    public String getEndereco() { return endereco; }
    public List<Bilhete> getBilhetes() { return bilhetes; }
    public double getCarteira() { return carteira; }
    public void setCarteira(double carteira) { this.carteira = carteira; }

    private void criarFicheiroAdepto() {
        try {
            File dir = new File("adeptos/" + id);
            dir.mkdirs();
            File bilhetesDir = new File("adeptos/" + id + "/bilhetes");
            bilhetesDir.mkdirs();

            File info = new File("adeptos/" + id + "/info.txt");
            try (PrintWriter pw = new PrintWriter(new FileWriter(info))) {
                pw.println("ID: " + id);
                pw.println("Nome: " + nome);
                pw.println("Idade: " + idade);
                pw.println("CC: " + documento);
                pw.println("Endereco: " + endereco);
                pw.println("Carteira: " + carteira + "€");
            }
            System.out.println("Diretório criado: /adeptos/" + id + "/");
            System.out.println("Ficheiro criado: /adeptos/" + id + "/info.txt");
        } catch (IOException e) {
            System.out.println("Erro ao criar ficheiro do adepto: " + e.getMessage());
        }
    }

    public boolean comprarBilhete(Setor setor, int numeroLugar) {
        double preco = setor.getPrecoBase();
        if (carteira < preco) {
            System.out.println("Saldo insuficiente! Carteira: " + carteira + "€, Preço: " + preco + "€");
            return false;
        }
        if (!setor.ocuparLugar(numeroLugar)) {
            System.out.println("Lugar não disponível!");
            return false;
        }
        String bilheteId = setor.getId() + numeroLugar;
        Bilhete bilhete = new Bilhete(bilheteId, setor, numeroLugar, preco);
        bilhetes.add(bilhete);

        double carteiraAnterior = carteira;
        carteira -= preco;

        System.out.println("Confirmação do bilhete:");
        System.out.println("- ID: " + bilheteId);
        System.out.println("- Setor: " + setor.getId());
        System.out.println("- Lugar: " + numeroLugar + " (Fila " + bilhete.getFila() + ", Posição " + bilhete.getPosicao() + ")");
        System.out.println("- Preço: " + (int) preco + "€");
        System.out.println("- Data Compra: " + bilhete.getDataCompraFormatada());
        System.out.println("Processando pagamento...");
        System.out.println("Carteira anterior: " + (int) carteiraAnterior + "€");
        System.out.println("Pagamento efetuado: -" + (int) preco + "€");
        System.out.println("Carteira atual: " + (int) carteira + "€");

        salvarBilhete(bilhete);
        return true;
    }

    private void salvarBilhete(Bilhete bilhete) {
        try {
            File file = new File("adeptos/" + id + "/bilhetes/" + bilhete.getId() + ".txt");
            try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
                pw.print(bilhete.toFileContent());
            }
            System.out.println("Ficheiro criado: /adeptos/" + id + "/bilhetes/" + bilhete.getId() + ".txt");
        } catch (IOException e) {
            System.out.println("Erro ao salvar bilhete: " + e.getMessage());
        }
    }

    public boolean comprarComida(Rolote rolote, List<Produto> produtosSelecionados, List<Integer> quantidades) {
        double total = 0;
        for (int i = 0; i < produtosSelecionados.size(); i++) {
            total += produtosSelecionados.get(i).getPreco() * quantidades.get(i);
        }
        if (carteira < total) {
            System.out.println("Saldo insuficiente!");
            return false;
        }
        for (int i = 0; i < produtosSelecionados.size(); i++) {
            rolote.vendeProduto(produtosSelecionados.get(i), quantidades.get(i));
        }
        carteira -= total;
        System.out.printf("O seu total é de %.2f€.%n", total);
        System.out.printf("A sua carteira atual é de %.2f€.%n", carteira);
        return true;
    }

    public double consultarGastoTotal() {
        double total = 0;
        for (Bilhete b : bilhetes) total += b.getPreco();
        return total;
    }
}
