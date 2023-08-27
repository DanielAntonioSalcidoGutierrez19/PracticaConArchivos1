import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {

        // en esta paste utilizare
        String archivo = "divina_comedia_sp.txt";
        Map<Integer, Integer> HistogramaCompleto = Histograma(archivo);
        LecturaHistograma(HistogramaCompleto);
    }
// en este apartatdo podre generar un histograma por medio de la clase Map
    public static void LecturaHistograma(Map<Integer, Integer> Datos) {
        for (Map.Entry<Integer, Integer> entry : Datos.entrySet()) {
            int LongitudFinal = entry.getKey();
            int Repeticiones = entry.getValue();
            System.out.println("Frecuencia de palabras de " +" "+ LongitudFinal + " caracteres: " + Repeticiones);
        }}
    public static Map<Integer, Integer> Histograma(String herramienta) {
        Map<Integer, Integer> Lng = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(herramienta))) {
            // con este string yo podre leer las lineas del codigo
            String ln;
            Pattern wordPattern = Pattern.compile("\\b\\w+\\b");
// PODEMOS OBTNER UNA INSTANCIA DE CARACTERES DEFINIDOS
            while ((ln = reader.readLine()) != null) {
                Matcher mt = wordPattern.matcher(ln);
                while (mt.find()) {
                    String palabras = mt.group();
                    if (palabras.matches("\\d+") || palabras.length() < 2 || palabras.length() > 10) {
                        continue;
                    }
                    //AQUI PUEDO DETERMINAR LA LONGITUD DE PALABRAS TOTALES
                    int Longitud = palabras.length();
                    Lng.put(Longitud, Lng.getOrDefault(Longitud, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Lng;
    }


}