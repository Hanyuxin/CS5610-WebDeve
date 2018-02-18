import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {WebsiteService} from '../../../services/website.service.client';
import {PageService} from '../../../services/page.service.client';
import {Page} from '../../../models/page.model.client';

@Component({
  selector: 'app-page-edit',
  templateUrl: './page-edit.component.html',
  styleUrls: ['./page-edit.component.css']
})
export class PageEditComponent implements OnInit {

  pageID: String;
  page: Page;
  constructor(private pageService: PageService, private activatedRoute: ActivatedRoute, private router: Router) { }

  deleteWeb() {
    this.pageService.deletePage(this.pageID);
    this.router.navigate(['..']);
  }
  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      console.log(params.get('pid'));
      this.pageID = params.get('pid');
    });
    this.page = this.pageService.findPageById(this.pageID);
  }

}
