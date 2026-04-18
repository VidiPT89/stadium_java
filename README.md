# 🏟️ Sistema de Gestão de Estádio — Java

> "No entanto, o árbitro continua a ser gerado aleatoriamente. Tal como na vida real."

Sistema completo de gestão de eventos num estádio de futebol, desenvolvido em Java com Programação Orientada a Objetos. Cobre tudo: do bilhete ao cachorro quente, passando pelos golos aleatórios.

---

## ⚽ O que faz

- Cria um estádio com **4 setores** (A, B, C, D) e **100 lugares** totais
- Agenda um **jogo** entre duas equipas com plantel, treinador e árbitro
- Gere **rolotes** de comida (máx. 5) com produtos e stock
- Regista **adeptos** com carteira virtual e histórico de compras
- Permite comprar **bilhetes** com visualização da matriz de lugares em tempo real
- **Simula o jogo**: 1 segundo = 1 minuto, 10% de probabilidade de golo a cada 10 minutos, intervalo de 5 segundos
- Gera um **relatório final** com receita da bilheteira e das rolotes

---

## 🗂️ Estrutura de Classes

| Classe | Responsabilidade |
|---|---|
| `Main` | Flow completo do programa |
| `Estadio` | Núcleo do sistema — agrega tudo |
| `Setor` | Matriz 5×5 de lugares, preços, disponibilidade |
| `Jogo` | Simulação do jogo e relatório final |
| `Equipa` | Nome, cidade, fundação, plantel, treinador |
| `Rolote` | Stand de comida com produtos e faturamento |
| `Produto` | Item vendável com nome, preço e stock |
| `Adepto` | Fan com carteira, bilhetes e ficheiros em disco |
| `Bilhete` | Associa lugar + setor + preço + data de compra |

---

## 🗃️ Sistema de Ficheiros

Cada adepto gera automaticamente a sua pasta:

```
adeptos/
└── AD001/
    ├── info.txt          ← dados do adepto
    └── bilhetes/
        └── A7.txt        ← dados do bilhete
```

---

## 🔄 Flow do Programa

```
1. Criar Estádio
2. Criar Jogo (2 equipas + data + árbitro)
3. Criar primeira Rolote
4. Registar Adepto
5. Comprar Bilhetes
6. Menu Pré-Jogo:
   ├── Adicionar Rolote
   ├── Criar Adepto
   ├── Comprar Comida
   ├── Comprar Bilhetes
   ├── Ver Estatísticas
   └── Iniciar Jogo → Relatório Final
```

---

## 🛠️ Tech Stack

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Programação_Orientada_a_Objetos-blue?style=flat)

**Sem dependências externas.** Java puro. Compila com `javac`, corre com `java`.

---

## ▶️ Como correr

```bash
# Compilar
javac *.java

# Executar
java Main
```

---

## 📦 Para entregar

```bash
zip nomeAlunoGestao.zip *.java
```

---

## 📝 Notas honestas

- Os golos são gerados aleatoriamente. O Gyökeres pode não marcar. Ou pode marcar três.
- O intervalo dura 5 segundos reais. Aproveita para ir buscar um cachorro quente.
- Os IDs de adeptos são sequenciais: `AD001`, `AD002`... infinitamente.
- O estádio tem ID único de 10 dígitos gerado aleatoriamente. A FIFA aprovaria.
- Limite de 5 rolotes por jogo. O estômago agradece.
- A carteira do adepto não aceita descoberto. Vida real.

---

*Próximo passo: interface gráfica. Ou não. O terminal tem o seu charme.*
