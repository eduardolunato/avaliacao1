import java.util.InputMismatchException;
import java.util.Scanner;

import controlePiloto.Impl.Aeronave;
import controlePiloto.Impl.Piloto;
import controlePiloto.Impl.Excecoes.PilotoNaoEncontradoException;

public class App {
    private final static int TAMANHO_INICIAL_LISTAS = 10;
    private static Scanner scanner = new Scanner(System.in);
    private static Piloto[] _pilotos = new Piloto[TAMANHO_INICIAL_LISTAS];
    private static int _numeroPilotos = 0;
    private static Aeronave[] _aeronaves = new Aeronave[TAMANHO_INICIAL_LISTAS];
    private static int _numeroAeronaves = 0;

    public static void main(String[] args) throws Exception {
        boolean continuarExecutando = true;
        do {
            try {
                imprimirMenu();
                int opcao = lerOpcao();
                continuarExecutando = executarOpcao(opcao);
            } catch (Exception e) {
                System.out.println("Ocorreu um erro durante a operação: " + e.getMessage());
                continuarExecutando = true;
            }
        } while (continuarExecutando);
    }

    private static boolean executarOpcao(int opcao) throws Exception {
        switch (opcao) {
            case 1: {
                cadastrarPiloto();
                break;
            }
            case 2: {
                cadastrarAeronave();
                break;
            }
            case 3: {
                listarPilotos();
                break;
            }

            case 4: {
                listarAeronaves();
                break;
            }


            case 5: {
                buscarPilotoCPF();
                break;
            }

            case 6: {
                aumentarEspaco();
                break;
            }


            case 9: {
                System.out.println("Saindo do sistema...");
                return false;
            }


            default: {
                System.out.println("Ainda não implementado!");
                break;
            }
        }

        return true;
    }

    private static void listarPilotos() {
        System.out.println("MENU > Lista de pilotos cadastrados:");
        for (int i = 0; i < _numeroPilotos; i++) {
            System.out.println(_pilotos[i]);
        }
    }

    private static void listarAeronaves() {
        System.out.println("MENU > Lista de Aeronaves cadastradas:");
        for (int i = 0; i < _numeroAeronaves; i++) {
            System.out.println(_aeronaves[i]);
        }
    }

    private static void cadastrarAeronave() throws PilotoNaoEncontradoException {
        System.out.println("MENU > Cadastro de Aeronaves");
        System.out.println("Digite a marca da Aeronave:");
        String marca = scanner.nextLine();
        System.out.println("Digite o chassi da Aeronave:");
        int chassi = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite o CPF do piloto:");
        String cpf = scanner.nextLine();
        Piloto piloto = buscarPiloto(cpf);

        Aeronave aeronave = new Aeronave(marca, chassi, piloto);
        adicionarAeronaveNaLista(aeronave);
    }

    private static void adicionarAeronaveNaLista(Aeronave aeronave) {
        if (_numeroAeronaves == _aeronaves.length) {
            Aeronave[] novaLista = new Aeronave[_aeronaves.length * 2];
            
            // Copio os elementos da lista antiga para a nova lista.
            for (int i = 0; i < _aeronaves.length; i++) {
                novaLista[i] = _aeronaves[i];
            }

            // Substituo a lista antiga pela nova.
            _aeronaves = novaLista;
        }

        // Adiciono o aeronave a lista.
        _aeronaves[_numeroAeronaves] = aeronave;
        _numeroAeronaves++;        
    }

    private static Piloto buscarPiloto(String cpf) throws PilotoNaoEncontradoException {
        for (Piloto piloto: _pilotos) {
            if (piloto != null && piloto.getCpf().equals(cpf)) {
                return piloto;
            }
        }

        throw new PilotoNaoEncontradoException(cpf);
    }

    private static Piloto buscarPilotoCPF() throws PilotoNaoEncontradoException {

        System.out.println("MENU > Buscar Piloto \n");
        System.out.println("Digite o cpf: ");
        String cpf = scanner.nextLine();

        for (Piloto piloto: _pilotos) {
            if (piloto != null && piloto.getCpf().equals(cpf)) {
                System.out.println(piloto);
                return piloto;

            }
        }

        throw new PilotoNaoEncontradoException(cpf);
    }


    
    private static void adicionarPilotoNaLista(Piloto piloto) {
        if (_numeroPilotos == _pilotos.length) {
            Piloto[] novaLista = new Piloto[_pilotos.length ];
            
            // Copio os elementos da lista antiga para a nova lista.
            for (int i = 0; i < _pilotos.length; i++) {
                novaLista[i] = _pilotos[i];
            }

            // Substituo a lista antiga pela nova.
            _pilotos = novaLista;
        }

        // Adiciono o piloto a lista.
        _pilotos[_numeroPilotos] = piloto;
        _numeroPilotos++;
    }

    private static void cadastrarPiloto() throws InputMismatchException {

        if (_pilotos.length == _numeroPilotos) {
            System.out.println("Espaço insuficiente, considere aumentar o espaço da Lista de Pilotos");  
        }

        if(_pilotos.length != _numeroPilotos) {

        System.out.println("Cadastro de pilotos");
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Matricula: ");
        int matricula = Integer.parseInt(scanner.nextLine());
        System.out.println("Breve: ");
        String breve = scanner.nextLine();
        System.out.println("CPF: ");
        String cpf = scanner.nextLine();

        Piloto piloto = new Piloto(nome, matricula, breve, cpf);
        adicionarPilotoNaLista(piloto);
        }
    }

    private static void aumentarEspaco() throws InputMismatchException {
        System.out.println("MENU > Aumentar espaço de armazenamento");
        System.out.println("Tamanho Atual: "+_pilotos.length);
        System.out.println("Digite um Novo Tamanho: ");
        int tamanho = Integer.parseInt(scanner.nextLine());

        if (tamanho < _numeroPilotos) {
            System.out.println("Digite um tamanho válido");  
        }
        if (tamanho >= _numeroPilotos) {
            Piloto[] novaLista = new Piloto[tamanho];

        
            // Copio os elementos da lista antiga para a nova lista.
            for (int i = 0; i < _numeroPilotos; i++) {
                novaLista[i] = _pilotos[i];
            }
            // Substituo a lista antiga pela nova.
            _pilotos = novaLista;

            System.out.println("Novo Tamanho: "+_pilotos.length);  


        }

    }




    private static void imprimirMenu() {

        System.out.println("\n************\n    MENU    \n************\n");
        System.out.println("1 - Cadastrar piloto");
        System.out.println("2 - Cadastrar Aeronave");
        System.out.println("3 - Listar pilotos cadastrados");
        System.out.println("4 - Listar aeronaves cadastradas");        
        System.out.println("5 - Localizar piloto pelo CPF");
        System.out.println("6 - Aumentar espaço de armazenamento");
        System.out.println("9 - Sair");
        System.out.print("Opção: ");



    }

    private static boolean validarOpcaoMenu(int opcao) {
        return ((opcao >= 1 && opcao <= 6) || opcao == 9 );
    }

    private static int lerOpcao() {
        int opcao = 0;
        do {
            System.out.println("Selecione a opção desejada: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                if (!validarOpcaoMenu(opcao)) {
                    System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Opção inválida!");
                scanner.nextLine();
            }
        } while (!validarOpcaoMenu(opcao));

        return opcao;
    }
}
