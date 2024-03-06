import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyInfoComponent } from './company-info/company-info.component';
import { CompanyComponent } from './company/company.component';
import { DasboardComponent } from './dasboard/dasboard.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { StockComponent } from './stock/stock.component';
import { UpdateFormComponent } from './update-form/update-form.component';


const routes: Routes = [{
  path: '', redirectTo: '/login', pathMatch: 'full'
},
{
  path: "login", component: LoginComponent
},
{
  path: "register", component: RegisterComponent
},
{
  path: "dasboard", component: DasboardComponent
},

{
  path: "stock", component: StockComponent
},
{ path: 'company-info/:companyCode', component: CompanyInfoComponent },

{ path: 'company', component: CompanyComponent },
{ path: 'update-company/:companyCode', component: UpdateFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
