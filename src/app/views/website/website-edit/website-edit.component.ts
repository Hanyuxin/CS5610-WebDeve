import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Website} from '../../../models/website.model.client';
import {WebsiteService} from '../../../services/website.service.client';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-website-edit',
  templateUrl: './website-edit.component.html',
  styleUrls: ['./website-edit.component.css']
})
export class WebsiteEditComponent implements OnInit {

  @ViewChild('f') websiteForm: NgForm;
  wid: String;
  website: Website;
  userId: String;
  websites: Website[] = [];
  constructor(private websiteService: WebsiteService, private activatedRoute: ActivatedRoute, private router: Router) { }

  deleteWeb() {
    this.websiteService.deleteWebsite(this.wid);
    this.router.navigate(['/user', this.userId, 'website']);
  }

  update() {
    if (this.websiteForm.value.webname === '') {
      alert('Please input new web name');
      return;
    }
    this.website.name = this.websiteForm.value.webname;
    this.website.description = this.websiteForm.value.description;
    this.websiteService.updateWebsite(this.wid, this.website);
    alert('Update Success!');
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
      console.log(params['wid']);
      this.wid = params['wid'];
    });
    this.website = this.websiteService.findWebsitesById(this.wid);
  }

}
