import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Website} from '../../../models/website.model.client';
import {WebsiteService} from '../../../services/website.service.client';

@Component({
  selector: 'app-website-edit',
  templateUrl: './website-edit.component.html',
  styleUrls: ['./website-edit.component.css']
})
export class WebsiteEditComponent implements OnInit {

  wid: String;
  userId: String;
  websites: Website[] = [];
  constructor(private websiteService: WebsiteService, private activatedRoute: ActivatedRoute, private router: Router) { }

  deleteWeb() {
    this.websiteService.deleteWebsite(this.wid);
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params: any) => {
        console.log(params['_id']);
        this.userId = params['_id'];
      }
    );
    this.websites = this.websiteService.findWebsitesByUser2(this.userId);
    console.log(this.websites);

    this.activatedRoute.params.subscribe(params => {
      console.log(params.get('wid'));
      this.wid = params.get('wid');
    });
  }

}
