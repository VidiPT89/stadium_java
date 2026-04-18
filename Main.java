import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Estadio estadio;
    private static Jogo jogo;

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Sistema de Gestão do Estádio!");

        // 1. Criar Estádio
        criarEstadio();

        // 2. Criar Jogo
        criarJogo();

        // 3. Criar primeira Rolote
        criarPrimeiraRolote();

        // 4. Criar primeiro Adepto
        criarAdepto();

        // 5. Comprar bilhetes
        comprarBilhetes();

        // 6. Menu pré-jogo
        menuPreJogo();
    }

    // ─── 1. Criar Estádio ────────────────────────────────────────────────────────
    private static void criarEstadio() {
        System.out.println("\nCriando Estádio...");
        System.out.print("Nome do estádio: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Localização: ");
        String local = scanner.nextLine().trim();

        estadio = new Estadio(nome, local);

        System.out.println("Nome: " + estadio.getNome());
        System.out.println("ID: " + estadio.getId());
        System.out.println("Localização: " + estadio.getLocalizacao());
        System.out.println("Setores criados:");
        for (Setor s : estadio.getSetores()) {
            System.out.println("- Setor " + s.getId() + ": " + s.getCapacidade() +
                               " lugares (" + (int) s.getPrecoBase() + "€)");
        }
        System.out.println("\nVisualização inicial dos setores:");
        System.out.println("(Legenda: [ ] = Livre, [X] = Ocupado)");
        estadio.imprimirVisualizacaoSetores();
    }

    // ─── 2. Criar Jogo ───────────────────────────────────────────────────────────
    private static void criarJogo() {
        System.out.println("\nCriando jogo...");

        System.out.print("Nome da equipa da casa: ");
        String nomeCasa = scanner.nextLine().trim();
        System.out.print("Cidade: ");
        String cidadeCasa = scanner.nextLine().trim();
        System.out.print("Ano de fundação: ");
        int fundCasa = lerInteiro();
        System.out.print("Treinador: ");
        String treinadorCasa = scanner.nextLine().trim();

        Equipa equipaCasa = new Equipa(nomeCasa, cidadeCasa, fundCasa, treinadorCasa);
        System.out.println("Plantel (11 jogadores, um por linha):");
        for (int i = 1; i <= 11; i++) {
            System.out.print("Jogador " + i + ": ");
            equipaCasa.adicionarJogador(scanner.nextLine().trim());
        }
        System.out.println("Equipa da Casa: " + nomeCasa);
        System.out.println("- Cidade: " + cidadeCasa);
        System.out.println("- Fundação: " + fundCasa);
        System.out.println("- Treinador: " + treinadorCasa);
        System.out.println("- Plantel: " + equipaCasa.getPlantel().size() + " jogadores carregados");

        System.out.print("\nNome da equipa visitante: ");
        String nomeVisit = scanner.nextLine().trim();
        System.out.print("Cidade: ");
        String cidadeVisit = scanner.nextLine().trim();
        System.out.print("Ano de fundação: ");
        int fundVisit = lerInteiro();
        System.out.print("Treinador: ");
        String treinadorVisit = scanner.nextLine().trim();

        Equipa equipaVisitante = new Equipa(nomeVisit, cidadeVisit, fundVisit, treinadorVisit);
        System.out.println("Plantel (11 jogadores, um por linha):");
        for (int i = 1; i <= 11; i++) {
            System.out.print("Jogador " + i + ": ");
            equipaVisitante.adicionarJogador(scanner.nextLine().trim());
        }
        System.out.println("Equipa Visitante: " + nomeVisit);
        System.out.println("- Cidade: " + cidadeVisit);
        System.out.println("- Fundação: " + fundVisit);
        System.out.println("- Treinador: " + treinadorVisit);
        System.out.println("- Plantel: " + equipaVisitante.getPlantel().size() + " jogadores carregados");

        System.out.print("\nData do jogo (DD/MM/AAAA): ");
        String data = scanner.nextLine().trim();
        System.out.print("Hora (HH:MM): ");
        String hora = scanner.nextLine().trim();
        System.out.print("Árbitro: ");
        String arbitro = scanner.nextLine().trim();

        jogo = new Jogo(equipaCasa, equipaVisitante, data + " " + hora, arbitro);
        estadio.setJogo(jogo);

        System.out.println("Data do jogo: " + data);
        System.out.println("Hora: " + hora);
        System.out.println("Jogo criado com sucesso: " + jogo.getNome());
    }

    // ─── 3. Criar Rolote ─────────────────────────────────────────────────────────
    private static void criarPrimeiraRolote() {
        System.out.println("\nCriando Rolote #1...");
        criarNovaRolote();
    }

    private static void criarNovaRolote() {
        int numeroRolote = estadio.getRolotes().size() + 1;
        if (numeroRolote > 5) {
            System.out.println("Limite máximo de rolotes atingido (5).");
            return;
        }
        System.out.print("Nome da rolote: ");
        String nomeRolote = scanner.nextLine().trim();
        Rolote rolote = new Rolote(numeroRolote, nomeRolote);

        System.out.print("Quantos produtos quer adicionar? ");
        int numProd = lerInteiro();
        System.out.println("Adicionando produtos:");
        for (int i = 1; i <= numProd; i++) {
            System.out.print("Nome do produto " + i + ": ");
            String nomeProd = scanner.nextLine().trim();
            System.out.print("Preço: ");
            double preco = lerDouble();
            System.out.print("Stock: ");
            int stock = lerInteiro();
            Produto p = new Produto(nomeProd, preco, stock);
            rolote.adicionaProduto(p);
            System.out.println(i + ". " + nomeProd + " - " + (int) preco + "€ (stock: " + stock + ")");
        }

        if (estadio.adicionarRolote(rolote)) {
            System.out.println("Estado: Aberto");
            System.out.println("Rolote #" + numeroRolote + " criada com sucesso!");
            System.out.println("Faturamento atual: 0€");
        }
    }

    // ─── 4. Criar Adepto ─────────────────────────────────────────────────────────
    private static void criarAdepto() {
        System.out.println("\nCriando novo adepto...");
        String id = estadio.gerarProximoIdAdepto();
        System.out.println("ID atribuído: " + id);

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Idade: ");
        int idade = lerInteiro();
        System.out.print("CC: ");
        String cc = scanner.nextLine().trim();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine().trim();
        System.out.print("Carteira inicial (€): ");
        double carteira = lerDouble();

        Adepto adepto = new Adepto(id, nome, idade, cc, endereco, carteira);
        estadio.adicionarAdepto(adepto);

        System.out.println("Status: Adepto registrado com sucesso!");
    }

    // ─── 5. Comprar Bilhetes ─────────────────────────────────────────────────────
    private static void comprarBilhetes() {
        System.out.println("\nQual o seu ID de adepto?");
        String idAdepto = scanner.nextLine().trim();
        Adepto adepto = estadio.getAdeptoPorId(idAdepto);
        if (adepto == null) {
            System.out.println("Adepto não encontrado.");
            return;
        }

        System.out.println("Quantos bilhetes quer?");
        int quantidade = lerInteiro();

        for (int i = 0; i < quantidade; i++) {
            System.out.println("Comprando bilhete para " + adepto.getNome() + " (" + adepto.getId() + ")...");
            System.out.println("Setores disponíveis:");
            for (Setor s : estadio.getSetores()) {
                System.out.println(s.getId() + " - " + (int) s.getPrecoBase() + "€ [" +
                        s.getLugaresOcupados() + "/" + s.getCapacidade() + " lugares]");
            }

            System.out.print("Seleção: Setor ");
            String setorId = scanner.nextLine().trim().toUpperCase();
            Setor setor = estadio.getSetor(setorId);
            if (setor == null) {
                System.out.println("Setor inválido.");
                i--;
                continue;
            }

            System.out.println("\n=== Estado atual do Setor " + setorId + " ===");
            System.out.println("Legenda:\n[ ] = Lugar disponível\n[X] = Lugar ocupado\n[S] = Sua seleção atual");
            setor.imprimirVisualizacao();

            List<String> disponiveis = setor.verificarDisponibilidade();
            if (disponiveis.isEmpty()) {
                System.out.println("Sem lugares disponíveis neste setor.");
                i--;
                continue;
            }
            System.out.print("Lugares disponíveis: ");
            System.out.println(String.join(", ", disponiveis));

            System.out.print("Selecione um lugar: ");
            String lugarStr = scanner.nextLine().trim().toUpperCase();

            // Parse lugar: e.g. "A7" -> setor A, lugar 7
            if (!lugarStr.startsWith(setorId)) {
                System.out.println("Lugar inválido para este setor.");
                i--;
                continue;
            }
            int numeroLugar;
            try {
                numeroLugar = Integer.parseInt(lugarStr.substring(setorId.length()));
            } catch (NumberFormatException e) {
                System.out.println("Lugar inválido.");
                i--;
                continue;
            }

            adepto.comprarBilhete(setor, numeroLugar);
        }
    }

    // ─── 6. Menu Pré-Jogo ────────────────────────────────────────────────────────
    private static void menuPreJogo() {
        while (true) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("O que deseja fazer?");
            System.out.println("1. Adicionar nova rolote");
            System.out.println("2. Criar novo adepto");
            System.out.println("3. Comprar comida");
            System.out.println("4. Comprar mais bilhetes");
            System.out.println("5. Estatísticas do Estádio");
            System.out.println("6. Iniciar jogo");
            System.out.print("> ");
            int opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    criarNovaRolote();
                    break;
                case 2:
                    criarAdepto();
                    break;
                case 3:
                    comprarComida();
                    break;
                case 4:
                    comprarBilhetes();
                    break;
                case 5:
                    mostrarEstatisticas();
                    break;
                case 6:
                    estadio.iniciaJogo();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // ─── Comprar Comida ──────────────────────────────────────────────────────────
    private static void comprarComida() {
        System.out.println("Qual o seu Id de adepto?");
        String idAdepto = scanner.nextLine().trim();
        Adepto adepto = estadio.getAdeptoPorId(idAdepto);
        if (adepto == null) {
            System.out.println("Adepto não encontrado.");
            return;
        }
        if (estadio.getRolotes().isEmpty()) {
            System.out.println("Sem rolotes disponíveis.");
            return;
        }

        System.out.println("===Rolotes disponíveis===");
        for (Rolote r : estadio.getRolotes()) {
            r.imprimirMenu();
        }

        System.out.println("De que rolote deseja comprar comida?");
        String nomeRolote = scanner.nextLine().trim();
        Rolote roloteEscolhida = null;
        for (Rolote r : estadio.getRolotes()) {
            if (r.getNome().equalsIgnoreCase(nomeRolote)) {
                roloteEscolhida = r;
                break;
            }
        }
        if (roloteEscolhida == null) {
            System.out.println("Rolote não encontrada.");
            return;
        }

        List<Produto> produtosSelecionados = new ArrayList<>();
        List<Integer> quantidades = new ArrayList<>();

        boolean continuar = true;
        while (continuar) {
            System.out.println("Pretende comprar que item?");
            int itemIdx = lerInteiro() - 1;
            if (itemIdx < 0 || itemIdx >= roloteEscolhida.getProdutos().size()) {
                System.out.println("Item inválido.");
                continue;
            }
            Produto produto = roloteEscolhida.getProdutos().get(itemIdx);
            System.out.println("Qual a quantidade?");
            int qtd = lerInteiro();
            produtosSelecionados.add(produto);
            quantidades.add(qtd);

            System.out.println("Pretende mais algum item? (S/N)");
            String resp = scanner.nextLine().trim();
            continuar = resp.equalsIgnoreCase("S");
        }

        adepto.comprarComida(roloteEscolhida, produtosSelecionados, quantidades);
    }

    // ─── Estatísticas ────────────────────────────────────────────────────────────
    private static void mostrarEstatisticas() {
        System.out.println("\n=== Lugares disponíveis ===");
        estadio.imprimirVisualizacaoSetores();

        if (estadio.getJogo() != null) {
            System.out.println("\n=== Jogo Agendado ===");
            Jogo j = estadio.getJogo();
            System.out.println(j.getNome());
            System.out.println("Data: " + j.getDataHora().split(" ")[0]);
            System.out.println("Hora: " + j.getDataHora().split(" ")[1]);
            System.out.println("Árbitro: " + j.getArbitro());
        }

        System.out.println("\n=== Rolotes ===");
        for (Rolote r : estadio.getRolotes()) {
            r.imprimirMenu();
        }

        System.out.println("\n=== Adeptos e bilhetes ===");
        for (Adepto a : estadio.getAdeptos()) {
            System.out.println("ID: " + a.getId());
            System.out.println("Nome: " + a.getNome());
            System.out.println("Bilhetes:" + a.getBilhetes().size());
        }
    }

    // ─── Helpers ─────────────────────────────────────────────────────────────────
    private static int lerInteiro() {
        while (true) {
            try {
                String linha = scanner.nextLine().trim();
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.print("Por favor insira um número inteiro: ");
            }
        }
    }

    private static double lerDouble() {
        while (true) {
            try {
                String linha = scanner.nextLine().trim();
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                System.out.print("Por favor insira um número: ");
            }
        }
    }
}
