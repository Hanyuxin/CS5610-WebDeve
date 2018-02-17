import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {WidgetService} from '../../../../services/widget.service.client';
import {Widget} from '../../../../models/widget.model.client';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-widget-image',
  templateUrl: './widget-image.component.html',
  styleUrls: ['./widget-image.component.css']
})
export class WidgetImageComponent implements OnInit {
  @ViewChild('f') imageForm: NgForm;
  pageID: String;
  wgid: String;
  width: String;
  name: String;
  text: String;
  url: String;
  widget: Widget;

  constructor(private activatedRoute: ActivatedRoute, private widgetService: WidgetService) {}

  upload() {
    this.name = this.imageForm.value.headerName;
    this.text = this.imageForm.value.text;
    this.url = this.imageForm.value.url;
    this.width = this.imageForm.value.width;

    this.widget = new Widget(this.widgetService.widgets.length, 'IMAGE', this.pageID,
      '1', this.text.toString(), this.width.toString(), this.url.toString());
    this.widgetService.createWidget(this.pageID, this.widget);
  }

  delete() {
    this.widgetService.deleteWidgetbyID(this.wgid);
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params: any) => {
        console.log(params['pid']);
        this.pageID = params['pid'];
      }
    );

    this.activatedRoute.params.subscribe(params => {
      console.log(params['wgid']);
      this.wgid = params['wgid'];
    });
  }

}
