import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AuthComponent } from './layout/auth/auth.component';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AuthInterciptor } from './interciptors/auth-interciptor.interceptor';
import { AppRoutingModule } from './app-routing.module';
import { AdminComponent } from './layout/admin/admin.component';
import { VendeurComponent } from './layout/vendeur/vendeur.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { StatistiqueComponent } from './components/statistique/statistique.component';
import { StatisticComponent } from './components/statistic/statistic.component';
import { StatistiqueVendeurComponent } from './components/statistique-vendeur/statistique-vendeur.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AllUsersComponent } from './pages/all-users/all-users.component';
import { ProduitListComponent } from './pages/produit-list/produit-list.component';
import { AddProduitComponent } from './pages/add-produit/add-produit.component';
import { ProduitsComponent } from './pages/produits/produits.component';
import { PanierComponent } from './pages/panier/panier.component';
import { CommandeComponent } from './pages/commande/commande.component';
import { PanierDropDownComponent } from './components/panier-drop-down/panier-drop-down.component';
import { ClientComponent } from './layout/client/client.component';
import { AddDemmandeVendeurComponent } from './pages/add-demmande-vendeur/add-demmande-vendeur.component';
import { ListDemmandeVendeurComponent } from './pages/list-demmande-vendeur/list-demmande-vendeur.component';
import { ListProduitVendeurComponent } from './pages/list-produit-vendeur/list-produit-vendeur.component';
import { ListCommandeClientComponent } from './pages/list-commande-client/list-commande-client.component';
import { CommandeDetailsComponent } from './pages/commande-details/commande-details.component';
import { SideBarVendeurComponent } from './components/side-bar-vendeur/side-bar-vendeur.component';
import { ProfileComponent } from './pages/profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    RegisterComponent,
    LoginComponent,
    AdminComponent,
    VendeurComponent,
    NavBarComponent,
    SideBarComponent,
    StatistiqueComponent,
    StatisticComponent,
    StatistiqueVendeurComponent,
    LogoutComponent,
    AllUsersComponent,
    ProduitListComponent,
    AddProduitComponent,
    ProduitsComponent,
    PanierComponent,
    CommandeComponent,
    PanierDropDownComponent,
    ClientComponent,
    AddDemmandeVendeurComponent,
    ListDemmandeVendeurComponent,
    ListProduitVendeurComponent,
    ListCommandeClientComponent,
    CommandeDetailsComponent,
    SideBarVendeurComponent,
    ProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterciptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
