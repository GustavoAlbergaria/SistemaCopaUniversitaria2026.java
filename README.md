# SistemaCopaUniversitaria2026.java
Sistema Copa Universitária UCSAL 2026

Sistema em Java, executado no terminal, que gerencia uma copa universitária de futebol com 5 seleções: cadastra times e jogadores, registra os resultados de todos os jogos e gera consultas e estatísticas do campeonato.

Projeto desenvolvido na faculdade de Engenharia de Software da Universidade Católica de Salvador, em dupla com **Felipe Bulcão Gonzalez Garcia**.

## Funcionalidades

- Cadastro de **5 seleções**, cada uma com **11 jogadores** (validação para nomes vazios)
- Geração automática de **20 jogos** (todos contra todos, ida e volta)
- Registro do **placar de cada jogo** e de **qual jogador marcou cada gol**, com suporte a **gol contra** (que não é computado para nenhum jogador)
- **Pontuação automática**: vitória = 3 pontos, empate = 1 ponto para cada seleção
- Validação das entradas (gols negativos, opções e números de inscrição inválidos)

### Consultas disponíveis

1. Seleção vencedora (desempate por mais gols dos jogadores e, depois, pelo menor número de inscrição)
2. Artilheiro(s) do campeonato, com a média de gols por partida
3. Percentual de jogadores com mais de 5 gols
4. Percentual de jogadores de uma seleção com pelo menos 1 gol
5. Desempenho das seleções (pontos e média de gols por partida)
6. Desempenho dos jogadores (gols marcados)

## Tecnologias

- Java (sem bibliotecas externas)

## Como executar

1. Clone o repositório:
```bash
   git clone https://github.com/GustavoAlbergaria/SistemaCopaUniversitaria2026.java.git
```
2. Entre na pasta do projeto:
```bash
   cd SistemaCopaUniversitaria2026.java
```
3. Compile e execute:
```bash
   javac SistemaCopaUniversitaria2026.java
   java SistemaCopaUniversitaria2026
```

Também é possível abrir o projeto no IntelliJ, VS Code ou Eclipse e executar a classe `SistemaCopaUniversitaria2026`.

## Exemplo de uso

Menu inicial:

```
Copa Universitária UCSAL 2026

[1] Cadastrar seleções
[2] Fechar programa

Digite a opção desejada:
```

Menu de consultas, exibido depois do cadastro e dos resultados:

```
===== CONSULTAS DA COPA =====
[1] Consultar seleção vencedora
[2] Consultar artilheiro(s)
[3] Consultar percentual de jogadores com mais de 5 gols
[4] Consultar percentual de jogadores de uma seleção com pelo menos 1 gol
[5] Listar desempenho das seleções
[6] Listar desempenho dos jogadores
[7] Fechar programa
```

## Estrutura do código

- `Jogador`: nome e quantidade de gols
- `Selecao`: nome, pontos e 11 jogadores, com métodos para somar gols, buscar jogadores e listar o elenco
- `Jogo`: confronto entre duas seleções, placar e atualização da pontuação
- Métodos estáticos para cadastro, registro de resultados e cada uma das consultas

## O que aprendi

- Modelagem com **classes** e relacionamento entre objetos (`Selecao` tem `Jogador`, `Jogo` tem duas `Selecao`)
- Primeiros passos com arrays para armazenar e percorrer dados (seleções, jogadores e jogos), **laços aninhados** e **switch** para organizar menus
- **Validação de entradas** do usuário e tratamento de casos especiais, como gol contra e critérios de desempate
- Trabalho em dupla e divisão de tarefas em um projeto maior

## Melhorias futuras

- Tratar entradas não numéricas (hoje o programa encerra com erro se o usuário digitar letras onde se espera número)
- Permitir configurar o número de seleções e jogadores
- Salvar os dados em arquivo

## Autores

**Gustavo Albergaria**
[LinkedIn](https://www.linkedin.com/in/gustavo-albergaria-b98583403/) | [GitHub](https://github.com/GustavoAlbergaria)

**Felipe Bulcão Gonzalez Garcia**
[LinkedIn](https://www.linkedin.com/in/felipesazz/) | [GitHub](https://gist.github.com/felipebulcao)
