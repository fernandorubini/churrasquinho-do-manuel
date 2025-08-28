\# Grupo Churrasquinho do Manuel 🍢



\[!\[Android](https://img.shields.io/badge/Android-API%2024%2B-brightgreen)](https://developer.android.com/)

\[!\[Kotlin](https://img.shields.io/badge/Kotlin-1.9.x-blue)](https://kotlinlang.org/)

\[!\[Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-black)](https://developer.android.com/jetpack/compose)

\[!\[Architecture](https://img.shields.io/badge/Architecture-Clean%20Architecture%20%7C%20MVVM-informational)]()

\[!\[DI](https://img.shields.io/badge/DI-Koin-purple)](https://insert-koin.io/)

\[!\[DB](https://img.shields.io/badge/DB-Room-orange)](https://developer.android.com/training/data-storage/room)

\[!\[Auth](https://img.shields.io/badge/Auth-Firebase%20Authentication-ffca28)](https://firebase.google.com/)



Aplicativo Android (Kotlin + Jetpack Compose) para a rede \*\*Churrasquinho do Manuel\*\*, com módulos de \*\*Cliente (B2C)\*\*, \*\*Colaboradores\*\* e \*\*Gestão\*\*. Segue \*\*Clean Architecture\*\* e \*\*MVVM\*\*, usa \*\*Koin\*\* (DI), \*\*Room\*\* (dados locais), \*\*Firebase Authentication\*\*, \*\*BigDecimal\*\* para valores e \*\*textos em pt-BR centralizados em `Strings.kt`\*\*.



> Pacote padrão: `com.example.grupochurrasquinhodomanuel`



---



\## 🧭 Sumário

\- \[Visão Geral](#visão-geral)

\- \[Principais Funcionalidades](#principais-funcionalidades)

\- \[Arquitetura e Padrões](#arquitetura-e-padrões)

\- \[Estrutura do Projeto](#estrutura-do-projeto)

\- \[Requisitos e Setup](#requisitos-e-setup)

\- \[Configs Sensíveis \& .gitignore](#configs-sensíveis--gitignore)

\- \[Como Rodar](#como-rodar)

\- \[Qualidade \& Convenções](#qualidade--convenções)

\- \[Roadmap](#roadmap)

\- \[Changelog](#changelog)

\- \[Contato](#contato)



---



\## 🚀 Visão Geral

Perfis atendidos:

1\. \*\*Cliente (B2C)\*\* — navega pelo cardápio, adiciona itens, acompanha pedidos (histórico + rastreamento) e avalia produtos.

2\. \*\*Colaboradores\*\* — visualizam pedidos por status, avançam etapas e abrem detalhes.

3\. \*\*Gestão (Manager)\*\* — painel com métricas (pedidos ativos, concluídos, vendas do dia, nota média) e gestão de colaboradores (em evolução).



---



\## ⭐ Principais Funcionalidades

\*\*Já implementadas\*\*

\- \*\*Autenticação (Firebase Auth)\*\* — login por e-mail/senha com validação e tratamento de erros.

\- \*\*Navegação condicional por tipo de usuário\*\* (`LoginState` + `UserType`).

\- \*\*Employee Home\*\* — lista pedidos por status, avança status e navega para detalhes.

\- \*\*Management Home\*\* — painel de métricas e tela detalhada (ManagementMetricsDetailScreen).

\- \*\*Order Tracking (cliente)\*\* — tela de rastreio (em transição para dados reais do Room).

\- \*\*Strings centralizadas\*\* em `core/constants/Strings.kt`.

\- \*\*Valores monetários\*\* com `BigDecimal` + `TypeConverter`.



\*\*Em andamento / próximas\*\*

\- \*\*\[P1]\*\* Conectar \*\*EmployeeManagementScreen\*\* ao \*\*Room\*\* via \*\*EmployeeRepository\*\* e \*\*EmployeeViewModel\*\* (Koin).

\- \*\*\[P2]\*\* `OrderHistory`/`OrderTracking` \*\*lendo dados reais do Room\*\*.

\- \*\*\[P3]\*\* Exibir e cadastrar \*\*Reviews\*\* no `ProductDetailScreen`.

\- \*\*\[P4]\*\* \*\*DataStore\*\* para sessão/logado + \*\*WelcomeScreen\*\* on-first-run.

\- \*\*\[P5]\*\* \*\*BottomNavigation\*\* por tipo de usuário (cliente/colaborador/gestão).



---



\## 🏗 Arquitetura e Padrões

\- \*\*Clean Architecture + MVVM\*\*

\- Camadas:

&nbsp;   - `core/` — utilitários, \*\*`Strings.kt`\*\*, enums/selos de sessão e tipos de usuário (ex.: \*\*`UserType.kt`\*\*).

&nbsp;   - `data/` — \*\*Room\*\* (entities/dao/db), \*\*repositories\*\* e mocks.

&nbsp;   - `domain/` (opcional por caso de uso) — use cases quando necessário.

&nbsp;   - `features/` — telas \*\*Compose\*\* + \*\*ViewModels\*\* por módulo (customer, employees, management, etc.).

&nbsp;   - `di/` — módulos \*\*Koin\*\* para database, repositories e viewmodels.

\- \*\*Compose\*\* — `Scaffold`, `LazyColumn`, `Dialog`, navegação declarativa.

\- \*\*StateFlow/Flow\*\* — gestão de estado reativa nos ViewModels.



---



\## 🗂 Estrutura do Projeto

> Estrutura \*\*atualizada\*\* (substitui o documento antigo). Observações:

> - \*\*`presentation/` foi renomeado para `ui/`\*\*.

> - \*\*`UserType.kt`\*\* está em `core/`.

> - \*\*`Order.kt` foi removido\*\*; agora usamos \*\*`OrderEntity`\*\* (Room) e modelos de UI em `model/`.



```text

app/

└─ src/main/java/com/example/grupochurrasquinhodomanuel/

&nbsp;  ├─ core/

&nbsp;  │  ├─ constants/

&nbsp;  │  │  └─ Strings.kt

&nbsp;  │  └─ (LoginState.kt, UserType.kt, util/…)

&nbsp;  │

&nbsp;  ├─ data/

&nbsp;  │  ├─ db/

&nbsp;  │  │  ├─ AppDatabase.kt

&nbsp;  │  │  └─ Converters.kt        # inclui BigDecimal

&nbsp;  │  ├─ dao/

&nbsp;  │  │  ├─ BrandDao.kt

&nbsp;  │  │  ├─ CustomerDao.kt

&nbsp;  │  │  ├─ EmployeeDao.kt

&nbsp;  │  │  ├─ OrderDao.kt

&nbsp;  │  │  ├─ ReviewDao.kt

&nbsp;  │  │  └─ UnitDao.kt

&nbsp;  │  ├─ entity/

&nbsp;  │  │  ├─ EmployeeEntity.kt

&nbsp;  │  │  ├─ OrderEntity.kt

&nbsp;  │  │  ├─ ReviewEntity.kt

&nbsp;  │  │  └─ UnitEntity.kt

&nbsp;  │  ├─ repository/

&nbsp;  │  │  ├─ BrandRepository.kt

&nbsp;  │  │  ├─ CustomerRepository.kt

&nbsp;  │  │  ├─ EmployeeRepository.kt

&nbsp;  │  │  ├─ OrderRepository.kt

&nbsp;  │  │  └─ ReviewRepository.kt

&nbsp;  │  └─ mock/

&nbsp;  │     └─ MockUnitList.kt

&nbsp;  │

&nbsp;  ├─ di/

&nbsp;  │  ├─ appModule.kt            # db + converters

&nbsp;  │  ├─ repositoryModule.kt

&nbsp;  │  └─ viewModelModule.kt

&nbsp;  │

&nbsp;  ├─ features/

&nbsp;  │  ├─ brand/

&nbsp;  │  │  ├─ ui/

&nbsp;  │  │  │  ├─ BrandScreen.kt

&nbsp;  │  │  │  └─ UnitScreen.kt

&nbsp;  │  │  └─ viewmodel/

&nbsp;  │  │     ├─ BrandViewModel.kt

&nbsp;  │  │     └─ UnitViewModel.kt

&nbsp;  │  │

&nbsp;  │  ├─ customer/

&nbsp;  │  │  ├─ model/               # modelos específicos de UI do módulo

&nbsp;  │  │  │  └─ Customer.kt

&nbsp;  │  │  ├─ ui/

&nbsp;  │  │  │  ├─ cart/CartScreen.kt

&nbsp;  │  │  │  ├─ menu/MenuScreen.kt

&nbsp;  │  │  │  ├─ order/OrderConfirmationScreen.kt

&nbsp;  │  │  │  └─ store/{HomeScreen.kt, StoreScreen.kt}

&nbsp;  │  │  └─ viewmodel/

&nbsp;  │  │     └─ CustomerViewModel.kt

&nbsp;  │  │

&nbsp;  │  ├─ employees/

&nbsp;  │  │  ├─ ui/

&nbsp;  │  │  │  ├─ home/EmployeeHomeScreen.kt

&nbsp;  │  │  │  └─ detail/EmployeeOrderDetailScreen.kt

&nbsp;  │  │  └─ viewmodel/

&nbsp;  │  │     └─ EmployeeViewModel.kt

&nbsp;  │  │

&nbsp;  │  ├─ login/

&nbsp;  │  │  ├─ ui/LoginScreen.kt

&nbsp;  │  │  └─ viewmodel/LoginViewModel.kt

&nbsp;  │  │

&nbsp;  │  ├─ management/

&nbsp;  │  │  ├─ ui/

&nbsp;  │  │  │  ├─ ManagementHomeScreen.kt

&nbsp;  │  │  │  ├─ ManagementContentScreen.kt

&nbsp;  │  │  │  └─ MetricsSection.kt

&nbsp;  │  │  └─ viewmodel/ManagementViewModel.kt

&nbsp;  │  │

&nbsp;  │  ├─ order/

&nbsp;  │  │  ├─ ui/OrderTrackingScreen.kt

&nbsp;  │  │  └─ viewmodel/OrderTrackingViewModel.kt

&nbsp;  │  │

&nbsp;  │  ├─ review/

&nbsp;  │  │  ├─ ui/ReviewScreen.kt

&nbsp;  │  │  └─ viewmodel/ReviewViewModel.kt

&nbsp;  │  │

&nbsp;  │  └─ register/

&nbsp;  │     └─ ui/

&nbsp;  │        ├─ CustomerRegisterScreen.kt

&nbsp;  │        ├─ EmployeeRegisterScreen.kt

&nbsp;  │        └─ UserTypeSelectionScreen.kt

&nbsp;  │

&nbsp;  ├─ model/

&nbsp;  │  ├─ OrderTracking.kt

&nbsp;  │  └─ OrderTrackingUpdate.kt   # modelos de UI globais (não-Room)

&nbsp;  │

&nbsp;  ├─ navigation/

&nbsp;  │  ├─ AppNavigation.kt

&nbsp;  │  └─ MainNavigation.kt

&nbsp;  │

&nbsp;  └─ ui/theme/

&nbsp;     ├─ Color.kt

&nbsp;     ├─ Theme.kt

&nbsp;     └─ Type.kt



\# Raiz:

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



🔐 Configs Sensíveis \& .gitignore



app/google-services.json (Firebase) — não comitar.



local.properties — caminho do SDK local — não comitar.



secrets.properties — chaves/tokens — não comitar.



Trecho essencial do .gitignore:



/.gradle/

\*\*/build/

.cxx/

local.properties

/.idea/

\*.iml

/captures/

google-services.json

secrets.properties



▶️ Como Rodar



Configure o Firebase e coloque google-services.json em app/.



Sincronize o Gradle.



Execute no emulador ou dispositivo físico.



✅ Qualidade \& Convenções



Strings: sempre em pt-BR e centralizadas em core/constants/Strings.kt.



Valores monetários: usar BigDecimal (com TypeConverter no Room).



Commits: sugere-se Conventional Commits

Ex.: feat(employee): conecta EmployeeManagementScreen ao Room.



🗺 Roadmap



&nbsp;\[P1] Conectar EmployeeManagementScreen ao Room via EmployeeRepository/EmployeeViewModel (Koin).



&nbsp;\[P2] OrderHistory/OrderTracking consumindo Room (dados reais).



&nbsp;\[P3] Exibir/cadastrar Reviews no ProductDetailScreen.



&nbsp;\[P4] DataStore para sessão/logado + WelcomeScreen on-first-run.



&nbsp;\[P5] BottomNavigation por tipo de usuário.



&nbsp;Favoritos com persistência local, cupons e ofertas (estudo).



&nbsp;Integração com métodos de pagamento (planejado).



🧾 Changelog



2025-08-28 — README revisado: renomeação presentation → ui, UserType.kt em core, remoção de Order.kt (uso de OrderEntity), estrutura/data/di atualizadas, roadmap e padrões consolidados.



📬 Contato



Autor: Fernando Rubini

GitHub: https://github.com/fernandorubini

