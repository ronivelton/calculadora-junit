# Calculadora JUnit - Projeto Integrado IV - A

Este projeto foi desenvolvido como Projeto integrador para aprender conceitos fundamentais de desenvolvimento de software, incluindo testes automatizados com JUnit 5 e versionamento com Git/GitHub.

## 📋 Sobre o Projeto

Uma calculadora simples em Java que implementa as quatro operações básicas (adição, subtração, multiplicação e divisão), com foco em:
- Desenvolvimento orientado a testes (TDD)
- Boas práticas de programação
- Versionamento de código com Git
- Colaboração através do GitHub

## 🚀 Tecnologias Utilizadas

- **Java 17+** - Linguagem de programação
- **JUnit 5** - Framework de testes unitários
- **Maven** - Gerenciador de dependências e build
- **Git/GitHub** - Controle de versão e colaboração

## 📁 Estrutura do Projeto

```
calculadora-junit/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── dev/ronivelton/calculadora/
│   │           └── Calculadora.java
│   └── test/
│       └── java/
│           └── dev/ronivelton/calculadora/
│               └── CalculadoraTest.java
├── pom.xml
├── .gitignore
└── README.md
```

## 🔧 Como Executar

### Pré-requisitos
- Java JDK 17 ou superior
- Maven 3.6 ou superior
- Git

### Clonando o Repositório
```bash
git clone https://github.com/seu-usuario/calculadora-junit.git
cd calculadora-junit
```

### Compilando o Projeto
```bash
mvn clean compile
```

### Executando os Testes
```bash
mvn test
```

### Gerando Relatório de Cobertura
```bash
mvn clean test
# O relatório estará em: target/site/jacoco/index.html
```

## 🧮 Funcionalidades

A calculadora implementa as seguintes operações:

- **Adição** (`somar`): Soma dois números
- **Subtração** (`subtrair`): Subtrai o segundo número do primeiro
- **Multiplicação** (`multiplicar`): Multiplica dois números
- **Divisão** (`dividir`): Divide o primeiro número pelo segundo
    - Lança `ArithmeticException` para divisão por zero
- **Potência** (`potencia`): Calcula a potência de um número
- **Raiz Quadrada** (`raizQuadrada`): Calcula a raiz quadrada
    - Lança `IllegalArgumentException` para números negativos

## 🧪 Sobre os Testes

Os testes cobrem diversos cenários para cada operação:
- Números positivos e negativos
- Operações com zero
- Casos extremos e exceções
- Testes parametrizados para múltiplos casos

## 📝 Aprendizados do Projeto

Este projeto proporcionou aprendizado prático em:

1. **Programação Orientada a Objetos** em Java
2. **Testes Unitários** com JUnit 5
3. **Controle de Versão** com Git
4. **Colaboração** com GitHub
5. **Estrutura de Projetos** Maven
6. **Tratamento de Exceções**
7**Peculiaridades de Ponto Flutuante** (IEEE 754)

## 🤝 Contribuindo

Este é um projeto educacional. Sinta-se à vontade para:
- Reportar bugs
- Sugerir novas funcionalidades
- Melhorar a documentação
- Adicionar mais testes