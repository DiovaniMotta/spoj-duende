import java.io.*;
import java.util.*;

/**
 * 
 * @author Diovani Bernardi da Motta Data: 06/09/2016
 * 
 *         Gugo, o duende, ficou preso em uma caverna e precisa sair o mais
 *         rapidamente possivel. A caverna e formada por saloes interligados por
 *         tuneis, na forma de uma grade retangular, com N linhas e M colunas.
 *         Alguns dos saloes da caverna tem paredes de cristal. Duendes, como
 *         todos sabem, nao gostam de ficar em ambientes com qualquer tipo de
 *         cristal, pois seus organismos entram em ressonancia com a estrutura
 *         de cristais, e em casos extremos os duendes podem ate mesmo explodir.
 *         Compreensivelmente, Gugo nao quer entrar em nenhum salao com parede
 *         de cristal.
 * 
 *         A figura abaixo mostra uma caverna com quatro linhas e cinco colunas
 *         de saloes; os saloes cinza tem paredes de cristal. A posicao inicial
 *         de Gugo e indicada com um caractere *.
 * 
 * 
 * 
 *         Tarefa Voce deve escrever um programa que, dadas a configuracao da
 *         caverna e a posicao inicial de Gugo dentro da caverna, calcule qual o
 *         numero minimo de saloes pelos quais o duende deve passar antes de
 *         sair da caverna (nao contando o salao em que o duende esta
 *         inicialmente), mas contando o salao que tem saida para o exterior).
 * 
 *         Entrada A caverna sera modelada como uma matriz de duas dimensoes,
 *         cujos elementos representam os saloes. Um salao que nao tem parede de
 *         cristal e que tem saida para o exterior da caverna e representado
 *         pelo valor 0; um salao que nao tem parede de cristal e nao tem saida
 *         para o exterior e representado pelo valor 1; um salao que tem parede
 *         de cristal e representado pelo valor 2; e o salao em que o duende
 *         esta inicialmente (que nao tem saida para o exterior e nem paredes de
 *         cristal) e representado pelo valor 3. A figura abaixo mostra a
 *         representacao da caverna apresentada acima.
 * 
 * 
 * 
 *         A primeira linha da entrada contem dois numeros inteiros N e M que
 *         indicam respectivamente o numero de linhas (1 <= N <= 10) e o numero
 *         de colunas (1 <= M <= 10) da representacao da caverna. Cada uma das N
 *         linhas seguintes contem M numeros inteiros Ci, descrevendo os saloes
 *         da caverna e a posicao inicial do duende (0 <= Ci <= 3). Voce pode
 *         supor que sempre ha um trajeto que leva Gugo a saida da caverna.
 * 
 *         Saida Seu programa deve produzir uma unica linha na saida, contendo
 *         um numero inteiro representando a quantidade minima de saloes pelos
 *         quais Gugo deve passar antes de conseguir sair da caverna (nao
 *         contando o salao em que ele esta inicialmente, mas contando o salao
 *         que tem saida para o exterior).
 * 
 *         Exemplo 1 
 *         Entrada: 
 *         4 5 
 *         0 1 1 1 1 
 *         0 2 2 2 1 
 *         2 1 1 1 1 
 *         1 1 1 3 1
 * 
 *         Saida: 8 
 *         
 *         Exemplo 2 
 *         Entrada: 
 *         1 10 
 *         2 0 1 1 3 1 1 1 0 1
 * 
 *         Saida: 3
 *
 */
public class Main {

	public static int[] linhas = { -1, 0, 0, +1 };
	public static int[] colunas = { 0, -1, +1, 0 };


	/**
	 * 
	 * @author Diovani Classe que representa a especificacao, modelo empregado
	 *         para a solucao do problema
	 */
	public static class Grafo {

		public int l;
		public int c;
		public Vertice vertice;
		public int[][] arestas;

	}

	/**
	 * 
	 * @author Diovani Classe que representa a especificacao, modelo empregado
	 *         para a solucao do problema
	 */
	public static class Vertice {

		public int l;
		public int c;

		public Vertice(int l, int c) {
			this.c = c;
			this.l = l;
		}
	}

	/**
	 * Inicializacao do software
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		input();
	}


	/**
	 * Responsavel por implementar o algoritmo de busca em largura 
	 * 
	 * @param grafo
	 *            objeto contendo as definicoes do problema
	 * @return quantidade minima de saloes que ele deve passar
	 */
	public static int find(Grafo grafo) {
		int[][] visitados = new int[grafo.l][grafo.c];
		for (int i = 0; i < grafo.l; i++)
			for (int j = 0; j < grafo.c; j++)
				visitados[i][j] = 0;
		ArrayList<Vertice> fila = new ArrayList<Vertice>();
		fila.add(grafo.vertice);
		int inicio = 0;
		int line = 0;
		int column = 0;
		while (fila.size() != inicio) {
			line = fila.get(inicio).l;
			column = fila.get(inicio).c;
			for (int i = 0; i < 4; i++) {
				int nLinha = line + linhas[i];
				int nColuna = column + colunas[i];
				if (nLinha >= 0 && nLinha < grafo.l && nColuna >= 0
						&& nColuna < grafo.c) {
					if (visitados[nLinha][nColuna] == 0
							&& grafo.arestas[nLinha][nColuna] != 2) {
						visitados[nLinha][nColuna] = visitados[line][column] + 1;
						if (grafo.arestas[nLinha][nColuna] == 0)
							return visitados[nLinha][nColuna];
						else
							fila.add(new Vertice(nLinha, nColuna));
					}
				}
			}
			inicio++;
		}
		return visitados[line][column] + 1;
	}

	/**
	 * Responsavel por invocar o metodo de busca em largura e efetuar a
	 * impressao no console dos dados processados na busca
	 * 
	 * @param grafo
	 *            objeto contendo as definicoes do problema
	 */
	public static void process(Grafo grafo) {
		int resposta = find(grafo);
		System.out.println(resposta);
	}

	/**
	 * Responsavel por efetuar a captura das entradas de dados
	 */
	public static void input() {
		try {
			Grafo grafo = new Grafo();
			String linha = new String("");
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(System.in));
			linha = bufferedReader.readLine();
			StringTokenizer tokenizer = new StringTokenizer(linha);
			grafo.l = Integer.valueOf(tokenizer.nextToken());
			grafo.c = Integer.valueOf(tokenizer.nextToken());
			grafo.arestas = new int[grafo.l][grafo.c];
			grafo.vertice = new Vertice(0, 0);
			for (int i = 0; i < grafo.l; i++) {
				linha = bufferedReader.readLine();
				tokenizer = new StringTokenizer(linha);
				for (int j = 0; j < grafo.c; j++) {
					grafo.arestas[i][j] = Integer
							.valueOf(tokenizer.nextToken());
					if (grafo.arestas[i][j] == 3) {
						grafo.vertice.l = i;
						grafo.vertice.c = j;
					}
				}
			}
			process(grafo);
		} catch (Exception exception) {
			return;
		}
	}
}