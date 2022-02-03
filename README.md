# Projeto Final - POO: Sistema de gerenciamento de um cinema
Alunos: Anderson Silva e Samuel Henrique

Curso: Engenharia de Computação - 2021.2

Professor: Atílio Gomes Luiz

## 1. Objetivo
  Este documento contém os requisitos necessário para o desenvolvimento de um sistema de gerenciamento de ingresso para um cinema. Estes requisitos vão descrever uma versão inicial de um sistema para um cinema, ou seja, um mínimo produto viável – em inglês, *Minimum Viable Product* (MVP). Sendo assim, à aplicação consiste em que o cliente vai poder escolher uma determinada sala para assistir algum filme que deseje.
  
## 2. Requisitos do Sistema

### 2.1 Entidade Cinema
    
* O cinema pode ter uma ou mais salas.
* O cinema deve ter um nome.
* Deve ser possível obter a cidade do cinema.
* O cinema tem no mínimo um administrador.

### 2.2 Entidade Sala

* Um sala só pode exibir um filme por vez.
* Uma sala deve possuir uma quantidade máxima de cadeiras e fixa de 80 cadeiras.
* Só poderá entrar na sala se o cliente tiver idade suficiente para a classificação indicativa do filme.
* Deve ser possível remover uma sala.
* Deve ser possível atualizar qualquer informação de uma sala cadastrado.
* Deve ser possível fazer uma buscar pelo id da sala.
* Deve ser possível obter a capacidade da sala
* Deve ser possível listar todas as sala cadastradas

### 2.3 Entidade Cliente

* Deve ser possível remover um cliente.
* Dever ser possível realizar o cadastro de clientes (nome, idade, CPF).
* Deve ser possível atualizar qualquer informação de um cliente cadastrado.
* Deve ser possível buscar um cliente pelo CPF

### 2.4 Entidade Filme

* Deve ser possível realizar cadastro de filmes (nome, código, duração, sinopse, gênero, idioma e modalidade (3D ou 2D)).
* Deve ser possível remover um filme.
* Deve ser possível atualizar qualquer informação de um filme cadastrado.
* Deve ser possível buscar um filme pelo código
* Deve ser possível listar todos os filmes

### 2.4 Entidade Ingresso

* O ingresso tem dois preços: meia e inteira.
* O ingresso pertence a um cliente
## Diagrama de Classes - UML
* Diagrama completo de todos os pacotes
![Diagrama completo](docs/diagrama.png)

* Diagrama do pacote Models
![Pacote Models](docs/modelsdiagrama.png)

* Diagrama pacotes Repository, Services, Views e Utils 
![Pacotes Repository, Services, Views e Utils](docs/servicesdiagrama.png)

## 2.5 Mapeamento dos dados nos arquivos

Pode ser visualizado no seguinte arquivo [Mapeamento de Dados](docs/mapeamento-dados-arquivo.txt)

## 3. Como executar o projeto

Basta abrir o terminal na pasta do projeto e encontar o arquivo [Projeto-Final.jar](./Projeto-Final.jar). Com o terminal aberto, bastar executar o seguinte comando no terminal:

```sh
    java -jar Projeto-Final.jar
```

Após isso, o programa com o método *main* é executado em seu terminal, tornando o projeto livre da IDE.

## 4. Relatório

O relatório final do projeto pode ser encontrado [aqui](./Relatorio-Projeto-Cinema-Anderson-Samuel.pdf).