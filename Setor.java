import java.util.ArrayList;
import java.util.List;

public class Setor {
    private String id;
    private int capacidade;
    private boolean[][] lugares; // true = ocupado
    private double precoBase;

    public Setor(String id, double precoBase) {
        this.id = id;
        this.capacidade = 25;
        this.lugares = new boolean[5][5];
        this.precoBase = precoBase;
    }

    public String getId() { return id; }
    public int getCapacidade() { return capacidade; }
    public boolean[][] getLugares() { return lugares; }
    public double getPrecoBase() { return precoBase; }
    public void setPrecoBase(double precoBase) { this.precoBase = precoBase; }

    public int getLugaresOcupados() {
        int count = 0;
        for (boolean[] fila : lugares)
            for (boolean lugar : fila)
                if (lugar) count++;
        return count;
    }

    public int getLugaresDisponiveis() {
        return capacidade - getLugaresOcupados();
    }

    public List<String> verificarDisponibilidade() {
        List<String> disponiveis = new ArrayList<>();
        for (int fila = 0; fila < 5; fila++) {
            for (int col = 0; col < 5; col++) {
                if (!lugares[fila][col]) {
                    int numero = fila * 5 + col + 1;
                    disponiveis.add(id + numero);
                }
            }
        }
        return disponiveis;
    }

    public boolean ocuparLugar(int numero) {
        int idx = numero - 1;
        int fila = idx / 5;
        int col = idx % 5;
        if (fila < 0 || fila >= 5 || col < 0 || col >= 5) return false;
        if (lugares[fila][col]) return false;
        lugares[fila][col] = true;
        return true;
    }

    public void atualizarPrecos(double novoPreco) {
        this.precoBase = novoPreco;
    }

    public void imprimirVisualizacao() {
        for (int fila = 0; fila < 5; fila++) {
            StringBuilder sb = new StringBuilder("Fila " + (fila + 1) + ": ");
            for (int col = 0; col < 5; col++) {
                sb.append(lugares[fila][col] ? "[X]" : "[ ]");
            }
            int inicio = fila * 5 + 1;
            int fim = inicio + 4;
            sb.append(" (" + id + inicio + "-" + id + fim + ")");
            System.out.println(sb);
        }
    }

    public void imprimirGrid() {
        for (int fila = 0; fila < 5; fila++) {
            for (int col = 0; col < 5; col++) {
                System.out.print(lugares[fila][col] ? "[X]" : "[ ]");
            }
            System.out.print(" ");
        }
    }
}
