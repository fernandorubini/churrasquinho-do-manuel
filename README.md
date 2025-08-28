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

**Já implementadas**
- **Autenticação (Firebase Auth)** — login por e-mail/senha com validação e tratamento de erros.
- **Navegação condicional por tipo de usuário** (`LoginState` + `UserType`).
- **Employee Management** — **conectada ao Room** via `EmployeeRepository` + `EmployeeViewModel` (Koin).
- **Order History / Tracking (cliente e colaboradores)** — **lendo dados reais do Room** (DAO/Repository/ViewModel).
- **Strings centralizadas** em `core/constants/Strings.kt`.
- **Valores monetários** com `BigDecimal` + `TypeConverter`.
- **Onboarding** — **WelcomeScreen** + **DataStore** para sessão/logado (preferências).

**Em andamento / próximas**
- **[P3]** Exibir e cadastrar **Reviews** no `ProductDetailScreen`.
- **[P5]** **BottomNavigation** por tipo de usuário (cliente/colaborador/gestão).



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
  MainActivity.kt
  MyApplication.kt

  core/
    UserType.kt
    constants/
      Strings.kt
    database/
      AppDatabase.kt
      Converters.kt
      DatabaseProvider.kt
    firebase/
      FirebaseAuthManager.kt
    model/
      Brand.kt
      CartItem.kt
      MenuItem.kt
      UnitEntity.kt
      UserType.kt
    notification/
    preferences/
      AuthPreferences.kt
      FavoritePreferences.kt
      SessionPreferences.kt
      StaffVenuePreferences.kt
    session/
    ui/
    util/

  data/
    dao/
      BrandDao.kt
      CustomerDao.kt
      EmployeeDao.kt
      UnitDao.kt
    entity/
      BrandEntity.kt
      Review.kt
    local/
      CustomerEntity.kt
      EmployeeEntity.kt
    mapper/
      BrandMapper.kt
      CustomerMapper.kt
      EmployeeMapper.kt
    mock/
      mockUnitList.kt
    repository/
      BrandRepository.kt
      CustomerRepository.kt
      EmployeeRepository.kt
      EmployeeRepositoryImpl.kt
      ReviewRepository.kt
      UnitRepository.kt

  di/
    appModule.kt

  features/
    brand/
      presentation/
        BrandScreen.kt
        BrandViewModel.kt
        UnitScreen.kt
        UnitViewModel.kt
    customer/
      navigation/
        CustomerNavigationGraph.kt
      presentation/
        CustomerActivity.kt
        CustomerViewModel.kt
        FavoriteViewModel.kt
      ui/
        orderhistory.kt
    login/
      model/
        LoginState.kt
      navigation/
        LoginNavigation.kt
      presentation/
        LoginViewModel.kt
      ui/
        LoginScreen.kt
      viewmodel/
        AuthViewModel.kt
    management/
      model/
        ManagementDashboardState.kt
      ui/
        EmployeeManagementScreen.kt
        ManagementContentScreen.kt
        ManagementHomeScreen.kt
        ManagementMetricsDetailScreen.kt
        MetricsSection.kt
      viewmodel/
        EmployeeViewModel.kt
        ManagementViewModel.kt
    order/
      data/
        local/
          OrderDao.kt
          OrderEntity.kt
      model/
        Order.kt
      presentation/
        EmployeeOrderViewModel.kt
        OrderTrackingScreen.kt
      ui/
        OrderHistoryScreen.kt
        ProductDetailScreen.kt
    register/
      navigation/
        EmployeeRegisterNavigation.kt
        RegisterNavigation.kt
        UserTypeSelectionNavigation.kt
      presentation/
        RegisterViewModel.kt
      ui/
        ClientRegisterScreen.kt
        EmployeeRegisterScreen.kt
        EmployeeRegisterViewModel.kt
        EmployeeTypeSelectionScreen.kt
        UserTypeSelectionScreen.kt
    review/
      data/
        local/
          ReviewEntity.kt
      ui/
        ReviewScreen.kt
        ReviewViewModel.kt
    splash/
      presentation/
        SplashScreen.kt
      ui/
        SplashScreen.kt
    welcome/
      navigation/
        WelcomeNavigation.kt
      presentation/
        WelcomeScreen.kt
        WelcomeViewModel.kt
      WelcomePreferences.kt

  model/
    Customer.kt
    Employee.kt
    OrderTracking.kt

  navigation/
    AppNavHost.kt
    AppNavigation.kt

  ui/theme/
    color.kt
    theme.kt
    type.kt

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



🔐 Configs Sensíveis \& .gitignore



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

