import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {WebsiteService} from '../../../services/website.service.client';
import {Website} from '../../../models/website.model.client';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-website-new',
  templateUrl: './website-new.component.html',
  styleUrls: ['./website-new.component.css']
})
export class WebsiteNewComponent implements OnInit {
  @ViewChild('f') webForm: NgForm;
  userId: String;
  websites: Website[] = [];
  webname: String;
  description: String;

  constructor(private websiteService: WebsiteService, private activatedRoute: ActivatedRoute, private router: Router) { }

  createWeb() {
    this.webname = this.webForm.value.webname;
    this.description = this.webForm.value.description;
    this.websiteService.createWebsite(this.userId,
      new Website(this.websiteService.websites.length.toString(), this.webname, this.userId, this.description));
    alert(this.webname + ' Create Success!');
    this.router.navigate(['..']);
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
  }

}
