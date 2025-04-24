
# 🧭 *Estrutura do Projeto* - *Grupo Churrasquinho do Manuel* 🍢

Este **documento** define a **estrutura de pacotes organizada** por **responsabilidades** e features.

## 🗂️ Estrutura Sugerida

```text
core/
  model/
    Brand.kt
    CartItem.kt
    MenuItem.kt

data/
  mock/
    MockUnitList.kt
  AppDatabase.kt
  BrandDao.kt
  BrandRepository.kt
  CustomerDao.kt
  CustomerRepository.kt
  ReviewDao.kt
  ReviewRepository.kt
  UnitDao.kt
  UnitEntity.kt
  UnitRepository.kt

di/
  AppModule.kt

features/
  brand/
    presentation/
      BrandScreen.kt
      BrandViewModel.kt
      UnitScreen.kt
      UnitViewModel.kt

  customer/
    model/
      Customer.kt
    presentation/
      CustomerViewModel.kt
    ui/
      cart/
        CartScreen.kt
        CartViewModel.kt
      menu/
        MenuScreen.kt
      order/
        OrderConfirmationScreen.kt
      store/
        HomeScreen.kt
        StoreScreen.kt
        StoreViewModel.kt

  employees/
    model/
      Employee.kt
    ui/
      home/
        EmployeeHomeScreen.kt

  login/
    model/
      LoginState.kt
    presentation/
      LoginViewModel.kt
    ui/
      LoginScreen.kt

  management/
    model/
      Management.kt
    ui/
      ManagementContentScreen.kt
      ManagementHomeScreen.kt
      MetricsSection.kt

  register/
    ui/
      CustomerRegisterScreen.kt
      EmployeeRegisterScreen.kt
      RegisterScreen.kt

model/
  Order.kt
  OrderTracking.kt
  Product.kt
  Review.kt

navigation/
  AppNavigation.kt
  MainNavigation.kt

ui/
  theme/
    Color.kt
    Theme.kt
    Type.kt

MainActivity.kt
MyApplication.kt
UserType.kt

```

✅ Padrões de Organização

core/: Modelos reutilizáveis compartilhados entre módulos.

data/: Fontes de dados, incluindo DAOs, Repositórios e Banco de Dados.

di/: Injeção de dependência via Koin.

features/: Cada funcionalidade separada por pastas (brand, customer, login etc), organizadas por camadas model, presentation e ui.

model/: Modelos globais de domínio do app.

navigation/: Gerenciamento de navegação central do app.

ui/theme/: Temas e estilos visuais compartilhados.

MainActivity.kt****, MyApplication.kt, ****UserType.kt: Entrypoints e configurações principais do aplicativo.
