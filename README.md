# 👤 Sistema CRUD de Usuários

Este é um projeto simples de CRUD (Create, Read, Update, Delete) de usuários de um projeto de biblioteca que está em desenvolvimento.  
Desenvolvido com foco em aprendizado e prática de conexão com banco de dados MySQL usando o XAMPP.

---

## 🛠️ Funcionalidades

- ✅ Cadastrar novos usuários  
- 🔍 Listar usuários cadastrados  
- ✏️ Editar dados de um usuário  
- 🗑️ Excluir usuários do sistema  

---

## 💻 Tecnologias Utilizadas

- Java (backend e lógica do sistema)  
- MySQL via XAMPP (como servidor local de banco de dados)  
- Interface gráfica simples em Swing  
- Versão alternativa com banco local SQLite via JDBC  

---

## 🧰 Requisitos para rodar

- Java JDK instalado (8 ou superior)  
- XAMPP instalado e rodando (MySQL ativo)  
- MySQL configurado com:  
  - Banco de dados: `cadastro`  
  - Tabela: `usuarios` (com colunas como `id`, `nome`, `email`, `telefone`, `tipo_usuario`)  

---

## ⚙️ Como usar

1. Inicie o XAMPP e ative o módulo Apache e MySQL  
2. Crie o banco de dados e a tabela usando o script SQL abaixo:

    ```sql
    CREATE DATABASE cadastro;
    USE crud_usuarios;

    CREATE TABLE usuarios (
      id INT AUTO_INCREMENT PRIMARY KEY,
      nome VARCHAR(100),
      email VARCHAR(100),
      telefone VARCHAR(20),
      tipo_usuario VARCHAR(50)
    );
    ```

3. Compile e execute o projeto em sua IDE.  
4. Use a interface para cadastrar, editar e excluir usuários.

---

## 📦 Versão .jar com SQLite (sem dependências externas)

Este projeto inclui um arquivo executável `.jar`, que implementa o sistema CRUD sem necessidade de XAMPP e MySQL para funcionar.

O sistema utiliza um banco de dados local SQLite, acessado através do driver JDBC para SQLite.  
Isso significa que **não é necessário instalar ou configurar nenhum servidor externo de banco de dados** (como MySQL ou XAMPP): todos os dados são armazenados em um arquivo `.db` local, criado automaticamente na primeira execução.

[CRUD-Java.zip](https://github.com/user-attachments/files/21083878/CRUD-Java.zip)

---

## 🔌 Como funciona

- O `.jar` já vem preparado para funcionar com o SQLite.  
- O driver JDBC do SQLite está embutido no projeto.  
- Ao iniciar o programa, um banco de dados local (ex: `usuarios.db`) será criado no mesmo diretório do `.jar`, caso ainda não exista.  
- Todas as operações de cadastro, edição, listagem e exclusão são feitas diretamente nesse arquivo local.

---

## ▶️ Como executar

Para rodar o programa:

```bash
java -jar ProjetoBiblioteca.jar
```
ou

abra o arquivo diretamente com o Java.

> **Obs:** Certifique-se de ter o Java instalado (JDK 8 ou superior).

---

## ✍️ Autor

Desenvolvido por **[Gustavo Henrique]**  
📧 [gustavoferreira.alencar@gmail.com]  
🔗 [github.com/GustavoHenriqueAlencar](https://github.com/GustavoHenriqueAlencar)

