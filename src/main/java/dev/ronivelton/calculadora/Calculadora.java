package dev.ronivelton.calculadora;

public class Calculadora {

    public double somar(double a, double b) {
        return a + b;
    }

    public double subtrair(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida");
        }
        return a / b;
    }

    public double potencia(double base, double expoente) {
        return Math.pow(base, expoente);
    }

    public double raizQuadrada(double numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Não é possível calcular a raiz quadrada de um número negativo");
        }
        return Math.sqrt(numero);
    }

    public double calcularPorcentagem(double valor, double porcentagem) {
        return (valor * porcentagem) / 100.0;
    }

    public double qualPorcentagem(double parte, double total) {
        if (total == 0) {
            throw new IllegalArgumentException("O valor total não pode ser zero");
        }
        return (parte / total) * 100.0;
    }

    public double adicionarPorcentagem(double valor, double porcentagem) {
        return valor + calcularPorcentagem(valor, porcentagem);
    }

    public double subtrairPorcentagem(double valor, double porcentagem) {
        return valor - calcularPorcentagem(valor, porcentagem);
    }
}