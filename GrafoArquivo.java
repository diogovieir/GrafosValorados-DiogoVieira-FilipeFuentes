import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GrafoArquivo {
    public static GrafoValorado lerArquivoGrafo(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            // Lê a primeira linha do arquivo para obter o número de vértices
            int numeroVertices = Integer.parseInt(br.readLine());

            // Cria um novo objeto GrafoValorado com o número de vértices lido do arquivo
            GrafoValorado grafo = new GrafoValorado(numeroVertices);

            String linha;
            while ((linha = br.readLine()) != null) {
                // Divide a linha em tokens separados por espaço
                String[] tokens = linha.split(" ");

                // Obtém os valores do vértice v, vértice w e peso da aresta
                int v = Integer.parseInt(tokens[0]);
                int w = Integer.parseInt(tokens[1]);
                int peso = Integer.parseInt(tokens[2]);

                // Adiciona a aresta ao grafo
                grafo.adicionarAresta(v, w, peso);
            }

            return grafo;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean salvarArquivoGrafo(GrafoValorado g, String caminhoArquivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            // Escreve o número de vértices na primeira linha do arquivo
            bw.write(g.numeroVertices() + "\n");

            // Percorre as arestas do grafo e as escreve no arquivo
            for (int v = 0; v < g.numeroVertices(); v++) {
                for (GrafoValorado.Aresta aresta : g.getListaAdjacencia()[v]) {
                    int w = aresta.w;
                    int peso = aresta.peso;
                    bw.write(v + " " + w + " " + peso + "\n");
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static void main(String[] args) {
        // Ler grafo de um arquivo
        String caminhoArquivo = "/home/harpy/Desktop/TrabsExtraALEST/grafo.txt";
        GrafoValorado grafoLido = GrafoArquivo.lerArquivoGrafo(caminhoArquivo);
        if (grafoLido != null) {
            // Realizar operações com o grafo lido
            System.out.println("Grafo lido:");
            System.out.println(grafoLido.toDot());
        } else {
            System.out.println("Erro ao ler o arquivo.");
        }
    
        // Criar um novo grafo
        GrafoValorado novoGrafo = new GrafoValorado(4);
        novoGrafo.adicionarAresta(0, 1, 33);
        novoGrafo.adicionarAresta(0, 2, 10);
        novoGrafo.adicionarAresta(1, 2, 99);
        novoGrafo.adicionarAresta(0, 3, 200);
    
        // Salvar grafo em um arquivo
        String novoCaminhoArquivo = "/home/harpy/Desktop/TrabsExtraALEST/resultado.txt";
        boolean salvouArquivo = GrafoArquivo.salvarArquivoGrafo(novoGrafo, novoCaminhoArquivo);
        if (salvouArquivo) {
            System.out.println("Grafo salvo com sucesso.");
        } else {
            System.out.println("Erro ao salvar o arquivo.");
        }
    }
    
}


