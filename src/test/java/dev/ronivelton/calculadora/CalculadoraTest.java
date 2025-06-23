package dev.ronivelton.calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da Calculadora")
public class CalculadoraTest {

    private Calculadora calculadora;

    @BeforeEach
    void setUp() {
        // Este método é executado antes de cada teste
        // Garante que cada teste tenha uma instância limpa da calculadora
        calculadora = new Calculadora();
    }

    /**
     * Método auxiliar para comparar doubles considerando -0.0 igual a 0.0
     * Isso é necessário porque em ponto flutuante IEEE 754, -0.0 e 0.0
     * são valores diferentes, mas matematicamente equivalentes.
     */
    private void assertEqualsIgnorandoZeroNegativo(double esperado, double atual, String mensagem) {
        if (esperado == 0.0 && atual == 0.0) {
            // Ambos são zero (positivo ou negativo), consideramos iguais
            return;
        }
        assertEquals(esperado, atual, 0.0001, mensagem);
    }

    @Nested
    @DisplayName("Testes de Soma")
    class TestesAdicao {

        @Test
        @DisplayName("Deve somar dois números positivos corretamente")
        void deveSomarNumerosPositivos() {
            // Arrange (preparação)
            double a = 5.0;
            double b = 3.0;
            double resultadoEsperado = 8.0;

            // Act (ação)
            double resultado = calculadora.somar(a, b);

            // Assert (verificação)
            assertEquals(resultadoEsperado, resultado, 0.0001,
                    "A soma de 5 + 3 deveria ser 8");
        }

        @Test
        @DisplayName("Deve somar números negativos corretamente")
        void deveSomarNumerosNegativos() {
            double resultado = calculadora.somar(-5.0, -3.0);
            assertEquals(-8.0, resultado, 0.0001);
        }

        @Test
        @DisplayName("Deve somar número positivo com negativo")
        void deveSomarPositivoComNegativo() {
            double resultado = calculadora.somar(10.0, -7.0);
            assertEquals(3.0, resultado, 0.0001);
        }

        @Test
        @DisplayName("Deve retornar o próprio número quando somar com zero")
        void deveSomarComZero() {
            double numero = 42.5;
            assertEquals(numero, calculadora.somar(numero, 0));
            assertEquals(numero, calculadora.somar(0, numero));
        }

        @ParameterizedTest
        @DisplayName("Deve somar corretamente vários casos")
        @CsvSource({
                "1.0, 1.0, 2.0",
                "0.1, 0.2, 0.3",
                "-1.0, 1.0, 0.0",
                "100.0, 200.0, 300.0",
                "0.0, 0.0, 0.0"
        })
        void deveSomarVariosCasos(double a, double b, double esperado) {
            assertEquals(esperado, calculadora.somar(a, b), 0.0001);
        }
    }

    @Nested
    @DisplayName("Testes de Subtração")
    class TestesSubtracao {

        @Test
        @DisplayName("Deve subtrair dois números positivos")
        void deveSubtrairNumerosPositivos() {
            assertEquals(2.0, calculadora.subtrair(5.0, 3.0));
        }

        @Test
        @DisplayName("Deve subtrair resultando em número negativo")
        void deveSubtrairResultandoNegativo() {
            assertEquals(-5.0, calculadora.subtrair(3.0, 8.0));
        }

        @Test
        @DisplayName("Deve subtrair números negativos")
        void deveSubtrairNumerosNegativos() {
            // -5 - (-3) = -5 + 3 = -2
            assertEquals(-2.0, calculadora.subtrair(-5.0, -3.0));
        }

        @Test
        @DisplayName("Subtração com zero deve retornar o número original ou seu oposto")
        void deveSubtrairComZero() {
            double numero = 15.0;
            assertEquals(numero, calculadora.subtrair(numero, 0));
            assertEquals(-numero, calculadora.subtrair(0, numero));
        }
    }

    @Nested
    @DisplayName("Testes de Multiplicação")
    class TestesMultiplicacao {

        @Test
        @DisplayName("Deve multiplicar dois números positivos")
        void deveMultiplicarNumerosPositivos() {
            assertEquals(15.0, calculadora.multiplicar(3.0, 5.0));
        }

        @Test
        @DisplayName("Deve aplicar regra de sinais na multiplicação")
        void deveAplicarRegraDeSinais() {
            // Positivo x Positivo = Positivo
            assertTrue(calculadora.multiplicar(2.0, 3.0) > 0);

            // Positivo x Negativo = Negativo
            assertTrue(calculadora.multiplicar(2.0, -3.0) < 0);

            // Negativo x Positivo = Negativo
            assertTrue(calculadora.multiplicar(-2.0, 3.0) < 0);

            // Negativo x Negativo = Positivo
            assertTrue(calculadora.multiplicar(-2.0, -3.0) > 0);
        }

        @Test
        @DisplayName("Qualquer número multiplicado por zero deve ser zero")
        void deveRetornarZeroQuandoMultiplicarPorZero() {
            // Nota: Usamos assertEqualsIgnorandoZeroNegativo porque
            // multiplicar número negativo por zero resulta em -0.0
            assertAll("Multiplicações por zero",
                    () -> assertEqualsIgnorandoZeroNegativo(0.0, calculadora.multiplicar(0, 100), "0 * 100"),
                    () -> assertEqualsIgnorandoZeroNegativo(0.0, calculadora.multiplicar(100, 0), "100 * 0"),
                    () -> assertEqualsIgnorandoZeroNegativo(0.0, calculadora.multiplicar(0, -50), "0 * -50"),
                    () -> assertEqualsIgnorandoZeroNegativo(0.0, calculadora.multiplicar(-50, 0), "-50 * 0"),
                    () -> assertEqualsIgnorandoZeroNegativo(0.0, calculadora.multiplicar(0, 0), "0 * 0")
            );
        }

        @Test
        @DisplayName("Multiplicação por 1 deve retornar o próprio número")
        void deveRetornarProprioNumeroQuandoMultiplicarPorUm() {
            double numero = 42.0;
            assertEquals(numero, calculadora.multiplicar(numero, 1));
            assertEquals(numero, calculadora.multiplicar(1, numero));
        }
    }

    @Nested
    @DisplayName("Testes de Divisão")
    class TestesDivisao {

        @Test
        @DisplayName("Deve dividir dois números positivos")
        void deveDividirNumerosPositivos() {
            assertEquals(2.0, calculadora.dividir(10.0, 5.0));
        }

        @Test
        @DisplayName("Deve dividir com resultado decimal")
        void deveDividirComResultadoDecimal() {
            assertEquals(2.5, calculadora.dividir(5.0, 2.0), 0.0001);
        }

        @Test
        @DisplayName("Deve aplicar regra de sinais na divisão")
        void deveAplicarRegraDeSinaisNaDivisao() {
            assertEquals(-2.0, calculadora.dividir(10.0, -5.0));
            assertEquals(-2.0, calculadora.dividir(-10.0, 5.0));
            assertEquals(2.0, calculadora.dividir(-10.0, -5.0));
        }

        @Test
        @DisplayName("Zero dividido por qualquer número deve ser zero")
        void zeroDivididoPorQualquerNumero() {
            // Nota: 0.0 / número negativo resulta em -0.0 devido ao padrão IEEE 754
            // Por isso usamos nosso método auxiliar que trata ambos zeros como iguais
            assertEqualsIgnorandoZeroNegativo(0.0, calculadora.dividir(0, 5.0), "0 / 5");
            assertEqualsIgnorandoZeroNegativo(0.0, calculadora.dividir(0, -5.0), "0 / -5");
            assertEqualsIgnorandoZeroNegativo(0.0, calculadora.dividir(0, 100.0), "0 / 100");
        }

        @Test
        @DisplayName("Deve lançar exceção ao dividir por zero")
        void deveLancarExcecaoAoDividirPorZero() {
            // Verificando que a exceção é lançada
            ArithmeticException exception = assertThrows(
                    ArithmeticException.class,
                    () -> calculadora.dividir(10.0, 0),
                    "Deveria lançar ArithmeticException ao dividir por zero"
            );

            // Verificando a mensagem da exceção
            assertEquals("Divisão por zero não é permitida", exception.getMessage());
        }

        @ParameterizedTest
        @DisplayName("Deve lançar exceção para qualquer divisão por zero")
        @ValueSource(doubles = {1.0, -1.0, 100.0, -100.0, 0.1, -0.1})
        void deveLancarExcecaoParaQualquerDivisaoPorZero(double dividendo) {
            assertThrows(ArithmeticException.class,
                    () -> calculadora.dividir(dividendo, 0));
        }
    }

    @Nested
    @DisplayName("Testes dos Métodos Extras")
    class TestesMetodosExtras {

        @Test
        @DisplayName("Deve calcular potência corretamente")
        void deveCalcularPotencia() {
            assertAll("Testes de potência",
                    () -> assertEquals(8.0, calculadora.potencia(2, 3)), // 2³ = 8
                    () -> assertEquals(1.0, calculadora.potencia(5, 0)), // 5⁰ = 1
                    () -> assertEquals(0.0, calculadora.potencia(0, 5)), // 0⁵ = 0
                    () -> assertEquals(0.25, calculadora.potencia(2, -2), 0.0001) // 2⁻² = 0.25
            );
        }

        @Test
        @DisplayName("Deve calcular raiz quadrada de números positivos")
        void deveCalcularRaizQuadrada() {
            assertEquals(3.0, calculadora.raizQuadrada(9.0));
            assertEquals(5.0, calculadora.raizQuadrada(25.0));
            assertEquals(0.0, calculadora.raizQuadrada(0.0));
        }

        @Test
        @DisplayName("Deve lançar exceção para raiz quadrada de número negativo")
        void deveLancarExcecaoParaRaizDeNumeroNegativo() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> calculadora.raizQuadrada(-9.0)
            );

            assertEquals("Não é possível calcular a raiz quadrada de um número negativo",
                    exception.getMessage());
        }
    }
}
