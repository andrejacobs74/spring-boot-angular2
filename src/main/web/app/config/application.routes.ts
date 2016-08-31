import { Routes, RouterModule }                 from '@angular/router';
import { ModuleWithProviders }                  from '@angular/core';
import { Login }                                from '../component/login.comp';
import { Home }                                 from '../component/home.comp'

export const appRoutes: Routes = [
    {
        path: '',
        redirectTo: '/loginForm',
        terminal: true
    },
    {path: 'loginForm', component: Login},
    {path: 'home',      component: Home}
];

export const appRoutingProviders: any[] = [

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);