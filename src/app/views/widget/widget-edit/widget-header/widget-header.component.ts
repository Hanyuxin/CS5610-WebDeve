import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {WidgetService} from '../../../../services/widget.service.client';

@Component({
  selector: 'app-widget-header',
  templateUrl: './widget-header.component.html',
  styleUrls: ['./widget-header.component.css']
})
export class WidgetHeaderComponent implements OnInit {

  wgid: String;
  constructor(private activatedRoute: ActivatedRoute, private widgetService: WidgetService) { }

  delete() {
    this.widgetService.deleteWidgetbyID(this.wgid);
  }
  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      console.log(params['wgid']);
      this.wgid = params['wgid'];
    });
  }

}
