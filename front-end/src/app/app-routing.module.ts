import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth-guard.guard';
import { RoleGuard } from './guards/role-guard.guard';
import { AdminComponent } from './layout/admin/admin.component';
import { AuthComponent } from './layout/auth/auth.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import * as path from "path";
import { LogoutComponent } from './components/logout/logout.component';
import { AllUsersComponent } from './pages/all-users/all-users.component';
import { ProduitListComponent } from './pages/produit-list/produit-list.component';
import { ProduitsComponent } from './pages/produits/produits.component';
import { PanierComponent } from './pages/panier/panier.component';

const routes: Routes = [
  {
    path: "produit",component: ProduitsComponent
  },
  {
    path: "panier",component: PanierComponent
  },

  {
    path: "auth",
    component: AuthComponent,
    children: [
      { path: "login", component: LoginComponent },
      { path: "register", component: RegisterComponent },
      { path: "", redirectTo: "login", pathMatch: "full" },
    ],
  },

  {
    path: "admin",
    component: AdminComponent ,canActivate: [AuthGuard,RoleGuard],data:{role:'admin'},
    children: [
      {path: "logout",component: LogoutComponent},
      {path: "users",component: AllUsersComponent},
      {path: "produit-list",component: ProduitListComponent}
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
