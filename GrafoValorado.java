import java.util.ArrayList;

public class GrafoValorado {

    class Aresta {
        public int v;
        public int w;
        public int peso;

        public Aresta(int v, int w, int peso) {
            this.v = v;
            this.w = w;
            this.peso = peso;
        }
    }

    ArrayList<Aresta> listaAdjacencia[];
    private int numeroVertices;
    private int numeroArestas;

    public GrafoValorado(int numeroVertices) {
        this.numeroVertices = numeroVertices;
        this.listaAdjacencia = new ArrayList[this.numeroVertices];
        this.numeroArestas = 0;

        for (int i = 0; i < this.numeroVertices; i++) {
            this.listaAdjacencia[i] = new ArrayList<Aresta>();
        }
    }
    
    public ArrayList<Aresta>[] getListaAdjacencia() {
        return listaAdjacencia;
    }

    public void adicionarAresta(int v, int w, int peso) {
        Aresta e1 = new Aresta(v, w, peso);
        listaAdjacencia[v].add(e1);

        Aresta e2 = new Aresta(w, v, peso);
        listaAdjacencia[w].add(e2);

        this.numeroArestas++;
    }

    public void removerAresta(int v, int w) {
        // Percorre a lista de adjacência do vértice v
        for (int i = 0; i < listaAdjacencia[v].size(); i++) {
            Aresta aresta = listaAdjacencia[v].get(i);
            // Verifica se a aresta atual conecta o vértice v ao vértice w
            if (aresta.v == v && aresta.w == w) {
                // Remove a aresta da lista de adjacência do vértice v
                listaAdjacencia[v].remove(i);
                break;
            }
        }

        // Percorre a lista de adjacência do vértice w
        for (int i = 0; i < listaAdjacencia[w].size(); i++) {
            Aresta aresta = listaAdjacencia[w].get(i);
            // Verifica se a aresta atual conecta o vértice w ao vértice v
            if (aresta.v == w && aresta.w == v) {
                // Remove a aresta da lista de adjacência do vértice w
                listaAdjacencia[w].remove(i);
                break;
            }
        }

        this.numeroArestas--;
    }

    public int grau(int vertice) {
        return listaAdjacencia[vertice].size();
    }

    public int numeroVertices() {
        return numeroVertices;
    }

    public int numeroArestas() {
        return numeroArestas;
    }

    public String toDot() {
        String resultado = "graph G { " + System.lineSeparator();
        for (int i = 0; i < numeroVertices; i++) {
            resultado = resultado + "\t" + i + ";" + System.lineSeparator();
        }

        boolean[][] jaImpresso = new boolean[numeroVertices][numeroVertices];

        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < listaAdjacencia[i].size(); j++) {
                if (!jaImpresso[i][j]) {
                    resultado += "\t" + listaAdjacencia[i].get(j).v + "--" + listaAdjacencia[i].get(j).w + "  [label=" + listaAdjacencia[i].get(j).peso + "]" + ";" + System.lineSeparator();
                    jaImpresso[i][j] = true;
                    jaImpresso[listaAdjacencia[i].get(j).w][listaAdjacencia[i].get(j).v] = true;
                }
            }
        }

        resultado += "}";
        return resultado;
    }

    public static void main(String[] args) {
        GrafoValorado g = new GrafoValorado(4);
        g.adicionarAresta(0, 1, 33);
        g.adicionarAresta(0, 2, 10);
        g.adicionarAresta(1, 2, 99);
        g.adicionarAresta(0, 3, 200);

        System.out.println("Número de vértices: " + g.numeroVertices());
        System.out.println("Número de arestas: " + g.numeroArestas());

        System.out.println("Grau do vértice 0: " + g.grau(0));
        System.out.println("Grau do vértice 1: " + g.grau(1));
        System.out.println("Grau do vértice 2: " + g.grau(2));
        System.out.println("Grau do vértice 3: " + g.grau(3));

        System.out.println("Antes da remoção:");
        System.out.println(g.toDot());

        g.removerAresta(0, 1);

        System.out.println("Depois da remoção:");
        System.out.println(g.toDot());
    }
}
