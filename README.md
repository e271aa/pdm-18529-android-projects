# PDM Android Projects — Colecção de Aplicações Android

Colecção de projetos académicos desenvolvidos no âmbito da cadeira de **Programação em Dispositivos Móveis (PDM)**.

Inclui três aplicações Android demonstrando diferentes conceitos e tecnologias de desenvolvimento mobile com Java e Android Studio.

## Projetos

### 1. Calculator — Aplicação de Calculadora

Aplicação básica de calculadora Android com interface intuitiva.

**Localização:** `Calculator_07.10.2024/`

**Funcionalidades:**
- Operações aritméticas básicas (adição, subtração, multiplicação, divisão)
- Interface visual clara
- Histórico de operações

**Tecnologias:**
- Java
- Android XML Layouts
- Android Studio

**Compilação:**
```bash
cd Calculator_07.10.2024
./gradlew build
```

---

### 2. Carrinho Partilhado — App de Car-Sharing

Aplicação de gestão de um sistema de partilha de veículos.

**Localização:** `CarrinhoPartilhado/`

**Funcionalidades:**
- Gestão de utilizadores
- Reserva de veículos
- Rastreamento de disponibilidade
- Sistema de pagamentos

**Tecnologias:**
- Java
- Android Architecture Components
- Gradle
- SQLite/Base de Dados Local

**Compilação:**
```bash
cd CarrinhoPartilhado
./gradlew build
```

---

### 3. NewsAPP — Aplicação de Notícias

Aplicação para consulta de notícias e feeds de conteúdo.

**Localização:** `NewsAPP/`

**Funcionalidades:**
- Listagem de notícias
- Categorias de conteúdo
- Pesquisa de artigos
- Interface responsiva

**Tecnologias:**
- Java
- RecyclerView (listagens)
- API REST (integração com fonte de notícias)
- Gradle

**Compilação:**
```bash
cd NewsAPP
./gradlew build
```

---

## Estrutura Geral

```
pdm-18529-android-projects/
├── Calculator_07.10.2024/       # Projeto de Calculadora
│   ├── app/
│   ├── build.gradle.kts
│   └── gradlew
├── CarrinhoPartilhado/          # Projeto de Car-Sharing
│   ├── app/
│   ├── build.gradle.kts
│   └── gradlew
├── NewsAPP/                     # Projeto de Notícias
│   ├── app/
│   ├── build.gradle.kts
│   └── gradlew
└── README.md                    # Este ficheiro
```

## Tecnologias Comuns

Todos os projetos utilizam:
- **Java** — Linguagem de programação
- **Android Studio** — IDE de desenvolvimento
- **Gradle** — Sistema de build
- **Android SDK** — Framework de desenvolvimento

## Requisitos

- Android Studio (versão recomendada: 2023.1+)
- Java JDK 11 ou superior
- Android SDK 21+ (API mínima)

## Execução

Para executar qualquer um dos projetos:

1. Abrir Android Studio
2. Seleccionar `File > Open` e escolher a pasta do projeto desejado
3. Aguardar a sincronização Gradle
4. Executar em emulador ou dispositivo físico com `Run > Run 'app'`

## Progresso de Desenvolvimento

Os commits neste repositório documentam a evolução de cada projeto ao longo da cadeira, desde versões iniciais (v1.0) até versões mais avançadas com funcionalidades completas.

---

Desenvolvido por Ruben Martins · nº 18529
