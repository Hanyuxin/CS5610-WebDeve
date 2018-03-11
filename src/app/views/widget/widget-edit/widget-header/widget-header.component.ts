import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {WidgetService} from '../../../../services/widget.service.client';
import {NgForm} from '@angular/forms';
import {Widget} from '../../../../models/widget.model.client';

@Component({
  selector: 'app-widget-header',
  templateUrl: './widget-header.component.html',
  styleUrls: ['./widget-header.component.css']
})
export class WidgetHeaderComponent implements OnInit {

  @ViewChild('f') headerForm: NgForm;
  wgid: String;
  pageID: String;
  widget: Widget;
  constructor(private activatedRoute: ActivatedRoute, private widgetService: WidgetService) { }

  delete() {
    this.widgetService.deleteWidgetbyID(this.wgid);
  }

  update() {
    if (this.headerForm.value.headName === '') {
      alert('Please input header Name');
    }
    this.widget.text = this.headerForm.value.text;
    this.widget.size = this.headerForm.value.size;
    if (this.wgid === undefined) {
      this.widget._id = this.widgetService.widgets.length.toString();
      this.widgetService.createWidget(this.pageID, this.widget);
    } else {
      this.widgetService.updateWidget(this.wgid, this.widget);
    }
  }
  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params: any) => {
        this.pageID = params['pid'];
      }
    );
    this.activatedRoute.params.subscribe(params => {
      this.wgid = params['wgid'];
    });
    if (this.wgid === undefined) {
      this.widget = new Widget('', 'HEADER', this.pageID, '', '', '', '');
    } else {
      this.widget = this.widgetService.findWidgetById(this.wgid);
    }
  }

}
