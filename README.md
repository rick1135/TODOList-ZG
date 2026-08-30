# TODO List - MVP utilizando Java + Gradle

Um gerenciador de tarefas desenvolvido em **Java puro**, sem frameworks, e gerenciado via **Gradle**, dividido em camadas seguindo o padrão arquitetural **MVC**. 

---

## Funcionalidades

- **CRUD completo de tarefas:**
  - **Criar (create):** Cadastro de tarefas com Nome, Descrição, Data de Término (`dd/MM/yyyy`), Nível de Prioridade (1 a 5), Categoria e Status.
  - **Ler (Read):**
    - Listagem geral de todas as tarefas rebalanceadas por prioridade;
    - Filtro por categoria;
    - Filtro por nível de prioridade (1 a 5);
    - Filtro por status (`TODO`, `DOING`, `DONE`);
  - **Atualizar (Update):** Edição da tarefa com rebalanceamento automático se a prioridade for alterada.
  - **Excluir (Delete):** Remoção de tarefas por ID.
- **Rebalanceamento automático por prioridade:**
  - Ao inserir ou atualizar uma tarefa, o sistema a posiciona na posição ideal, deixando a lista de tarefas ordenada em ordem decrescente de prioridade.
- **Persistência de dados em arquivo TXT:**
  - Todas as tarefas são salvas e lidas automaticamente do arquivo `tarefas.txt`, mantendo os dados persistidos entre as execuções.

---

## Arquitetura do projeto (MVC)

O projeto segue a separação de responsabilidades em camadas:

### Detalhamento das camadas

1. **Model (`Task` e `TaskStatus`):**
   Define a estrutura dos dados da tarefa: `id`, `nome`, `descricao`, `dataFinal`, `prioridade` (1 a 5), `categoria` e `status`.
2. **Repository (`TaskRepository`):**
   Única camada que tem acesso direto para a lista de dados e ao arquivo `tarefas.txt`. Gerencia a geração de IDs únicos, inserção ordenada (rebalanceamento) e operações no arquivo.
3. **Service (`TaskService`):**
   Contém as regras de negócio: valida se o nome não está vazio, se a prioridade está entre 1 e 5, e possui filtros e contagens por status.
4. **Controller (`TaskController`):**
   Desacopla a interface do usuário das regras de negócio.
5. **View (`ConsoleView`):**
   Responsável pela exibição do menu interativo no console, leitura dos inputs (`Scanner`) e tratamento de erros de digitação (`try/catch`).

---

## Formato de Persistência (`tarefas.txt`)

Os dados são armazenados linha por linha com campos delimitados por ponto e vírgula (`;`):

```text
id;nome;descricao;dataFinal;prioridade;categoria;status
```

*Exemplo:*
```text
1;Estudar Java;Revisar MVC e arquivos;2026-09-01;5;Estudos;TODO
2;Fazer Compras;Mercado do mês;2026-08-31;3;Pessoal;DOING
```

---

## Pré-requisitos

- **Java JDK 17** (ou superior)

---

## Como executar o projeto

1. Abra o terminal na pasta raiz do projeto:
   ```bash
   cd /caminho/para/TODOList-ZG
   ```

2. Compile o projeto:
   ```bash
   ./gradlew compileJava
   ```

3. Execute a aplicação no terminal interativo:
   ```bash
   ./gradlew run --console=plain
   ```

---