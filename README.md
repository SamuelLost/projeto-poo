# Projeto Final - POO: Sistema de gerenciamento de um cinema
Alunos: Anderson Silva e Samuel Henrique

Curso: Engenharia de Computação - 2021.2

Professor: Atílio Gomes Luiz

## 1. Objetivo
  Este documento contém os requisitos necessário para o desenvolvimento de um sistema de gerenciamento de ingresso para um cinema. Estes requisitos vão descrever uma versão inicial de um sistema para um cinema, ou seja, um mínimo produto viável – em inglês, *Minimum Viable Product* (MVP). Sendo assim, à aplicação consiste em que o cliente vai poder escolher uma determinada sala para assistir algum filme que deseje.
  
## 2. Requisitos do Sistema

* Entidade Cinema

  * O cinema pode ter uma ou mais salas.
  * O cinema deve ter um nome.
* Entidade Sala

  * Um sala só pode exibir um filme por vez.
  * Uma sala deve possuir uma quantidade máxima de cadeiras.
  * Só poderá entrar na sala se o cliente tiver idade suficiente para a classificação indicativa do filme.
  * Deve ser possível remover uma sala.
  * Deve ser possível atualizar qualquer informação de uma sala cadastrado.
* Entidade Cliente
  * O cliente só pode estar em uma sala de cada vez.
  * Deve ser possível remover um cliente.
  * Dever ser possível realizar o cadastro de clientes nas salas (nome, idade, CPF).
  * Deve ser possível atualizar qualquer informação de um cliente cadastrado.

* Entidade Filme
  * O ingresso tem dois preços: meia e inteira.
  * Deve ser possível realizar cadastro de filmes (nome, código, duração, sinopse, gênero, idioma e modalidade (3D ou 2D)).
  * Deve ser possível remover um filme.
  * Deve ser possível atualizar qualquer informação de um filme cadastrado.
