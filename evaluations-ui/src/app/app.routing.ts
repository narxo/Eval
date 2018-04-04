import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './app.guard';
import { SingleView } from './singleView.component';
import { CombinedView } from './combinedView.component';
import { StandardView } from './views/standardView/standardView.component';
import { AsfView } from './views/asfView/asfView.component';

const appRoutes: Routes = [
    { path: 'login', component: LoginComponent},
    { path: '', component: CombinedView, canActivate: [AuthGuard], 
    children:[{ path: '', component: SingleView}]},
    {path: 'standardView', component: StandardView},
    {path: 'asfView', component: AsfView},
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);