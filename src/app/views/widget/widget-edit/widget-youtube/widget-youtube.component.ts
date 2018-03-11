import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {WidgetService} from '../../../../services/widget.service.client';
import {NgForm} from '@angular/forms';
import {Widget} from '../../../../models/widget.model.client';

@Component({
  selector: 'app-widget-youtube',
  templateUrl: './widget-youtube.component.html',
  styleUrls: ['./widget-youtube.component.css']
})
export class WidgetYoutubeComponent implements OnInit {

  @ViewChild('f') youtubeForm: NgForm;
  pageID: String;
  wgid: String;
  width: String;
  name: String;
  text: String;
  url: String;
  widget: Widget;
  constructor(private activatedRoute: ActivatedRoute, private widgetService: WidgetService) { }

  update () {
    if (this.youtubeForm.value.headName === '') {
      alert('Please input header Name');
    }
    this.widget.url = this.youtubeForm.value.url;
    this.widget.text = this.youtubeForm.value.text;
    this.widget.width = this.youtubeForm.value.width;
    if (this.wgid === undefined) {
      this.widgetService.createWidget(this.pageID, this.widget);
    } else {
      this.widgetService.updateWidget(this.wgid, this.widget);
    }
  }

  delete () {
    const widget = this.widgetService.deleteWidgetbyID(this.wgid);
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
      this.widget = new Widget('', 'YOUTUBE', this.pageID, '', '', '', '');
    } else {
      this.widget = this.widgetService.findWidgetById(this.wgid);
    }
  }

}
