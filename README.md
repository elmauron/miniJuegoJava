# Mini Juego de Rol en Java

Este proyecto es un juego de rol de cartas desarrollado en Java usando **Java Swing** para la interfaz gráfica. En el juego, dos jugadores se enfrentan con personajes generados aleatoriamente o creados a mano. Cada personaje tiene habilidades y características únicas que influyen en el resultado de las batallas.

## Características del Juego

- **Generación de Personajes**: El sistema permite crear 6 personajes aleatorios o ingresar sus datos manualmente. Estos personajes se dividen en dos equipos de tres.
- **Interfaz Gráfica**: La interfaz del juego está construida con Java Swing, facilitando una experiencia visual intuitiva y dinámica. Es bastante básica.
- **Sistema de Combate**: 
  - Basado en rondas, cada ronda consta de hasta 7 ataques por personaje o hasta que uno de los personajes quede sin salud.
  - Cálculo de daño basado en atributos de cada personaje y factores aleatorios.
- **Condiciones de Victoria**: Un jugador gana cuando los tres personajes de su oponente quedan eliminados.
  
## Atributos de los Personajes

Cada personaje cuenta con los siguientes atributos:

- **Velocidad**: Valor entre 1 y 10.
- **Destreza**: Valor entre 1 y 5.
- **Fuerza**: Valor entre 1 y 10.
- **Nivel**: Valor entre 1 y 10.
- **Armadura**: Valor entre 1 y 10.
- **Salud Inicial**: 100 puntos.
- **Raza**: Humano, Orco, Elfo.
- **Nombre y Apodo**.

## Mecánica de Combate

Cada ronda sigue la siguiente secuencia:

1. **Selección de Personaje**: El sistema selecciona al azar un personaje de cada jugador para enfrentarse.
2. **Ataques**: Los jugadores alternan ataques hasta llegar a 7 por personaje o hasta que uno quede sin salud.
3. **Cálculo de Daño**:
   - El daño se calcula en base al **Poder de Disparo**, **Efectividad de Disparo** y **Poder de Defensa**.
4. **Actualización de Salud**: La salud del personaje defensor se reduce según el daño calculado. Si llega a cero, el personaje es eliminado de la partida.

## Gameplay y Logs

- Durante el juego, se muestra un registro en la interfaz gráfica, documentando cada acción y cambio en los personajes.
- Al finalizar la partida, el log completo se guarda en un archivo de texto, permitiendo revisitar cada ronda jugada.

## Menú de Opciones

- **Iniciar Partida Aleatoria**: Genera 6 personajes aleatorios y asigna 3 a cada jugador.
- **Iniciar Partida Manual**: Permite ingresar los datos de cada personaje manualmente.
- **Leer Logs de Partidas**: Muestra el log de todas las partidas jugadas previamente.
- **Borrar Archivo de Logs**: Elimina el archivo de logs.
- **Salir**: Cierra el juego.

## Instalación y Ejecución

1. # Clonar el repositorio
git clone https://github.com/elmauron/miniJuegoJava.git
cd miniJuegoJava/MiniJuegoJava

2. # Compilar el proyecto
javac -cp src -d out src/*.java

3. # Ejecutar el juego
java -cp out MiniJuegoJavaApplication


