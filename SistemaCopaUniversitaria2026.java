// Feito por: Gustavo Miranda Albergaria Barreto e Felipe Bulcão Gonzalez Garcia
import java.util.Scanner;

public class SistemaCopaUniversitaria2026 {

    static Scanner scanner = new Scanner(System.in);

    // Os tamanhos 5, 11 e 20 vêm diretamente dos requisitos do projeto.
    static Selecao[] selecoes = new Selecao[5];
    static Jogo[] jogos = new Jogo[20];

    static class Jogador {
        String nome;
        int gols;
    }

    static class Selecao {
        String nome;
        int pontos;
        Jogador[] jogadores = new Jogador[11];

        // Soma apenas os gols dos jogadores, pois gol contra não entra para nenhum jogador.
        int calcularTotalGols() {
            int totalGols = 0;
            for (int jogador = 0; jogador < 11; jogador++) {
                totalGols += jogadores[jogador].gols;
            }
            return totalGols;
        }

        int contarJogadoresComPeloMenosUmGol() {
            int quantidade = 0;
            for (int jogador = 0; jogador < 11; jogador++) {
                if (jogadores[jogador].gols >= 1) {
                    quantidade++;
                }
            }
            return quantidade;
        }

        // Retorna o número de inscrição do jogador ou -1 se não encontrar.
        int buscarJogadorPorNome(String nomeJogador) {
            for (int jogador = 0; jogador < 11; jogador++) {
                if (jogadores[jogador].nome.equalsIgnoreCase(nomeJogador)) {
                    return jogador + 1;
                }
            }
            return -1;
        }

        void listarJogadores() {
            System.out.println("Jogadores da seleção " + nome + ":");
            for (int jogador = 0; jogador < 11; jogador++) {
                System.out.println((jogador + 1) + " - " + jogadores[jogador].nome);
            }
        }
    }

    static class Jogo {
        int numero;
        Selecao selecao1;
        Selecao selecao2;
        int golsSelecao1;
        int golsSelecao2;

        void mostrarConfronto() {
            System.out.println();
            System.out.println("Jogo " + numero);
            System.out.println(selecao1.nome + " x " + selecao2.nome);
        }

        // Regra do projeto: vitória = 3 pontos; empate = 1 ponto para cada.
        void atualizarPontuacao() {
            if (golsSelecao1 > golsSelecao2) {
                selecao1.pontos += 3;
            } else if (golsSelecao2 > golsSelecao1) {
                selecao2.pontos += 3;
            } else {
                selecao1.pontos++;
                selecao2.pontos++;
            }
        }
    }

    public static void main(String[] args) {
        int opcao;

        System.out.println("Copa Universitária UCSAL 2026");
        System.out.println();

        mostrarMenuInicial();
        opcao = Integer.parseInt(scanner.nextLine());

        switch (opcao) {
            case 1:
                cadastrarSelecoes();
                criarJogos();
                cadastrarResultados();
                menuConsultas();
                break;
            case 2:
                System.out.println("Até a próxima!");
                break;
            default:
                System.out.println("Erro: opção inválida.");
                break;
        }
    }

    public static void mostrarMenuInicial() {
        System.out.println("[1] Cadastrar seleções");
        System.out.println("[2] Fechar programa");
        System.out.println();
        System.out.println("Digite a opção desejada:");
    }

    public static void mostrarMenuConsultas() {
        System.out.println();
        System.out.println("===== CONSULTAS DA COPA =====");
        System.out.println("[1] Consultar seleção vencedora");
        System.out.println("[2] Consultar artilheiro(s)");
        System.out.println("[3] Consultar percentual de jogadores com mais de 5 gols");
        System.out.println("[4] Consultar percentual de jogadores de uma seleção com pelo menos 1 gol");
        System.out.println("[5] Listar desempenho das seleções");
        System.out.println("[6] Listar desempenho dos jogadores");
        System.out.println("[7] Fechar programa");
        System.out.println();
        System.out.println("Digite a opção desejada:");
    }

    public static void menuConsultas() {
        int opcao;

        do {
            mostrarMenuConsultas();
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    consultarSelecaoVencedora();
                    break;
                case 2:
                    consultarArtilheiros();
                    break;
                case 3:
                    consultarPercentualJogadoresMaisDeCincoGols();
                    break;
                case 4:
                    consultarPercentualJogadoresComGolPorSelecao();
                    break;
                case 5:
                    listarDesempenhoSelecoes();
                    break;
                case 6:
                    listarDesempenhoJogadores();
                    break;
                case 7:
                    System.out.println("Até a próxima!");
                    break;
                default:
                    System.out.println("Erro: opção inválida.");
                    break;
            }
        } while (opcao != 7);
    }

    public static void cadastrarSelecoes() {
        for (int selecao = 0; selecao < 5; selecao++) {
            selecoes[selecao] = new Selecao();

            System.out.println();
            System.out.println("Qual o nome da seleção " + (selecao + 1) + "?");
            selecoes[selecao].nome = scanner.nextLine();

            while (selecoes[selecao].nome.equals("")) {
                System.out.println("Erro: o nome da seleção não pode ficar vazio.");
                System.out.println("Qual o nome da seleção " + (selecao + 1) + "?");
                selecoes[selecao].nome = scanner.nextLine();
            }

            selecoes[selecao].pontos = 0;
            System.out.println("Seleção " + selecoes[selecao].nome + " cadastrada, seu número de inscrição é: " + (selecao + 1));

            cadastrarJogadores(selecao + 1);
        }
    }

    public static boolean cadastrarJogadores(int numeroInscricaoSelecao) {
        if (numeroInscricaoSelecao < 1 || numeroInscricaoSelecao > 5) {
            return false;
        }

        // O usuário vê inscrições de 1 a 5, mas o array usa índices de 0 a 4.
        int indiceSelecao = numeroInscricaoSelecao - 1;

        System.out.println();
        System.out.println("Cadastro dos jogadores da seleção " + selecoes[indiceSelecao].nome);

        for (int jogador = 0; jogador < 11; jogador++) {
            selecoes[indiceSelecao].jogadores[jogador] = new Jogador();

            System.out.println("Qual o nome do jogador " + (jogador + 1) + "?");
            selecoes[indiceSelecao].jogadores[jogador].nome = scanner.nextLine();

            while (selecoes[indiceSelecao].jogadores[jogador].nome.equals("")) {
                System.out.println("Erro: o nome do jogador não pode ficar vazio.");
                System.out.println("Qual o nome do jogador " + (jogador + 1) + "?");
                selecoes[indiceSelecao].jogadores[jogador].nome = scanner.nextLine();
            }

            selecoes[indiceSelecao].jogadores[jogador].gols = 0;
            System.out.println("Jogador(a) " + selecoes[indiceSelecao].jogadores[jogador].nome + " cadastrado(a), seu número de inscrição é: " + (jogador + 1));
        }

        return true;
    }

    public static void criarJogos() {
        int posicao = 0;

        /*
         * O primeiro for escolhe a primeira seleção do confronto.
         * O segundo for escolhe a adversária.
         * Quando os índices são diferentes, um jogo é criado.
         *
         * Como o loop também passa pelo confronto inverso, ele gera ida e volta:
         * seleção A x seleção B e seleção B x seleção A.
         */
        for (int selecao1 = 0; selecao1 < 5; selecao1++) {
            for (int selecao2 = 0; selecao2 < 5; selecao2++) {
                if (selecao1 != selecao2) {
                    jogos[posicao] = new Jogo();
                    jogos[posicao].numero = posicao + 1;
                    jogos[posicao].selecao1 = selecoes[selecao1];
                    jogos[posicao].selecao2 = selecoes[selecao2];
                    posicao++;
                }
            }
        }
    }

    public static void cadastrarResultados() {
        System.out.println();
        System.out.println("===== CADASTRO DOS RESULTADOS =====");

        for (int i = 0; i < 20; i++) {
            Jogo jogoAtual = jogos[i];
            jogoAtual.mostrarConfronto();

            System.out.println("Quantos gols a seleção " + jogoAtual.selecao1.nome + " fez?");
            jogoAtual.golsSelecao1 = Integer.parseInt(scanner.nextLine());

            while (jogoAtual.golsSelecao1 < 0) {
                System.out.println("Erro: o número de gols não pode ser negativo.");
                System.out.println("Quantos gols a seleção " + jogoAtual.selecao1.nome + " fez?");
                jogoAtual.golsSelecao1 = Integer.parseInt(scanner.nextLine());
            }

            System.out.println("Quantos gols a seleção " + jogoAtual.selecao2.nome + " fez?");
            jogoAtual.golsSelecao2 = Integer.parseInt(scanner.nextLine());

            while (jogoAtual.golsSelecao2 < 0) {
                System.out.println("Erro: o número de gols não pode ser negativo.");
                System.out.println("Quantos gols a seleção " + jogoAtual.selecao2.nome + " fez?");
                jogoAtual.golsSelecao2 = Integer.parseInt(scanner.nextLine());
            }

            registrarGolsDosJogadores(jogoAtual.selecao1, jogoAtual.golsSelecao1);
            registrarGolsDosJogadores(jogoAtual.selecao2, jogoAtual.golsSelecao2);
            jogoAtual.atualizarPontuacao();
        }
    }

    public static void registrarGolsDosJogadores(Selecao selecao, int quantidadeGols) {
        for (int gol = 0; gol < quantidadeGols; gol++) {
            int foiContra;
            int numeroInscricaoJogador;

            System.out.println();
            System.out.println("Gol " + (gol + 1) + " da seleção " + selecao.nome);
            System.out.println("Foi gol contra?");
            System.out.println("[1] Sim");
            System.out.println("[2] Não");
            foiContra = Integer.parseInt(scanner.nextLine());

            while (foiContra != 1 && foiContra != 2) {
                System.out.println("Erro: opção inválida.");
                System.out.println("[1] Sim");
                System.out.println("[2] Não");
                foiContra = Integer.parseInt(scanner.nextLine());
            }

            if (foiContra == 1) {
                // Gol contra não é computado para nenhum jogador.
                System.out.println("Gol contra registrado. Nenhum jogador receberá esse gol.");
            } else {
                selecao.listarJogadores();

                System.out.println("Digite o número do jogador que fez o gol:");
                numeroInscricaoJogador = Integer.parseInt(scanner.nextLine());

                while (numeroInscricaoJogador < 1 || numeroInscricaoJogador > 11) {
                    System.out.println("Erro: número de jogador inválido.");
                    System.out.println("Digite o número do jogador que fez o gol:");
                    numeroInscricaoJogador = Integer.parseInt(scanner.nextLine());
                }

                int indiceJogador = numeroInscricaoJogador - 1;
                selecao.jogadores[indiceJogador].gols++;
            }
        }
    }

    public static void consultarSelecaoVencedora() {
        Selecao selecaoVencedora = selecoes[0];

        for (int selecao = 1; selecao < 5; selecao++) {
            if (selecoes[selecao].pontos > selecaoVencedora.pontos) {
                selecaoVencedora = selecoes[selecao];
            } else if (selecoes[selecao].pontos == selecaoVencedora.pontos) {
                /*
                 * Critério de desempate:
                 * 1º maior pontuação;
                 * 2º maior número de gols marcados pelos jogadores;
                 * 3º menor número de inscrição.
                 *
                 * O terceiro critério já acontece naturalmente, pois começamos com a primeira
                 * seleção cadastrada e só trocamos se outra tiver mais pontos ou mais gols.
                 */
                if (selecoes[selecao].calcularTotalGols() > selecaoVencedora.calcularTotalGols()) {
                    selecaoVencedora = selecoes[selecao];
                }
            }
        }

        System.out.println();
        System.out.println("Seleção vencedora: " + selecaoVencedora.nome);
        System.out.println("Pontuação total: " + selecaoVencedora.pontos);
    }

    public static void consultarArtilheiros() {
        int maiorQuantidadeGols = 0;

        // Primeiro encontra a maior quantidade de gols feita por um jogador.
        for (int selecao = 0; selecao < 5; selecao++) {
            for (int jogador = 0; jogador < 11; jogador++) {
                if (selecoes[selecao].jogadores[jogador].gols > maiorQuantidadeGols) {
                    maiorQuantidadeGols = selecoes[selecao].jogadores[jogador].gols;
                }
            }
        }

        System.out.println();
        System.out.println("===== ARTILHEIRO(S) =====");

        // Depois exibe todos os jogadores que têm essa mesma quantidade de gols.
        for (int selecao = 0; selecao < 5; selecao++) {
            for (int jogador = 0; jogador < 11; jogador++) {
                if (selecoes[selecao].jogadores[jogador].gols == maiorQuantidadeGols) {
                    double mediaGolsPorPartida = selecoes[selecao].jogadores[jogador].gols / 8.0;

                    System.out.println("Jogador: " + selecoes[selecao].jogadores[jogador].nome);
                    System.out.println("Seleção: " + selecoes[selecao].nome);
                    System.out.println("Média de gols por partida: " + mediaGolsPorPartida);
                    System.out.println();
                }
            }
        }
    }

    public static void consultarPercentualJogadoresMaisDeCincoGols() {
        int jogadoresComMaisDeCincoGols = 0;
        double percentual;

        for (int selecao = 0; selecao < 5; selecao++) {
            for (int jogador = 0; jogador < 11; jogador++) {
                if (selecoes[selecao].jogadores[jogador].gols > 5) {
                    jogadoresComMaisDeCincoGols++;
                }
            }
        }

        percentual = (jogadoresComMaisDeCincoGols * 100.0) / 55;
        System.out.println();
        System.out.println("Percentual de jogadores com mais de 5 gols: " + percentual + "%");
    }

    public static void consultarPercentualJogadoresComGolPorSelecao() {
        int numeroInscricaoSelecao;
        double percentual;

        System.out.println();
        listarSelecoes();

        System.out.println("Digite o número da seleção:");
        numeroInscricaoSelecao = Integer.parseInt(scanner.nextLine());

        while (numeroInscricaoSelecao < 1 || numeroInscricaoSelecao > 5) {
            System.out.println("Erro: número de seleção inválido.");
            System.out.println("Digite o número da seleção:");
            numeroInscricaoSelecao = Integer.parseInt(scanner.nextLine());
        }

        int indiceSelecao = numeroInscricaoSelecao - 1;
        percentual = (selecoes[indiceSelecao].contarJogadoresComPeloMenosUmGol() * 100.0) / 11;

        System.out.println();
        System.out.println("Seleção: " + selecoes[indiceSelecao].nome);
        System.out.println("Percentual de jogadores com pelo menos 1 gol: " + percentual + "%");
    }

    public static void listarDesempenhoSelecoes() {
        System.out.println();
        System.out.println("===== DESEMPENHO DAS SELEÇÕES =====");

        for (int selecao = 0; selecao < 5; selecao++) {
            double mediaGolsPorPartida = selecoes[selecao].calcularTotalGols() / 8.0;

            System.out.println("Seleção: " + selecoes[selecao].nome);
            System.out.println("Pontuação total: " + selecoes[selecao].pontos);
            System.out.println("Média de gols por partida: " + mediaGolsPorPartida);
            System.out.println();
        }
    }

    public static void listarDesempenhoJogadores() {
        System.out.println();
        System.out.println("===== DESEMPENHO DOS JOGADORES =====");

        for (int selecao = 0; selecao < 5; selecao++) {
            for (int jogador = 0; jogador < 11; jogador++) {
                System.out.println("Jogador: " + selecoes[selecao].jogadores[jogador].nome);
                System.out.println("Seleção: " + selecoes[selecao].nome);
                System.out.println("Gols marcados: " + selecoes[selecao].jogadores[jogador].gols);
                System.out.println();
            }
        }
    }

    public static String verNomeSelecao(int numeroInscricaoSelecao) {
        if (numeroInscricaoSelecao < 1 || numeroInscricaoSelecao > 5) {
            return "Invalido";
        }

        int indiceSelecao = numeroInscricaoSelecao - 1;
        return selecoes[indiceSelecao].nome;
    }

    public static String verNomeJogador(int numeroInscricaoSelecao, int numeroInscricaoJogador) {
        if (numeroInscricaoSelecao < 1 || numeroInscricaoSelecao > 5) {
            return "Invalido";
        }

        if (numeroInscricaoJogador < 1 || numeroInscricaoJogador > 11) {
            return "Invalido";
        }

        int indiceSelecao = numeroInscricaoSelecao - 1;
        int indiceJogador = numeroInscricaoJogador - 1;
        return selecoes[indiceSelecao].jogadores[indiceJogador].nome;
    }

    public static int verNumInscricaoSelecao(String nomeSelecao) {
        for (int selecao = 0; selecao < 5; selecao++) {
            if (selecoes[selecao].nome.equalsIgnoreCase(nomeSelecao)) {
                return selecao + 1;
            }
        }

        // -1 é usado como sinal de "não encontrado".
        return -1;
    }

    public static String verNumInscricaoJogador(String nomeJogador, String nomeSelecao) {
        int numeroInscricaoSelecao = verNumInscricaoSelecao(nomeSelecao);

        if (numeroInscricaoSelecao == -1) {
            return "-1";
        }

        int indiceSelecao = numeroInscricaoSelecao - 1;
        int numeroInscricaoJogador = selecoes[indiceSelecao].buscarJogadorPorNome(nomeJogador);

        if (numeroInscricaoJogador == -1) {
            return "Não Localizado";
        }

        return String.valueOf(numeroInscricaoJogador);
    }

    public static int verGolsSelecao(String nomeSelecao) {
        int numeroInscricaoSelecao = verNumInscricaoSelecao(nomeSelecao);

        if (numeroInscricaoSelecao == -1) {
            return -1;
        }

        int indiceSelecao = numeroInscricaoSelecao - 1;
        return selecoes[indiceSelecao].calcularTotalGols();
    }

    public static int verGolsJogador(String nomeJogador, String nomeSelecao) {
        int numeroInscricaoSelecao = verNumInscricaoSelecao(nomeSelecao);

        if (numeroInscricaoSelecao == -1) {
            return -1;
        }

        int indiceSelecao = numeroInscricaoSelecao - 1;
        int numeroInscricaoJogador = selecoes[indiceSelecao].buscarJogadorPorNome(nomeJogador);

        if (numeroInscricaoJogador == -1) {
            return -1;
        }

        int indiceJogador = numeroInscricaoJogador - 1;
        return selecoes[indiceSelecao].jogadores[indiceJogador].gols;
    }

    public static void listarSelecoes() {
        System.out.println("Seleções cadastradas:");
        for (int selecao = 0; selecao < 5; selecao++) {
            System.out.println((selecao + 1) + " - " + selecoes[selecao].nome);
        }
    }
}