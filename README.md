Estrutura do Projeto - Grupo Churrasquinho do Manuel 🌟

Este documento descreve a estrutura atualizada do projeto, organizada por responsabilidades e features, seguindo boas práticas de modularização em projetos Android com Jetpack Compose e arquitetura limpa.

🗂️ Pacotes e suas responsabilidades

core/model

Modelos compartilhados entre módulos e features:

Brand.kt

MenuItem.kt

CartItem.kt

data

Implementações de acesso a dados (Room):

DAOs: BrandDao.kt, UnitDao.kt, ReviewDao.kt

Entidades: UnitEntity.kt

Repositórios: BrandRepository.kt, UnitRepository.kt, ReviewRepository.kt

AppDatabase.kt

Mocks (opcional): mock/MockUnitList.kt

di

Módulo de injeção de dependência (Koin):

AppModule.kt

features

Separadas por responsabilidades (ex: customer, employees, management, etc.) e cada uma com suas camadas:

features/customer

model/Customer.kt

ui/cart/CartScreen.kt, CartViewModel.kt

ui/menu/MenuScreen.kt

ui/order/OrderConfirmationScreen.kt

ui/store/HomeScreen.kt, StoreScreen.kt, StoreViewModel.kt

presentation/CustomerViewModel.kt

features/employees

model/Employee.kt

ui/home/EmployeeHomeScreen.kt

features/management

model/Management.kt

ui/ManagementHomeScreen.kt, ManagementContentScreen.kt, MetricsSection.kt

features/register

ui/CustomerRegisterScreen.kt, EmployeeRegisterScreen.kt, RegisterScreen.kt

features/login

model/LoginState.kt

presentation/LoginViewModel.kt

ui/LoginScreen.kt

model/

Modelos não exclusivos de nenhuma feature:

Order.kt, OrderTracking.kt, Product.kt, Review.kt

navigation/

Centraliza a navegação da aplicação:

AppNavigation.kt, MainNavigation.kt

💡 Padrões Adotados

Camadas separadas por responsabilidade: model, ui, presentation.

Reutilização de ViewModels com DI via Koin.

Composables centralizados em ui/ por feature.

Dados globais compartilhados no pacote core/.

Navegação declarativa com NavHost e rotas nomeadas.

Este README serve como guia de arquitetura e referência para novos colaboradores e manutenção futura do projeto.

Se houver necessidade de ajustes ou novas features, seguir a mesma estrutura modularizada.

