# Sistema de Cadastro de Alunos

## Descrição

O Sistema de Cadastro de Alunos é uma aplicação desenvolvida para gerenciar o cadastro de alunos em um banco de dados. Ele permite inserir, remover, pesquisar e inserir alunos em uma posição específica dentro do banco de dados. Este projeto foi criado como parte de um trabalho universitário, com o principal objetivo de combinar habilidades de desenvolvimento com Java e gerenciamento de banco de dados.

## Funcionalidades

- **Inserir Aluno**: Adiciona um novo aluno ao banco de dados.
- **Remover Aluno**: Remove um aluno existente do banco de dados.
- **Pesquisar Aluno**: Pesquisa um aluno no banco de dados com base em critérios específicos.
- **Inserir em Posição Específica**: Adiciona um aluno em uma posição específica na lista de alunos.

## Regras de Negócio

- **Uniqueness**: Não é possível adicionar alunos com a mesma matrícula.
- **Validação de Matrícula**: Não é possível remover alunos com matrículas inexistentes.
- **Interface Visual**: Quando um aluno é adicionado, ele aparece em uma interface que exibe todos os dados do aluno.

## Tecnologias Usadas

- **Java 22**: Linguagem de programação utilizada para desenvolver a lógica da aplicação.
- **Hibernate**: Framework de mapeamento objeto-relacional utilizado para interagir com o banco de dados.
- **SQL**: Linguagem utilizada para manipulação e consulta de dados no banco de dados.
- **Maven**: Ferramenta de automação de compilação e gerenciamento de dependências do projeto.
- **Git/GitHub**: Sistema de controle de versão e plataforma de hospedagem de código.
- **NetBeans**: Ambiente de desenvolvimento integrado (IDE) utilizado para o desenvolvimento da interface gráfica da aplicação.
