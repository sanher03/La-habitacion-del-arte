package Demostrador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Clase que proporciona métodos para escribir en un archivo de texto.
 * Esta clase también gestiona la apertura y cierre del archivo.
 * 
 * @author Fabio Desio
 */
public class FileWriterHelper {

  private PrintWriter printWriter;
  private boolean isOpen = false;

  /**
   * Constructor que inicializa el objeto PrintWriter con el archivo especificado.
   * 
   * @param file Nombre del archivo en el que se escribirá.
   */
  public FileWriterHelper(String file) {
    try {
      printWriter = new PrintWriter(new File(file));
      isOpen = true;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      isOpen = false;
    }
  }

  /**
   * Escribe una línea en el archivo de texto.
   * 
   * @param line Línea que se escribirá en el archivo.
   */
  public void writeLine(String line) {
    if (isOpen) {
      printWriter.println(line);
    } else {
      System.err.println(
        "PrintWriter no está abierto. No se puede escribir en el archivo."
      );
    }
  }

  /**
   * Escribe una línea en el archivo de texto seguida de un salto de línea.
   * 
   * @param line Línea que se escribirá en el archivo.
   */
  public void writeLineN(String line) {
    if (isOpen) {
      printWriter.println(line + "\n");
    } else {
      System.err.println(
        "PrintWriter no está abierto. No se puede escribir en el archivo."
      );
    }
  }

  /**
   * Cierra el PrintWriter y libera los recursos asociados.
   */
  public void close() {
    if (isOpen) {
      printWriter.close();
    }
  }
}
