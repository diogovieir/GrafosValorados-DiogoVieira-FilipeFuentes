import java.util.ArrayList;

public class BuscaEmProfundidade {
    private boolean[] visitados;
    private int[] antecessor;
    public int[] preordem;
    public int[] posordem;
    private int contadorPreordem;
    private int contadorPosordem;

    public static class Aresta {
        public int v;
        public int w;
        public int peso;

        public Aresta(int v, int w, int peso) {
            this.v = v;
            this.w = w;
            this.peso = peso;
        }
    }

    private ArrayList<Aresta>[] listaAdjacencia;

    public BuscaEmProfundidade(ArrayList<Aresta>[] listaAdjacencia, int verticeOrigem) {
        int numeroVertices = listaAdjacencia.length;
        visitados = new boolean[numeroVertices];
        antecessor = new int[numeroVertices];
        preordem = new int[numeroVertices];
        posordem = new int[numeroVertices];
        contadorPreordem = 0;
        contadorPosordem = 0;

        for (int i = 0; i < numeroVertices; i++) {
            visitados[i] = false;
            antecessor[i] = -1;
            preordem[i] = -1;
            posordem[i] = -1;
        }

        dfs(listaAdjacencia, verticeOrigem);
    }

    private void dfs(ArrayList<Aresta>[] listaAdjacencia, int vertice) {
        visitados[vertice] = true;
        preordem[vertice] = contadorPreordem++;

        ArrayList<Aresta> adjacencia = listaAdjacencia[vertice];
        for (Aresta aresta : adjacencia) {
            int verticeAdjacente = aresta.w;

            if (!visitados[verticeAdjacente]) {
                antecessor[verticeAdjacente] = vertice;
                dfs(listaAdjacencia, verticeAdjacente);
            }
        }

        posordem[vertice] = contadorPosordem++;
    }

    public void imprimirResultado() {
        System.out.println("Array de visitados:");
        for (int i = 0; i < visitados.length; i++) {
            System.out.println(i + ": " + visitados[i]);
        }

        System.out.println("Array de antecessor:");
        for (int i = 0; i < antecessor.length; i++) {
            System.out.println(i + ": " + antecessor[i]);
        }

        System.out.println("Array de preordem:");
        for (int i = 0; i < preordem.length; i++) {
            System.out.println(i + ": " + preordem[i]);
        }

        System.out.println("Array de posordem:");
        for (int i = 0; i < posordem.length; i++) {
            System.out.println(i + ": " + posordem[i]);
        }
    }

    public static void main(String[] args) {
        ArrayList<Aresta>[] listaAdjacencia = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            listaAdjacencia[i] = new ArrayList<>();
        }

        listaAdjacencia[0].add(new Aresta(0, 1, 33));
        listaAdjacencia[0].add(new Aresta(0, 2, 10));
        listaAdjacencia[1].add(new Aresta(1, 2, 99));
        listaAdjacencia[0].add(new Aresta(0, 3, 200));

        BuscaEmProfundidade busca = new BuscaEmProfundidade(listaAdjacencia, 0);
        busca.imprimirResultado();
    }
}
