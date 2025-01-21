# Piedra, Papel o Tijeras - Estilo Street Fighter

¡Bienvenido a **Piedra, Papel o Tijeras - Estilo Street Fighter**! Este proyecto reimagina el clásico juego con una ambientación inspirada en el famoso juego de lucha **Street Fighter**. Disfruta de la acción con efectos visuales, un sistema de ranking y modos para uno o dos jugadores.

## 🚀 Características principales

- **Modo 1 Jugador**: Juega contra la máquina y desafía tu estrategia.
- **Modo 2 Jugadores**: Enfréntate a un amigo y descubre quién es el mejor luchador.
- **Ranking dinámico**: Guarda tus estadísticas y compite por la mejor tasa de victorias.
- **Ambientación única**: Diseños y fondos basados en escenarios y personajes de Street Fighter.
- **Interfaz gráfica con JavaFX**: Estilo moderno y animaciones que mejoran la experiencia visual.

## 🎮 Modos de juego

### Modo 1 Jugador (Player vs Computer)
- Introduce tu nombre y desafía a la máquina.
- La salud de cada jugador se representa visualmente con barras de vida.
- Gana rondas para reducir la salud de tu oponente y proclamarte vencedor.

### Modo 2 Jugadores (Player vs Player)
- Ambos jugadores pueden ingresar sus nombres.
- Juega por turnos, seleccionando entre **Piedra**, **Papel** o **Tijeras**.
- ¡El primer jugador en agotar la salud de su oponente será el ganador!

### Sistema de Ranking
- Guarda los resultados de cada partida.
- Muestra los rankings para ambos modos, ordenados por la tasa de victorias.

## 📂 Estructura del proyecto

### Código principal
- `MainMenu.java`: Punto de entrada y menú principal.
- `PvCMode.java`: Lógica y diseño para el modo contra la máquina.
- `PvPMode.java`: Lógica y diseño para el modo jugador contra jugador.
- `Ranking.java`: Visualización del ranking.
- `RankingHandler.java`: Gestión de almacenamiento y recuperación de datos del ranking.

### Clases adicionales
- `Player.java`: Representa a un jugador, incluyendo sus estadísticas y salud.

### Estilos
- `styleMenu.css`: Estilo del menú principal.
- `stylePvC.css`: Estilo para el modo contra la máquina.
- `stylePvP.css`: Estilo para el modo jugador contra jugador.
- `styleRanking.css`: Estilo para la pantalla de ranking.

### Recursos visuales
- Fondos y gráficos incluidos en la carpeta `images/`:
  - **Escenarios de Street Fighter**.
  - Iconos personalizados para botones y barras de vida.
  - Animaciones para las acciones de los jugadores.

## 💻 Requisitos

1. **Java 11+**: Asegúrate de tener una versión actualizada del JDK instalada.
2. **JavaFX**: Necesario para ejecutar la interfaz gráfica.
3. **Archivos de recursos**: Verifica que la carpeta `images/` esté en el directorio raíz del proyecto.

## ⚙️ Instrucciones de uso

1. Compila y ejecuta el archivo `MainMenu.java`.
2. Selecciona uno de los modos disponibles desde el menú principal.
3. Disfruta del juego y consulta el ranking para ver tus estadísticas.

## 📈 Futuras mejoras

- Añadir más opciones de personalización para los jugadores.
- Introducir nuevos modos de juego con reglas adicionales.
- Incorporar sonidos y música de fondo para una experiencia más inmersiva.
