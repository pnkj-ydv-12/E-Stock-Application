import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { NavbarComponent } from './navbar/navbar.component';
import { DasboardComponent } from './dasboard/dasboard.component';
import { CompanyComponent } from './company/company.component';
import { StockComponent } from './stock/stock.component';
import { CompanyInfoComponent } from './company-info/company-info.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FilterPipe } from './pipe/FilterPipe';
import { MatDialogModule } from '@angular/material/dialog';
import { UpdateFormComponent } from './update-form/update-form.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    NavbarComponent,
    DasboardComponent,
    CompanyComponent,
    StockComponent,
    CompanyInfoComponent,
    FilterPipe,
    UpdateFormComponent
    

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatDialogModule,


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
