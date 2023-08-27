import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class Matriz {
    public static void main(String[] args) {



        try {
           // aqui lee mis matrices a y b con extensión .mat
            double[][] MA= leer("a.mat");
            double[][] MB = leer("b.mat");
            // Calcular producto de matrices
            double[][] resultadoMatriz =ProductodeMatrices(MA, MB);
            // Escribir matriz resultado en archivo c.mat
            MatrizEscritura(resultadoMatriz, "c.mat");
            System.out.println("Producto de matrices calculado y almacenado en c.mat.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static double[][] leer(String filename) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            int Fila = fileInputStream.read();
            int Columna = fileInputStream.read();
            double[][] matriz1 = new double[Fila][Columna];
            for (int i = 0; i < Fila; i++) {
                for (int j = 0; j < Columna; j++) {
                    long Bits = 0;
                    for (int k = 0; k < 8; k++) {
                        Bits |= ((long) fileInputStream.read()) << (k * 8);
                    }
                    matriz1[i][j] = Double.longBitsToDouble(Bits);
                }
            }
            return matriz1;
        }
    }
    public static void MatrizEscritura(double[][] matriz, String filename) throws IOException {
        int fila = matriz.length;
        int columna = matriz[0].length;
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            fileOutputStream.write(fila);
            fileOutputStream.write(columna);
            for (int i = 0; i < fila; i++) {
                for (int j = 0; j < columna; j++) {
                    long Bits = Double.doubleToRawLongBits(matriz[i][j]);
                    for (int k = 0; k < 8; k++) {
                        fileOutputStream.write((int) (Bits >> (k * 8)));
                    }}}}}
    public static double[][] ProductodeMatrices (double[][] matrizA, double[][] matrizB) {
        int A = matrizA.length;
        int ColA = matrizA[0].length;
        int ColB = matrizB[0].length;
        double[][] resultado = new double[A][ColB];
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < ColB; j++) {
                double Producto = 0.0;
                for (int k = 0; k < ColA; k++) {
                    Producto += matrizA[i][k] * matrizB[k][j];
                }
                resultado[i][j] = Producto;}
        }
        return resultado; }}