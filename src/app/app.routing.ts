import {Routes, RouterModule} from '@angular/router';

import {RegisterComponent} from './views/user/register/register.component';
import {LoginComponent} from './views/user/login/login.component';
import {ProfileComponent} from './views/user/profile/profile.component';
import {WebsiteListComponent} from './views/website/website-list/website-list.component';
import {WebsiteNewComponent} from './views/website/website-new/website-new.component';
import {WebsiteEditComponent} from './views/website/website-edit/website-edit.component';
import {PageListComponent} from './views/pages/page-list/page-list.component';
import {PageNewComponent} from './views/pages/page-new/page-new.component';
import {PageEditComponent} from './views/pages/page-edit/page-edit.component';


const appRoutes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'user/:_id', component: ProfileComponent},
  {path: 'user/:_id/website', component: WebsiteListComponent},
  {path: 'user/:_id/website/new', component: WebsiteNewComponent},
  {path: 'user/:_id/website/:wid', component: WebsiteEditComponent},
  {path: 'user/:uid/website/:wid/page', component: PageListComponent},
  {path: 'user/:uid/website/:wid/page/new', component: PageNewComponent},
  {path: 'user/:uid/website/:wid/page/:pid', component: PageEditComponent},
];

export const routing = RouterModule.forRoot(appRoutes);
