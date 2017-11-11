import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class OpenCSV {
    static String worldFile = "C:/Users/norbe/Downloads/world.csv";
    static String line = "";
    static String splitCharacter = ";";
    static ArrayList<String> CityName = new ArrayList<>();
    static ArrayList<Integer> CityPopulation = new ArrayList<>();
    static ArrayList<String> CountryName = new ArrayList<>();
    static ArrayList<String> Continent = new ArrayList<>();
    static ArrayList<Double> Surface = new ArrayList<>();
    static ArrayList<String> Capital = new ArrayList<>();

    static LinkedHashSet<String> countries = new LinkedHashSet<>();
    static LinkedHashSet<Double> surf = new LinkedHashSet<>();
    static LinkedHashSet<String> cities = new LinkedHashSet<>();
    static LinkedHashSet<String> cont = new LinkedHashSet<>();
    static HashMap<String, Integer> countCoutries = new HashMap<>();

    static ArrayList[] objects = {CityName, CityPopulation, CountryName, Continent, Surface, Capital};

    public static void main(String args[]) {
        try (BufferedReader br = new BufferedReader(new FileReader(worldFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] info = line.split(splitCharacter);


                info[0] = info[0].replace("\"", "");
                info[2] = info[2].replace("\"", "");
                info[4] = info[4].replace("\"", "");
                info[5] = info[5].replace("\"", "");
                info[7] = info[7].replace("\"", "");
                info[7] = info[7].replace(",", ".");
                info[16] = info[16].replace("\"", "");

                objects[0].add(info[0]);

                if (info[2] != "\\t") {
                    objects[1].add(Integer.parseInt(info[2]));
                } else {
                    objects[1].add(0);
                }

                objects[2].add(info[4]);

                objects[3].add(info[5]);

                if (info[7] != "\\t") {
                    objects[4].add(Double.parseDouble(info[7]));
                } else {
                    objects[4].add(0);
                }

                objects[5].add(info[16]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        one();
        two();
        three();
        four();
    }

    static public void one() {
        countries.addAll(CountryName);

        for (String c : countries) {
            System.out.println("Country: " + c + " || City: " + CityName.get(CountryName.indexOf(c)));
        }
    }

    static public void two() {
        surf.addAll(Surface);

        for (Double d : surf) {
            if (d > 300.00) {
                System.out.println("Country: " + CountryName.get(Surface.indexOf(d)) + " || Surface: " + d);
            }
        }
    }

    static public void three() {
        cities.addAll(CityName);

        for (String c : cities) {
            int i = CityName.indexOf(c);
            int pop = CityPopulation.get(i);
            if (pop > 10000) {
                System.out.println("City: " + c + " || Country: " + CountryName.get(i) + " || Population: " +
                        CityPopulation.get(i));
            }
        }
    }

    static public void four() {
        cont.addAll(Continent);

        for (String c : cont) {
            countCoutries.put(c, 0);
        }

        for (String c : countries) {
            String flag = Continent.get(CountryName.indexOf(c));
            countCoutries.put(flag, countCoutries.get(flag) + 1);
        }
        for (Map.Entry<String, Integer> entry : countCoutries.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}

