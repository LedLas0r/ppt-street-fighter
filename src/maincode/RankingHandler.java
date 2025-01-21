package maincode;

import java.io.*;
import java.util.*;

public class RankingHandler {
    private static final String PVP_FILE = "rankingpvp.txt";
    private static final String PVC_FILE = "rankingpvc.txt";

    public void savePvPPlayer(Player player) {
        savePlayer(player, PVP_FILE);
    }

    public void savePvCPlayer(Player player) {
        savePlayer(player, PVC_FILE);
    }

    private void savePlayer(Player player, String fileName) {
        try {
            File file = new File(fileName);
            List<String> lines = new ArrayList<>();

            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();
            }

            boolean playerExists = false;
            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts[0].equals(player.getName())) {
                    int victories = Integer.parseInt(parts[1]);
                    int defeats = Integer.parseInt(parts[2]);

                    victories += player.getnVictories();
                    defeats += player.getnDefeats();

                    lines.set(i, player.getName() + "," + victories + "," + defeats);
                    playerExists = true;
                    break;
                }
            }

            if (!playerExists) {
                lines.add(player.getName() + "," + player.getnVictories() + "," + player.getnDefeats());
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Player> getRanking(String fileName) {
        List<Player> players = new ArrayList<>();
        try {
            File file = new File(fileName);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    // Validar si la línea tiene al menos 3 elementos
                    String[] parts = line.split(",");
                    if (parts.length >= 3) {
                        try {
                            String name = parts[0];
                            int victories = Integer.parseInt(parts[1]);
                            int defeats = Integer.parseInt(parts[2]);

                            // Crear el jugador y ajustar sus victorias y derrotas
                            Player player = new Player(name);
                            for (int i = 0; i < victories; i++) {
                                player.increaseNVictories();
                            }
                            for (int i = 0; i < defeats; i++) {
                                player.increaseNDefeats();
                            }

                            players.add(player);
                        } catch (NumberFormatException e) {
                            System.err.println("Error al parsear victorias o derrotas para el jugador: " + parts[0]);
                        }
                    } else {
                        System.err.println("Línea mal formada: " + line);
                    }
                }
                reader.close();
            } else {
                System.err.println("El archivo " + fileName + " no existe.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ordenar los jugadores por winrate
        players.sort(Comparator.comparingDouble(Player::getWinrate).reversed());
        return players;
    }
}
