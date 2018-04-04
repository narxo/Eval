import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { ExpansionPanelsModule } from 'ng2-expansion-panels';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './app.guard';
import { CombinedView } from './combinedView.component';

import 'hammerjs';

import { ToolbarComponent } from './library/toolbar.component';
import { AppComponent } from './app.component';
import { StandardView } from './views/standardView/standardView.component';
import { TeamMemberView } from './views/teamMemberView/teamMemberView.component';
import { AsfView } from './views/asfView/asfView.component';
import { LoginComponent } from './login/login.component';
import { LoginService } from './login/login.service';
import { AlertService } from './alert/alert.service';
import { AlertComponent } from './alert/alert.component';
import { routing } from './app.routing';
import { SingleView } from './singleView.component'
import { LogoutComponent } from './logout/logout.component';
import { BackendModule } from './backend/backend.module';
import { MaterialModule } from "./MaterialModule.module";
import { MatIconModule, MatSelectModule } from "@angular/material";
import { Stampcard } from './stampcard/stampcard.component';
import { YearViewComponent } from './library/yearView.component';
import { YearService } from './library/year.service';
import { FeedbackDialogContent } from './feedbackDialog/feedbackDialogContent.component';


@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    Stampcard,
    LoginComponent,
    AlertComponent,
    LogoutComponent,
    SingleView,
    CombinedView,
    YearViewComponent,
    FeedbackDialogContent,
    StandardView,
    TeamMemberView,
    AsfView
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ExpansionPanelsModule,
    MatIconModule,
    MaterialModule,
    routing,
    BackendModule
  ],
  providers: [
    RouterModule,
    LoginService,
    AlertService,
    AuthGuard,
    YearService
  ],
  entryComponents: [
    FeedbackDialogContent
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
