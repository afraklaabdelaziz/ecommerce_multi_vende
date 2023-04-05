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