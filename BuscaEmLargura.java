import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BuscaEmLargura {
    private boolean[] visitados;
    private int[] antecessor;
    private int[] distancia;

    public BuscaEmLargura(GrafoValorado g, int verticeOrigem) {
        int numeroVertices = g.numeroVertices();
        visitados = new boolean[numeroVertices];
        antecessor = new int[numeroVertices];
        distancia = new int[numeroVertices];

        bfs(g, verticeOrigem);
    }

    private void bfs(GrafoValorado g, int verticeOrigem) {
        Queue<Integer> fila = new LinkedList<>();
        fila.offer(verticeOrigem);
        visitados[verticeOrigem] = true;
        antecessor[verticeOrigem] = -1;
        distancia[verticeOrigem] = 0;

        while (!fila.isEmpty()) {
            int verticeAtual = fila.poll();

            for (GrafoValorado.Aresta aresta : g.getListaAdjacencia()[verticeAtual]) {
                int verticeAdjacente = aresta.w;

                if (!visitados[verticeAdjacente]) {
                    fila.offer(verticeAdjacente);
                    visitados[verticeAdjacente] = true;
                    antecessor[verticeAdjacente] = verticeAtual;
                    distancia[verticeAdjacente] = distancia[verticeAtual] + 1;
                }
            }
        }
    }

    public void imprimirResultado() {
        System.out.println("Caminho percorrido e distâncias:");

        for (int i = 0; i < visitados.length; i++) {
            System.out.println("Vértice " + i + ":");
            System.out.println("Visitado: " + visitados[i]);
            System.out.println("Antecessor: " + antecessor[i]);
            System.out.println("Distância: " + distancia[i]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GrafoValorado g = new GrafoValorado(4);
        g.adicionarAresta(0, 1, 33);
        g.adicionarAresta(0, 2, 10);
        g.adicionarAresta(1, 2, 99);
        g.adicionarAresta(0, 3, 200);

        BuscaEmLargura busca = new BuscaEmLargura(g, 0);
        busca.imprimirResultado();
    }
}
