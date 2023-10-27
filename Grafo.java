import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Grafo {

    private int Grafo[][];

    private int Cordenada[][];

    private int vertices;

    private int arestas;

    private boolean direcionado = false;

    public int getVertices() {
        return vertices;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public int getArestas() {
        return arestas;
    }

    public void setArestas(int arestas) {
        this.arestas = arestas;
    }

    public boolean isDirecionado() {
        return direcionado;
    }

    public void setDirecionado(boolean direcionado) {
        this.direcionado = direcionado;
    }

    public void lerArquivo(String nomeArquivo){
        try {
            File arquivoGrafo = new File(nomeArquivo);
            Scanner e = new Scanner(arquivoGrafo);
            String direcionado = e.nextLine();
            if(direcionado.equals("nao")){
                this.direcionado = false;
            }else{
                this.direcionado = true;
            }
            vertices = e.nextInt();
            Grafo = new int[vertices][vertices];
            for(int i = 0; i<vertices;i++){
                for (int j = 0; j<vertices; j++){
                    Grafo[i][j] = -1;
                }
            }
            Cordenada = new int[2][vertices];
            for(int i = 0; i<vertices;i++){
                e.nextInt();
                Cordenada[0][i] = e.nextInt();
                Cordenada[1][i] = e.nextInt();
            }
            arestas = e.nextInt();
            for(int i = 0; i<arestas;i++){
                int origem = e.nextInt();
                int destino = e.nextInt();
                if(this.direcionado){
                    Grafo[origem][destino] = e.nextInt();
                }else {
                    int peso = e.nextInt();
                    Grafo[origem][destino] = peso;
                    Grafo[destino][origem] = peso;
                }
            }
            e.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERRO!");
            e.printStackTrace();
        }
    }

    public void exportaGrafo(){
        try {
            File arquivo = new File("src/grafo.txt");
            arquivo.createNewFile();
        } catch(IOException e) {
            System.out.println("Falha");
        }

        try {
            FileWriter arquivo = new FileWriter("src/grafo.txt");
            if(direcionado){
                arquivo.write("sim\n");
            }else{
                arquivo.write("nao\n");
            }
            arquivo.write(vertices + "\n");
            for(int i = 0; i < vertices; i++){
                arquivo.write(i+" "+Cordenada[0][i]+" "+Cordenada[1][i]+"\n");
            }
            arquivo.write(arestas+"\n");
            for(int i = 0; i<vertices ; i++){
                for(int j=0; j<vertices; j++){
                    if(Grafo[i][j] == -1 || j>i){
                        continue;
                    }else{
                        arquivo.write(i+" "+j+" "+Grafo[i][j]+"\n");
                    }
                }
            }
            arquivo.close();
        } catch(IOException e) {
            System.out.println("Falha");
        }
    }

    public void criarGrafoVazio(int tamanho, boolean direcionado){
        Scanner e = new Scanner(System.in);
        vertices = tamanho;
        Grafo = new int[vertices][vertices];
        Cordenada = new int[2][vertices];
        arestas = 0;
        this.direcionado = direcionado;
        for(int i = 0; i < vertices; i++){
            System.out.println("Digite a coordenada x: ");
            Cordenada[0][i] = e.nextInt();
            System.out.println("Digite a coordenada y: ");
            Cordenada[1][i] = e.nextInt();
            for (int j = 0; j<vertices; j++){
                Grafo[i][j] = -1;
            }
        }
    }

    public void imprimeGrafo(){
        for(int i = 0; i < vertices; i++){
            for(int j = 0; j<vertices;j++){
                if(Grafo[i][j] == -1){
                    System.out.print("X ");
                }else{
                    System.out.print(Grafo[i][j]+" ");
                }
            }
            System.out.println();
        }
    }

    public int consulta(int origem, int destino){
        return Grafo[origem][destino];
    }

    public void removeAresta(int origem, int destino) {
        if (direcionado) {
            Grafo[origem][destino] = -1;
        } else {
            Grafo[origem][destino] = -1;
            Grafo[destino][origem] = -1;
        }
    }
    public void insereAresta(int origem, int destino, int peso){
        if (direcionado) {
            Grafo[origem][destino] = peso;
        } else {
            Grafo[origem][destino] = peso;
            Grafo[destino][origem] = peso;
        }
    }

    public void alteraCords(int vertice, int x, int y){
        Cordenada[0][vertice] = x;
        Cordenada[1][vertice] = y;
    }

    public int primeiroAdjacente(int vertice){
        for(int i = 0; i<vertices;i++){
            if(Grafo[vertice][i] != -1){
                return i;
            }else{
                continue;
            }
        }
        return -1;
    }

    public  int proximoAdjacente(int origem, int destino){
        for(int i = destino+1;i<vertices;i++){
            if(Grafo[origem][i] != -1){
                return i;
            }else {
                continue;
            }
        }
        return -1;
    }

    public void listaAdjacente(int vertice){
        boolean existe = false;
        System.out.println("adjacentes: ");
        for (int i = 0; i<vertices;i++){
            if(Grafo[vertice][i] != -1){
                System.out.println(i + " ");
                existe = true;
            }else {
                continue;
            }
        }
        if(!existe){
            System.out.println("nenhuma adjacencia no grafo");
        }
    }

    public void Menu(){
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("| 1- Importar grafo");
        System.out.println("| 2- Criar grafo vazio");
        System.out.println("| 3- Imprimir grafo");
        System.out.println("| 4- Consultar se um vértice é adjacente");
        System.out.println("| 5- Inserir aresta");
        System.out.println("| 6- Remover aresta");
        System.out.println("| 7- Editar coordenadas do vertice");
        System.out.println("| 8- Consultar primeira adjacente");
        System.out.println("| 9- Consultar proxima adjacente");
        System.out.println("| 10- Consultar lista de adjacente");
        System.out.println("| 11- Salvar e Sair");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    }

}