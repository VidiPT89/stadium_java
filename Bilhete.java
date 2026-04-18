import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bilhete {
    private String id;
    private Setor setor;
    private int lugar;
    private double preco;
    private LocalDateTime dataCompra;

    public Bilhete(String id, Setor setor, int lugar, double preco) {
        this.id = id;
        this.setor = setor;
        this.lugar = lugar;
        this.preco = preco;
        this.dataCompra = LocalDateTime.now();
    }

    public String getId() { return id; }
    public Setor getSetor() { return setor; }
    public int getLugar() { return lugar; }
    public double getPreco() { return preco; }
    public LocalDateTime getDataCompra() { return dataCompra; }

    public String getDataCompraFormatada() {
        return dataCompra.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public int getFila() { return (lugar - 1) / 5 + 1; }
    public int getPosicao() { return (lugar - 1) % 5 + 1; }

    public String toFileContent() {
        return "ID: " + id + "\n" +
               "Setor: " + setor.getId() + "\n" +
               "Lugar: " + lugar + " (Fila " + getFila() + ", Posicao " + getPosicao() + ")\n" +
               "Preco: " + preco + "€\n" +
               "Data Compra: " + getDataCompraFormatada() + "\n";
    }

    @Override
    public String toString() {
        return "Bilhete " + id + " - Setor " + setor.getId() + " Lugar " + lugar +
               " (Fila " + getFila() + ", Pos " + getPosicao() + ") - " + preco + "€";
    }
}
