import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        int origem;
        int destino;
        int peso;
        int x;
        int y;
        int opcao=0;
        int tamanho;
        int resp=0;
        boolean direcionado;
        Scanner e = new Scanner(System.in);
        while(opcao != 11){
            grafo.Menu();
            System.out.println("Digite a opcao desejada: ");
            opcao = e.nextInt();

            switch (opcao){
                case 1:
                    System.out.println("Importando os dados do grafo");
                    grafo.lerArquivo("src/dados.txt");
                    System.out.println("Grafo importado com sucesso!");
                    break;
                case 2:
                    System.out.println("Digite o tamanho do grafo: ");
                    tamanho = e.nextInt();
                    System.out.println("Digite se o grafo é direcionado ou não (1-sim ou 2-não)");
                    resp = e.nextInt();
                    if (resp==1){
                        direcionado=true;
                    }else{
                        direcionado=false;
                    }
                    grafo.criarGrafoVazio(tamanho,direcionado);
                    System.out.println("Grafo criado com sucesso!");
                    break;
                case 3:
                    System.out.println("Imprimindo o grafo: ");
                    grafo.imprimeGrafo();
                    break;
                case 4:
                    System.out.println("Digite a origem: ");
                    origem = e.nextInt();
                    System.out.println("Digite o destino");
                    destino = e.nextInt();
                    int adjacente = grafo.consulta(origem,destino);
                    if(adjacente != -1){
                        System.out.println("É adjacente e o peso da aresta é: "+adjacente);
                    }else{
                        System.out.println("Não são adjacentes");
                    }
                    break;
                case 5:
                    System.out.println("Digite a origem: ");
                    origem = e.nextInt();
                    System.out.println("Digite o destino: ");
                    destino = e.nextInt();
                    System.out.println("Digite o peso: ");
                    peso = e.nextInt();
                    grafo.insereAresta(origem,destino,peso);
                    break;
                case 6:
                    System.out.println("Digite a origem: ");
                    origem = e.nextInt();
                    System.out.println("Digite o destino: ");
                    destino = e.nextInt();
                    grafo.removeAresta(origem,destino);
                    break;
                case 7:
                    System.out.println("Digite a origem: ");
                    origem = e.nextInt();
                    System.out.println("Digite o vertice x: ");
                    x = e.nextInt();
                    System.out.println("Digite o vertice y: ");
                    y = e.nextInt();
                    grafo.alteraCords(origem,x,y);
                    break;
                case 8:
                    System.out.println("Digite a origem: ");
                    origem = e.nextInt();
                    if(grafo.primeiroAdjacente(origem) != -1) {
                        System.out.println("a primeira adjacencia é: " + grafo.primeiroAdjacente(origem));
                    }else{
                        System.out.println("esse vertice não tem adjacente");
                    }
                    break;
                case 9:
                    System.out.println("Digite a origem: ");
                    origem = e.nextInt();
                    System.out.println("Digite o destino: ");
                    destino = e.nextInt();
                    if(grafo.proximoAdjacente(origem,destino) != -1) {
                        System.out.println("a próxima adjacencia desse vertíce: " + grafo.proximoAdjacente(origem, destino));
                    }else {
                        System.out.println("esse vertice não tem adjacente");

                    }
                    break;
                case 10:
                    System.out.println("Digite o vertice: ");
                    origem = e.nextInt();
                    grafo.listaAdjacente(origem);
                    break;
                default:
                    System.out.println("Saindo do programa");
                    break;
            }
        }
        grafo.exportaGrafo();
    }

}