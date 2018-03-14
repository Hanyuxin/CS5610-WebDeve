import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
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

  constructor(private activatedRoute: ActivatedRoute, private widgetService: WidgetService, private route: Router) {}

  upload() {
    // this.name = this.imageForm.value.headerName;
    this.text = this.imageForm.value.text;
    this.url = this.imageForm.value.url;
    this.width = this.imageForm.value.width;

    const new_widget = new Widget(undefined, 'IMAGE', this.pageID,
      '1', this.text.toString(), this.width.toString(), this.url.toString());
    this.widgetService.createWidget(this.pageID, new_widget).subscribe(
      (widget: Widget) => {
        this.widget = widget;
      });
  }

  delete() {
    this.widgetService.deleteWidget(this.wgid).subscribe(
      () => this.route.navigate(['../'], {relativeTo: this.activatedRoute})
    );
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
    if (this.wgid === undefined) {
      this.widget = new Widget('', 'IMAGE', this.pageID, '', '', '', '');
    } else {
      this.widgetService.findWidgetById(this.wgid).subscribe(
        (widget: Widget) => {
          this.widget = widget;
        });
    }
  }

}
