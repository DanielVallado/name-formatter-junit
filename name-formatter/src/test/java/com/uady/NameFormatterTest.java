package com.uady;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.Timeout;

import com.uady.util.MyFileReader;

public class NameFormatterTest {
    
private static int contador = 0;

    @BeforeAll //! Ejecutar al inicio, antes de cualquier otra prueba
    public static void init() {
        System.out.println("Inicio de las pruebas");
    }

    @AfterAll //! Ejecutar después de haber concluido todas las pruebas.
    public static void finish() {
        System.out.println("Fin de todas las pruebas");
    }

    @BeforeEach //! Ejecutar antes de cada una de las pruebas.
    public void setUp() {
        contador++;
        System.out.printf("Prueba %s%n", contador);
    }

    @AfterEach //! Ejecutar al final de cada una de las pruebas.
    public void tearDown() {
        System.out.println("Fin prueba");
    }

    @DisplayName("Name in uppercase") //! Sobrenombre del test
    @Tag("uppercase") //! Agrupar pruebas (mvn test -Dgroups=uppercase)
    @Test //! Declara un test convencional
    public void NameUppercase() {
        // Test case 1: Name in uppercase
        String name1 = "JOHN DOE";
        String resultado1 = NameFormatter.formatName(name1); //! Compara que sean iguales
        Assertions.assertEquals("John Doe", resultado1, "Se esperaba John Doe");
    }

    //? ****************************************************

    @Nested //! Declarar clases internas con grupos de pruebas
    class pruebasAnidadas {

        @DisplayName("Name with additional spaces")
        @RepeatedTest(4) //! Repetir test n veces
        @Timeout(5) //! Tiempo máximo de espera
        public void nameAdditionalSpaces() {
            // Test case 4: Name with additional spaces
            String name4 = "   Alice   Johnson   ";
            String resultado4 = NameFormatter.formatName(name4);
            Assertions.assertEquals("Alice Johnson", resultado4, "Se esparaba Alice Johnson");
        }

        @Test
        public void booleanosTests() {
            assertAll("booleanos", //! Grupo de aserciones
                    () -> assertTrue(true, "Se esperaba true"),
                    () -> assertFalse(false, "Se esperaba false")
            );
        }

    }

    @Test
    void TestFileReading() {
        String path = "src/main/java/com/uady/data/nombres.txt";
        File archivo = new File(path);

        //! Verifica si el archivo existe y si no, se salta el código restante
        Assumptions.assumeTrue(archivo.exists());

        List<String> datos = MyFileReader.readFile(path);
        Assertions.assertNotNull(datos); //! Verifica que no sea nulo
    }

    @Test
    void pruebaExcepcionAritmetica() { //! Verifica un tipo de excepción
        assertThrows(ArithmeticException.class, () -> {
            int resultado = 10 / 0;
        });
    }

    @TestFactory //! Pruebas dinámicas
    Collection<DynamicTest> generarPruebas() {
        return Stream.of(4, 2, 6)
                .map(numero -> dynamicTest("Prueba para el número " + numero, () -> {
                    assertEquals(0, numero % 2);
                }))
                .toList();
    }


    //* Esto no es necesario, es lo que le pidió el profe a Ricardo la clase anterior
    //! Forma para reunir los primeros test en uno solo
    @TestFactory
    @DisplayName("Test de formato de nombres")
    Collection<DynamicTest> nameFormatTests() {
        return Arrays.asList(
            testDinamico("Name in uppercase", "JOHN DOE", "John Doe"),
            testDinamico("Name with mixed letters", "MaRy AnDersON", "Mary Anderson"),
            testDinamico("Name in lowercase", "jane smith", "Jane Smith")
        );
    }

    private DynamicTest testDinamico(String testName, String input, String expectedOutput) {
        return DynamicTest.dynamicTest(testName, () -> {
            String resultado = NameFormatter.formatName(input);
            Assertions.assertEquals(expectedOutput, resultado, "El formato del nombre es incorrecto");
        });
    }

}
