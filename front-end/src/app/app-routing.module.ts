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
import { ClientComponent } from './layout/client/client.component';
import { ListDemmandeVendeurComponent } from './pages/list-demmande-vendeur/list-demmande-vendeur.component';
import { ListCommandeClientComponent } from './pages/list-commande-client/list-commande-client.component';
import { CommandeDetailsComponent } from './pages/commande-details/commande-details.component';
import { VendeurComponent } from './layout/vendeur/vendeur.component';
import { ListProduitVendeurComponent } from './pages/list-produit-vendeur/list-produit-vendeur.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { CommandeComponent } from './pages/commande/commande.component';

const routes: Routes = [

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
    path: "",
    component: ClientComponent,
    children: [
      { path: "", component: ProduitsComponent},
      { path: "panier", component: PanierComponent },
      { path: "mes-commandes", component: ListCommandeClientComponent },
      { path: "commande-details/:id", component: CommandeDetailsComponent },
      {path: "profile",component: ProfileComponent},
      {path: "logout",component: LogoutComponent},
      { path: "", redirectTo: "prduits", pathMatch: "full" },
    ],
  },

  {
    path: "admin",
    component: AdminComponent ,canActivate: [AuthGuard,RoleGuard],data:{role:'admin'},
    children: [
      {path: "logout",component: LogoutComponent},
      {path: "users",component: AllUsersComponent},
      {path: "produit-list",component: ProduitListComponent},
      {path: "demmande-list",component: ListDemmandeVendeurComponent},
      {path: "commande-list",component: CommandeComponent},
      {path: "profile",component: ProfileComponent}
    ]
  },

  {
    path: "vendeur",
    component: VendeurComponent ,canActivate: [AuthGuard,RoleGuard],data:{role:'vendeur'},
    children: [
      {path: "logout",component: LogoutComponent},
      {path: "users",component: AllUsersComponent},
      {path: "profile",component: ProfileComponent},
      {path: "produit-list",component: ListProduitVendeurComponent},
      {path: "commandes",component: ListDemmandeVendeurComponent}
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
