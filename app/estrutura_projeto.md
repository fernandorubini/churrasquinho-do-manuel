# Grupo Churrasquinho do Manuel 🍢

[![Android](https://img.shields.io/badge/Android-API%2024%2B-brightgreen)](https://developer.android.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.x-blue)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-black)](https://developer.android.com/jetpack/compose)
[![Architecture](https://img.shields.io/badge/Architecture-Clean%20Architecture%20%7C%20MVVM-informational)]()
[![DI](https://img.shields.io/badge/DI-Koin-purple)](https://insert-koin.io/)
[![DB](https://img.shields.io/badge/DB-Room-orange)](https://developer.android.com/training/data-storage/room)
[![Auth](https://img.shields.io/badge/Auth-Firebase%20Authentication-ffca28)](https://firebase.google.com/)

Aplicativo Android (Kotlin + Jetpack Compose) para a rede **Churrasquinho do Manuel**, com módulos de **Cliente (B2C)**, **Colaboradores** e **Gestão**. Segue **Clean Architecture** e **MVVM**, usa **Koin** (DI), **Room** (dados locais), **Firebase Authentication**, **BigDecimal** para valores e **textos em pt-BR centralizados em `Strings.kt`**.

> Pacote padrão: `com.example.grupochurrasquinhodomanuel`

---

## 🧭 Sumário
- [Visão Geral](#visão-geral)
- [Principais Funcionalidades](#principais-funcionalidades)
- [Arquitetura e Padrões](#arquitetura-e-padrões)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Requisitos e Setup](#requisitos-e-setup)
- [Configs Sensíveis & .gitignore](#configs-sensíveis--gitignore)
- [Como Rodar](#como-rodar)
- [Qualidade & Convenções](#qualidade--convenções)
- [Roadmap](#roadmap)
- [Changelog](#changelog)
- [Contato](#contato)

---

## 🚀 Visão Geral
Perfis atendidos:
1. **Cliente (B2C)** — navega pelo cardápio, adiciona itens, acompanha pedidos (histórico + rastreamento) e avalia produtos.
2. **Colaboradores** — visualizam pedidos por status, avançam etapas e abrem detalhes.
3. **Gestão (Manager)** — painel com métricas (pedidos ativos, concluídos, vendas do dia, nota média) e gestão de colaboradores (em evolução).

---

## ⭐ Principais Funcionalidades
**Já implementadas**
- **Autenticação (Firebase Auth)** — login por e-mail/senha com validação e tratamento de erros.
- **Navegação condicional por tipo de usuário** (`LoginState` + `UserType`).
- **Employee Home** — lista pedidos por status, avança status e navega para detalhes.
- **Management Home** — painel de métricas e tela detalhada (ManagementMetricsDetailScreen).
- **Order Tracking (cliente)** — tela de rastreio (em transição para dados reais do Room).
- **Strings centralizadas** em `core/constants/Strings.kt`.
- **Valores monetários** com `BigDecimal` + `TypeConverter`.

**Em andamento / próximas**
- **[P1]** Conectar **EmployeeManagementScreen** ao **Room** via **EmployeeRepository** e **EmployeeViewModel** (Koin).
- **[P2]** `OrderHistory`/`OrderTracking` **lendo dados reais do Room**.
- **[P3]** Exibir e cadastrar **Reviews** no `ProductDetailScreen`.
- **[P4]** **DataStore** para sessão/logado + **WelcomeScreen** on-first-run.
- **[P5]** **BottomNavigation** por tipo de usuário (cliente/colaborador/gestão).

---

## 🏗 Arquitetura e Padrões
- **Clean Architecture + MVVM**
- Camadas:
    - `core/` — utilitários, **`Strings.kt`**, enums/selos de sessão e tipos de usuário (ex.: **`UserType.kt`**).
    - `data/` — **Room** (entities/dao/db), **repositories** e mocks.
    - `domain/` (opcional por caso de uso) — use cases quando necessário.
    - `features/` — telas **Compose** + **ViewModels** por módulo (customer, employees, management, etc.).
    - `di/` — módulos **Koin** para database, repositories e viewmodels.
- **Compose** — `Scaffold`, `LazyColumn`, `Dialog`, navegação declarativa.
- **StateFlow/Flow** — gestão de estado reativa nos ViewModels.

---

## 🗂 Estrutura do Projeto
> Estrutura **atualizada** (substitui o documento antigo). Observações:
> - **`presentation/` foi renomeado para `ui/`**.
> - **`UserType.kt`** está em `core/`.
> - **`Order.kt` foi removido**; agora usamos **`OrderEntity`** (Room) e modelos de UI em `model/`.

```text
app/
└─ src/main/java/com/example/grupochurrasquinhodomanuel/
   ├─ core/
   │  ├─ constants/
   │  │  └─ Strings.kt
   │  └─ (LoginState.kt, UserType.kt, util/…)
   │
   ├─ data/
   │  ├─ db/
   │  │  ├─ AppDatabase.kt
   │  │  └─ Converters.kt        # inclui BigDecimal
   │  ├─ dao/
   │  │  ├─ BrandDao.kt
   │  │  ├─ CustomerDao.kt
   │  │  ├─ EmployeeDao.kt
   │  │  ├─ OrderDao.kt
   │  │  ├─ ReviewDao.kt
   │  │  └─ UnitDao.kt
   │  ├─ entity/
   │  │  ├─ EmployeeEntity.kt
   │  │  ├─ OrderEntity.kt
   │  │  ├─ ReviewEntity.kt
   │  │  └─ UnitEntity.kt
   │  ├─ repository/
   │  │  ├─ BrandRepository.kt
   │  │  ├─ CustomerRepository.kt
   │  │  ├─ EmployeeRepository.kt
   │  │  ├─ OrderRepository.kt
   │  │  └─ ReviewRepository.kt
   │  └─ mock/
   │     └─ MockUnitList.kt
   │
   ├─ di/
   │  ├─ appModule.kt            # db + converters
   │  ├─ repositoryModule.kt
   │  └─ viewModelModule.kt
   │
   ├─ features/
   │  ├─ brand/
   │  │  ├─ ui/
   │  │  │  ├─ BrandScreen.kt
   │  │  │  └─ UnitScreen.kt
   │  │  └─ viewmodel/
   │  │     ├─ BrandViewModel.kt
   │  │     └─ UnitViewModel.kt
   │  │
   │  ├─ customer/
   │  │  ├─ model/               # modelos específicos de UI do módulo
   │  │  │  └─ Customer.kt
   │  │  ├─ ui/
   │  │  │  ├─ cart/CartScreen.kt
   │  │  │  ├─ menu/MenuScreen.kt
   │  │  │  ├─ order/OrderConfirmationScreen.kt
   │  │  │  └─ store/{HomeScreen.kt, StoreScreen.kt}
   │  │  └─ viewmodel/
   │  │     └─ CustomerViewModel.kt
   │  │
   │  ├─ employees/
   │  │  ├─ ui/
   │  │  │  ├─ home/EmployeeHomeScreen.kt
   │  │  │  └─ detail/EmployeeOrderDetailScreen.kt
   │  │  └─ viewmodel/
   │  │     └─ EmployeeViewModel.kt
   │  │
   │  ├─ login/
   │  │  ├─ ui/LoginScreen.kt
   │  │  └─ viewmodel/LoginViewModel.kt
   │  │
   │  ├─ management/
   │  │  ├─ ui/
   │  │  │  ├─ ManagementHomeScreen.kt
   │  │  │  ├─ ManagementContentScreen.kt
   │  │  │  └─ MetricsSection.kt
   │  │  └─ viewmodel/ManagementViewModel.kt
   │  │
   │  ├─ order/
   │  │  ├─ ui/OrderTrackingScreen.kt
   │  │  └─ viewmodel/OrderTrackingViewModel.kt
   │  │
   │  ├─ review/
   │  │  ├─ ui/ReviewScreen.kt
   │  │  └─ viewmodel/ReviewViewModel.kt
   │  │
   │  └─ register/
   │     └─ ui/
   │        ├─ CustomerRegisterScreen.kt
   │        ├─ EmployeeRegisterScreen.kt
   │        └─ UserTypeSelectionScreen.kt
   │
   ├─ model/
   │  ├─ OrderTracking.kt
   │  └─ OrderTrackingUpdate.kt   # modelos de UI globais (não-Room)
   │
   ├─ navigation/
   │  ├─ AppNavigation.kt
   │  └─ MainNavigation.kt
   │
   └─ ui/theme/
      ├─ Color.kt
      ├─ Theme.kt
      └─ Type.kt

# Raiz:
MainActivity.kt
MyApplication.kt


🧩 Requisitos e Setup

Android Studio Koala ou superior

JDK 17

Gradle 8.x (wrapper)

Kotlin 1.9.x

Min SDK 24

Clonar

git clone https://github.com/fernandorubini/churrasquinho-do-manuel.git

🔐 Configs Sensíveis & .gitignore

app/google-services.json (Firebase) — não comitar.

local.properties — caminho do SDK local — não comitar.

secrets.properties — chaves/tokens — não comitar.

Trecho essencial do .gitignore:

/.gradle/
**/build/
.cxx/
local.properties
/.idea/
*.iml
/captures/
google-services.json
secrets.properties

▶️ Como Rodar

Configure o Firebase e coloque google-services.json em app/.

Sincronize o Gradle.

Execute no emulador ou dispositivo físico.

✅ Qualidade & Convenções

Strings: sempre em pt-BR e centralizadas em core/constants/Strings.kt.

Valores monetários: usar BigDecimal (com TypeConverter no Room).

Commits: sugere-se Conventional Commits
Ex.: feat(employee): conecta EmployeeManagementScreen ao Room.

🗺 Roadmap

 [P1] Conectar EmployeeManagementScreen ao Room via EmployeeRepository/EmployeeViewModel (Koin).

 [P2] OrderHistory/OrderTracking consumindo Room (dados reais).

 [P3] Exibir/cadastrar Reviews no ProductDetailScreen.

 [P4] DataStore para sessão/logado + WelcomeScreen on-first-run.

 [P5] BottomNavigation por tipo de usuário.

 Favoritos com persistência local, cupons e ofertas (estudo).

 Integração com métodos de pagamento (planejado).

🧾 Changelog

2025-08-28 — README revisado: renomeação presentation → ui, UserType.kt em core, remoção de Order.kt (uso de OrderEntity), estrutura/data/di atualizadas, roadmap e padrões consolidados.

📬 Contato

Autor: Fernando Rubini
GitHub: https://github.com/fernandorubini