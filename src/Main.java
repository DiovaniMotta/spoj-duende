import java.io.*;
import java.util.*;

class Main {
	class Posicao {
		public Posicao(int l, int c) {
			linha = l;
			coluna = c;
		}

		public int linha;
		public int coluna;
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		Main processando = new Main();
		processando.processa();

		System.exit(0);
	}

	int busca(int[][] mapa, int pontoPartidaL, int pontoPartidaC,
			int numLinhas, int numColunas) {
		int[][] matriz = new int[numLinhas][numColunas];

		for (int i = 0; i < numLinhas; i++) {
			for (int j = 0; j < numColunas; j++) {
				matriz[i][j] = 0;
			}
		}

		ArrayList<Posicao> filaVisitados = new ArrayList<Posicao>();

		filaVisitados.add(new Posicao(pontoPartidaL, pontoPartidaC));

		int lin = 0;
		int col = 0;

		int[] vetorLinha = { -1, 0, 0, +1 };
		int[] vetorColuna = { 0, -1, +1, 0 };
		int inicioFila = 0;
		while (filaVisitados.size() != inicioFila) {
			lin = filaVisitados.get(inicioFila).linha;
			col = filaVisitados.get(inicioFila).coluna;

			for (int i = 0; i < 4; i++) {
				int nLinha = lin + vetorLinha[i];
				int nColuna = col + vetorColuna[i];
				if (nLinha >= 0 && nLinha < numLinhas && nColuna >= 0
						&& nColuna < numColunas) {
					if (matriz[nLinha][nColuna] == 0
							&& mapa[nLinha][nColuna] != 2) {
						matriz[nLinha][nColuna] = matriz[lin][col] + 1;
						if (mapa[nLinha][nColuna] == 0) {
							return matriz[nLinha][nColuna];
						} else {
							filaVisitados.add(new Posicao(nLinha, nColuna));
							;
						}
					}
				}
			}

			inicioFila++;
		}

		return matriz[lin][col] + 1;
	}

	void processa() throws NumberFormatException, IOException {
		String line = "";

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		line = br.readLine();
		StringTokenizer tokenizer = new StringTokenizer(line);
		int numLinhas = Integer.valueOf(tokenizer.nextToken());
		int numColunas = Integer.valueOf(tokenizer.nextToken());

		int[][] mapa = new int[numLinhas][numColunas];

		int pontoPartidaL = 0;
		int pontoPartidaC = 0;
		for (int i = 0; i < numLinhas; i++) {
			line = br.readLine();
			tokenizer = new StringTokenizer(line);
			for (int j = 0; j < numColunas; j++) {
				mapa[i][j] = Integer.valueOf(tokenizer.nextToken());
				if (mapa[i][j] == 3) {
					pontoPartidaL = i;
					pontoPartidaC = j;
				}
			}
		}
		int resposta = busca(mapa, pontoPartidaL, pontoPartidaC, numLinhas,
				numColunas);
		System.out.println(resposta);
		return;
	}
}