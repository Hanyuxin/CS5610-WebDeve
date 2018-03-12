import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {WebsiteService} from '../../../services/website.service.client';
import {PageService} from '../../../services/page.service.client';
import {Page} from '../../../models/page.model.client';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-page-edit',
  templateUrl: './page-edit.component.html',
  styleUrls: ['./page-edit.component.css']
})
export class PageEditComponent implements OnInit {

  @ViewChild('f') pageForm: NgForm;
  pageID: String;
  page: Page;
  constructor(private pageService: PageService, private activatedRoute: ActivatedRoute, private router: Router) { }

  update() {
    const page = new Page(this.pageID, this.pageForm.value.pagename, this.page.websiteId, this.pageForm.value.title);
    this.pageService.updatePage(this.pageID, page);
    alert('success Update Page!');
  }
  deletePage() {
    this.pageService.deletePage(this.pageID);
  }
  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      console.log(params.get('pid'));
      this.pageID = params.get('pid');
    });
    this.page = this.pageService.findPageById(this.pageID);
  }

}
